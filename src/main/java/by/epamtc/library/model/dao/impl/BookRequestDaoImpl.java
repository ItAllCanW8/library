package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.entity.*;
import by.epamtc.library.util.DateTimeHelper;
import by.epamtc.library.util.SortingHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRequestDaoImpl implements BookRequestDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String bookReqIdCol = "request_id";
    private static final String bookReqTypeCol = "request_type";
    private static final String bookReqSateCol = "state";
    private static final String bookReqDateCol = "request_date";
    private static final String bookReqClosingDateCol = "closing_date";
    private static final String bookReqPenaltyAmountCol = "penalty_amount";
    private static final String bookReqBookIdCol = "book_id_fk";
    private static final String bookReqUserIdCol = "user_id_fk";

    private static final String bookImgCol = "img";
    private static final String bookTitleCol = "title";
    private static final String bookPdfCol = "pdf";
    private static final String bookQuantityCol = "available_quantity";

    private static final String usernameCol = "username";
    private static final String userPhotoCol = "photo_path";



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
                requests.add(new BookRequest(new Book(resultSet.getString(bookTitleCol),
                        resultSet.getString(bookImgCol), resultSet.getString(bookPdfCol))));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading reading room by reader id " + readerId, e);
        }

        return requests;
    }

    @Override
    public List<BookRequest> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType) throws DaoException {
        List<BookRequest> bookRequests = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SORT_BOOK_REQUESTS + sortingColumn.getValue() + " "
                    + sortingOrderType.getValue());

            while (resultSet.next()) {
                bookRequests.add(createRequestFromResultSet(resultSet, true));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error sorting book requests by " + sortingColumn + " " + sortingOrderType, e);
        }
        return bookRequests;
    }

    @Override
    public List<BookRequest> findBookRequestsByType(BookRequestType requestType) throws DaoException {
        List<BookRequest> bookRequests = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_REQUESTS_BY_TYPE)) {
            statement.setString(1, requestType.getValue());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                bookRequests.add(createRequestFromResultSet(resultSet, true));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding book requests by type " + requestType.getValue(), e);
        }
        return bookRequests;
    }

    @Override
    public List<BookRequest> findBookRequestsByState(BookRequestState requestState) throws DaoException {
        List<BookRequest> bookRequests = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BOOK_REQUESTS_BY_STATE)) {
            statement.setString(1, requestState.getValue());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                bookRequests.add(createRequestFromResultSet(resultSet, true));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding book requests by state " + requestState.getValue(), e);
        }
        return bookRequests;
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

    @Override
    public Optional<String> findEmailByRequestId(long requestId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_EMAIL_BY_REQUEST_ID)) {
            statement.setLong(1, requestId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getString(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding email by book request id " + requestId, e);
        }
    }

    private BookRequest createRequestFromResultSet(ResultSet resultSet, boolean areUserFieldsPresent) throws SQLException {
        long requestId = resultSet.getLong(bookReqIdCol);
        BookRequestType requestType = BookRequestType.fromString(resultSet.getString(bookReqTypeCol));
        BookRequestState requestState = BookRequestState.fromString(resultSet.getString(bookReqSateCol));
        String requestDate = resultSet.getString(bookReqDateCol);
        String closingDate = resultSet.getString(bookReqClosingDateCol);
        int penaltyAmount = resultSet.getInt(bookReqPenaltyAmountCol);

        long bookId = resultSet.getLong(bookReqBookIdCol);
        String bookTitle = resultSet.getString(bookTitleCol);
        String bookImg = resultSet.getString(bookImgCol);
        String bookPdf= resultSet.getString(bookPdfCol);

        if(areUserFieldsPresent){
            long userId = resultSet.getLong(bookReqUserIdCol);
            String username = resultSet.getString(usernameCol);
            String userPhoto = resultSet.getString(userPhotoCol);

            return new BookRequest(requestId,requestType, requestState, requestDate, closingDate,
                    penaltyAmount, new Book(bookId, bookTitle, bookImg, bookPdf), new User(userId, username, userPhoto));
        } else {
            short bookAvailableQuantity = resultSet.getShort(bookQuantityCol);

            return new BookRequest(requestId, requestType, requestState, requestDate, closingDate,
                    penaltyAmount, new Book(bookId, bookTitle, bookImg, bookPdf, bookAvailableQuantity));
        }
    }
}
