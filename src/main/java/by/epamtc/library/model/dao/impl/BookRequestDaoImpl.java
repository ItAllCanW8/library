package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.entity.BookRequestType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRequestDaoImpl implements BookRequestDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private BookRequestDaoImpl() {
    }
    private static class Holder {
        static final BookRequestDaoImpl INSTANCE = new BookRequestDaoImpl();
    }

    public static BookRequestDaoImpl getInstance() {
        return BookRequestDaoImpl.Holder.INSTANCE;
    }

    @Override
    public boolean bookRequestExists(BookRequest request) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_BOOK_REQUEST_FOR_EXISTENCE)) {
            statement.setLong(1, request.getBook().getId());
            statement.setLong(2, request.getUser().getId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error checking for book request existence.");
        }
    }

    @Override
    public boolean add(BookRequest request) throws DaoException {
        if(BookRequestState.fromString(request.getState().getValue()) == null &&
                BookRequestType.fromString(request.getType().getValue()) == null)
            throw new DaoException("Invalid BookRequest state or type.");

        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_BOOK_REQUEST)) {
            statement.setString(1, request.getType().getValue());
            statement.setString(2, request.getState().getValue());
            statement.setString(3, request.getRequestDate());
            statement.setLong(4, request.getBook().getId());
            statement.setLong(5, request.getUser().getId());

            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error adding book request." + e);
        }
    }
}
