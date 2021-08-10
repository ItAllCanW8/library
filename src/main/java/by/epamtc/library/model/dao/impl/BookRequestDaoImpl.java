package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.entity.*;
import by.epamtc.library.util.DateTimeHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookRequestDaoImpl implements BookRequestDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String bookReqId = "request_id";
    private static final String bookReqType = "request_type";
    private static final String bookReqSate = "state";
    private static final String bookReqDate = "request_date";
    private static final String bookReqProcessingDate = "processing_date";
    private static final String bookReqClosingDate = "closing_date";
    private static final String bookReqPenaltyAmount = "penalty_amount";
    private static final String bookReqBookId = "book_id_fk";
    private static final String bookReqUserId = "user_id_fk";

    private BookRequestDaoImpl() {
    }
    private static class Holder {
        static final BookRequestDao INSTANCE = new BookRequestDaoImpl();
    }

    public static BookRequestDao getInstance() {
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
            throw new DaoException("Error checking for book request existence.", e);
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
            throw new DaoException("Error adding book request.", e);
        }
    }

    @Override
    public List<BookRequest> loadBookRequests() throws DaoException {
        List<BookRequest> requests = new ArrayList<>();

        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_BOOK_REQUESTS);

            while (resultSet.next()) {
                requests.add(createRequestFromResultSet(resultSet));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading book requests.", e);
        }

        return requests;
    }

    @Override
    public boolean changeRequestState(long requestId, String newRequestState) throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_BOOK_REQUEST_STATE)) {
            statement.setString(1, newRequestState);
            statement.setString(2, LocalDateTime.now().format(DateTimeHelper.formatter));
            statement.setLong(3, requestId);
            statement.execute();

            return statement.getUpdateCount() == 1;
        }
        catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error changing book request state.", e);
        }
    }

    private BookRequest createRequestFromResultSet(ResultSet resultSet) throws SQLException {
        long requestId = resultSet.getLong(bookReqId);
        BookRequestType requestType = BookRequestType.fromString(resultSet.getString(bookReqType));
        BookRequestState requestState = BookRequestState.fromString(resultSet.getString(bookReqSate));
        String requestDate = resultSet.getString(bookReqDate);
        String processingDate = resultSet.getString(bookReqProcessingDate);
        String closingDate = resultSet.getString(bookReqClosingDate);
        int penaltyAmount = resultSet.getInt(bookReqPenaltyAmount);

        long bookId = resultSet.getLong(bookReqBookId);
        long userId = resultSet.getLong(bookReqUserId);

        return new BookRequest(requestId, requestType, requestState, requestDate, processingDate, closingDate,
                penaltyAmount, new Book(bookId), new User(userId));
    }
}
