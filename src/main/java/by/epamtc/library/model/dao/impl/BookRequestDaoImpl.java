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
    private static final String bookReqClosingDate = "closing_date";
    private static final String bookReqPenaltyAmount = "penalty_amount";
    private static final String bookReqBookId = "book_id_fk";
    private static final String bookReqUserId = "user_id_fk";

    public BookRequestDaoImpl() {
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
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_BOOK_REQUEST)) {
            statement.setString(1, request.getType().getValue());
            statement.setString(2, request.getState().getValue());
            statement.setString(3, request.getRequestDate());
            statement.setLong(4, request.getBook().getId());
            statement.setLong(5, request.getUser().getId());

            statement.execute();

            return statement.getUpdateCount() == 1;
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
                requests.add(createRequestFromResultSet(resultSet, true));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading book requests.", e);
        }

        return requests;
    }

    @Override
    public List<BookRequest> loadBookRequestsByReaderId(long readerId) throws DaoException {
        List<BookRequest> requests = new ArrayList<>();

        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_BOOK_REQUESTS_BY_READER_ID)) {
            statement.setLong(1, readerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                requests.add(createRequestFromResultSet(resultSet, false));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading book requests by reader id " + readerId, e);
        }

        return requests;
    }

    @Override
    public List<BookRequest> loadReadingRoomByReaderId(long readerId) throws DaoException {
        List<BookRequest> requests = new ArrayList<>();

        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.LOAD_READING_ROOM_BY_READER_ID)) {
            statement.setLong(1, readerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                requests.add(new BookRequest(new Book(resultSet.getString("title"),
                        resultSet.getString("img"), resultSet.getString("pdf"))));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading reading room by reader id " + readerId, e);
        }

        return requests;
    }

    @Override
    public boolean changeRequestState(long requestId, String newRequestState) throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_BOOK_REQUEST_STATE)) {
            statement.setString(1, newRequestState);
            statement.setLong(2, requestId);
            statement.execute();

            return statement.getUpdateCount() == 1;
        }
        catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error changing book request state.", e);
        }
    }

    @Override
    public boolean closeBookRequest(long requestId) throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.CLOSE_BOOK_REQUEST)) {
            statement.setString(1, LocalDateTime.now().format(DateTimeHelper.formatter));
            statement.setLong(2, requestId);
            statement.execute();

            return statement.getUpdateCount() == 1;
        }
        catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error closing book request with id = " + requestId, e);
        }
    }

    @Override
    public boolean deleteBookRequest(long requestId) throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_BOOK_REQUEST)) {
            statement.setLong(1, requestId);
            statement.execute();

            return statement.getUpdateCount() == 1;
        }
        catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error deleting book request with id = " + requestId, e);
        }
    }

    private BookRequest createRequestFromResultSet(ResultSet resultSet, boolean isReqFromLibrarian) throws SQLException {
        long requestId = resultSet.getLong(bookReqId);
        BookRequestType requestType = BookRequestType.fromString(resultSet.getString(bookReqType));
        BookRequestState requestState = BookRequestState.fromString(resultSet.getString(bookReqSate));
        String requestDate = resultSet.getString(bookReqDate);
        String closingDate = resultSet.getString(bookReqClosingDate);
        int penaltyAmount = resultSet.getInt(bookReqPenaltyAmount);

        long bookId = resultSet.getLong(bookReqBookId);
        String bookTitle = resultSet.getString("title");
        String bookImg = resultSet.getString("img");
        String bookPdf= resultSet.getString("pdf");

        if(isReqFromLibrarian){
            long userId = resultSet.getLong(bookReqUserId);
            String username = resultSet.getString("username");
            String userPhoto = resultSet.getString("photo_path");

            return new BookRequest(requestId,requestType, requestState, requestDate, closingDate,
                    penaltyAmount, new Book(bookId, bookTitle, bookImg, bookPdf), new User(userId, username, userPhoto));
        } else {
            String bookAvailableQuantity = resultSet.getString("available_quantity");

            return new BookRequest(requestId, requestType, requestState, requestDate, closingDate,
                    penaltyAmount, new Book(bookId, bookTitle, bookImg, bookPdf, bookAvailableQuantity));
        }
    }
}
