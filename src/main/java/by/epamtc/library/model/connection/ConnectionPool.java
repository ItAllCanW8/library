package by.epamtc.library.model.connection;

import by.epamtc.library.exception.ConnectionPoolException;

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

    private BlockingQueue<Connection> freePool;
    private BlockingQueue<Connection> occupiedConnections;

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

            String url = properties.getProperty(DB_URL);
            String user = properties.getProperty(DB_USER);
            String password = properties.getProperty(DB_PASSWORD);
            int poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE));

            Class.forName(properties.getProperty(DB_DRIVER));

            freePool = new ArrayBlockingQueue<>(poolSize);
            occupiedConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freePool.add(connection);
            }
        } catch (IOException e) {
            LOGGER.error("Oops! Error loading db properties.", e);
            throw new ConnectionPoolException("Oops! Error loading db properties.", e);
        } catch (SQLException e) {
            LOGGER.error("Oops! Error connecting to db.", e);
            throw new ConnectionPoolException("Oops! Error connecting to db.", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Oops! Error finding db driver.", e);
            throw new ConnectionPoolException("Oops! Error finding db driver.", e);
        }

        LOGGER.info("Connection pool initialized successfully.");
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;

        try {
            connection = freePool.take();
            occupiedConnections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Oops! Error connecting to data source.", e);
            throw new ConnectionPoolException("Oops! Error connecting to data source.", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        returnConnection(connection);
    }

    public void releaseConnection(Connection connection, Statement statement) throws ConnectionPoolException {
        returnConnection(connection);
        closeStatement(statement);
    }

    public void releaseConnection(Connection connection, Statement statement, ResultSet rs) throws ConnectionPoolException {
        returnConnection(connection);
        closeStatement(statement);
        closeResultSet(rs);
    }

    private void returnConnection(Connection connection) throws ConnectionPoolException {
        if (connection != null) {
            occupiedConnections.remove(connection);
            try {
                freePool.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error("Oops! Error releasing connection.", e);
                throw new ConnectionPoolException("Oops! Error releasing connection.", e);
            }
        }
    }

    private void closeStatement(Statement statement) throws ConnectionPoolException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Oops! Error closing statement", e);
                throw new ConnectionPoolException("Oops! Error closing statement", e);
            }
        }
    }

    private void closeResultSet(ResultSet rs) throws ConnectionPoolException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("Oops! Error closing result set.", e);
                throw new ConnectionPoolException("Oops! Error closing result set.", e);
            }
        }
    }

    public void dispose() throws ConnectionPoolException {
        try {
            for (Connection connection : freePool) {
                connection.close();
            }
            for (Connection connection : occupiedConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Oops! Error closing connections.", e);
            throw new ConnectionPoolException("Oops! Error closing connections.", e);
        }

        LOGGER.info("Connection pool closed successfully.");
    }
}
