package by.epamtc.library.model.connection;

import by.epamtc.library.exception.ConnectionPoolException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String POOL_SIZE = "pool.size";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    private static final String DB_DRIVER = "db.driver";

    private BlockingQueue<ProxyConnection> freePool;
    private BlockingQueue<ProxyConnection> occupiedConnections;

    private static final int FATAL_CONNECTION_ERROR_NUMBER = 5;

    private ConnectionPool() {
    }

    private static class Holder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    public void init() throws ConnectionPoolException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            byte errorCounter = 0;

            int poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE));
            String url = properties.getProperty(DB_URL);
            String user = properties.getProperty(DB_USER);
            String password = properties.getProperty(DB_PASSWORD);

            Class.forName(properties.getProperty(DB_DRIVER));

            freePool = new ArrayBlockingQueue<>(poolSize);
            occupiedConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    freePool.add(new ProxyConnection(connection));
                }
                catch (SQLException e) {
                    LOGGER.log(Level.ERROR, "Connection hasn't been created.", e);
                    errorCounter++;
                    if(errorCounter >= FATAL_CONNECTION_ERROR_NUMBER){
                        LOGGER.log(Level.FATAL, errorCounter + " connections haven't been created.");
                        throw new RuntimeException(errorCounter + " connections haven't been created.");
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error loading db properties.", e);
            throw new ConnectionPoolException("Error loading db properties.", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.ERROR,"Error finding db driver.", e);
            throw new ConnectionPoolException("Error finding db driver.", e);
        }

        LOGGER.info("Connection pool initialized.");
    }

    public Connection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freePool.take();
            occupiedConnections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Error taking connection.", e);
            throw new ConnectionPoolException("Error taking connection.", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (connection != null) {
            occupiedConnections.remove(connection);
            try {
                freePool.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR,"Error releasing connection.", e);
                throw new ConnectionPoolException("Error releasing connection.", e);
            }
        }
    }

    public void dispose() throws ConnectionPoolException {
        try {
            for (ProxyConnection connection : freePool)
                connection.reallyClose();
            for (ProxyConnection connection : occupiedConnections)
                connection.reallyClose();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Error destroying connection pool.", e);
            throw new ConnectionPoolException("Error destroying connection pool.", e);
        }

        LOGGER.info("Connection pool closed.");
    }
}