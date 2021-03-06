package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.entity.*;
import by.epamtc.library.util.DateTimeHelper;
import by.epamtc.library.util.SortingHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * BookRequestDao implementation.
 *
 * @author Artur Mironchik
 */
public class BookRequestDaoImpl implements BookRequestDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String bookReqIdCol = "request_id";
    private static final String bookReqTypeCol = "request_type";
    private static final String bookReqSateCol = "state";
    private static final String bookReqDateCol = "request_date";
    private static final String bookReqExpectedReturnDate = "expected_return_date";
    private static final String bookReqClosingDateCol = "closing_date";
    private static final String bookReqBookIdCol = "book_id_fk";
    private static final String bookReqUserIdCol = "user_id_fk";

    private static final String bookImgCol = "img";
    private static final String bookTitleCol = "title";
    private static final String bookPdfCol = "pdf";
    private static final String bookQuantityCol = "available_quantity";

    private static final String usernameCol = "username";
    private static final String userPhotoCol = "photo_path";

    private static final String coeffNameCol = "coefficient_name";
    public static final String coeffValueCol = "coefficient_value";
    public static final String countOfRequestsCol = "COUNT(request_id)";

    /**
     * Constructs a BookRequestDaoImpl object.
     */
    BookRequestDaoImpl() {
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

    private boolean updateBookRequestState(long requestId, String newRequestState, String query, String expectedReturnDate)
            throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, newRequestState);

            if(expectedReturnDate != null){
                statement.setString(2, expectedReturnDate);
                statement.setLong(3, requestId);
            } else{
                statement.setLong(2, requestId);
            }

            statement.execute();

            return statement.getUpdateCount() == 1;
        }
        catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error changing book request state.", e);
        }
    }

    @Override
    public boolean changeRequestState(long requestId, String newRequestState, Optional<String> expectedReturnDate)
            throws DaoException {
        if(expectedReturnDate.isPresent())
            return updateBookRequestState(requestId,newRequestState,SqlQuery.UPDATE_BOOK_REQUEST_STATE_TO_APPROVED,
                    expectedReturnDate.get());
        else
            return updateBookRequestState(requestId,newRequestState,SqlQuery.UPDATE_BOOK_REQUEST_STATE,null);
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

    @Override
    public Optional<String> loadNumberOfDaysCoeff() throws DaoException {
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.LOAD_NUMBER_OF_DAYS_COEFF);

            return (resultSet.next() ? Optional.of(resultSet.getString(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading number of days coefficient." , e);
        }
    }

    @Override
    public Map<String, String> loadRRWorkingHours() throws DaoException {
        Map<String, String> workingHours = new HashMap<>();

        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.LOAD_READING_ROOM_COEFFS);

            while(resultSet.next())
                workingHours.put(resultSet.getString(coeffNameCol),resultSet.getString(coeffValueCol));

            return workingHours;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading reading room working hours." , e);
        }
    }

    @Override
    public void deleteReadingRoomRequests(long userId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_READING_ROOM_REQUESTS)) {
            statement.setLong(1, userId);
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error deleting reading room requests by user id " + userId, e);
        }
    }

    @Override
    public Map<String, String> loadUserAndMaxCountOfBooks(long userId) throws DaoException {
        Map<String, String> fields = new HashMap<>();

        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_AND_MAX_COUNT_OF_BOOKS_FOR_SUB)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                if(resultSet.getString(coeffValueCol) == null)
                    fields.put(coeffValueCol, loadMaxSubBooksCoeff());
                else
                    fields.put(coeffValueCol, resultSet.getString(coeffValueCol));

                fields.put(countOfRequestsCol, resultSet.getString(countOfRequestsCol));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading user and max count of book requests by user id " + userId, e);
        }
        return fields;
    }

    @Override
    public String loadMaxSubBooksCoeff() throws DaoException {
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.LOAD_EXTENSION_DAYS_COEFF);

            return (resultSet.next() ? resultSet.getString(1) : null);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading max number of books for subscription." , e);
        }
    }

    @Override
    public String loadExtensionDaysCoeff() throws DaoException {
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.LOAD_EXTENSION_DAYS_COEFF);

            return (resultSet.next() ? resultSet.getString(1) : null);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading extension days coeff." , e);
        }
    }

    @Override
    public boolean extendBookRequest(long requestId, String expectedReturnDateStr, long extensionDaysCoeff) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_BOOK_EXPECTED_RETURN_DATE)) {
            LocalDateTime newExpectedReturnDate = LocalDateTime.parse(expectedReturnDateStr, DateTimeHelper.formatter)
                    .plusDays(extensionDaysCoeff);

            statement.setString(1, newExpectedReturnDate.toString());
            statement.setLong(2, requestId);

            return statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error extending book request by request id " + requestId, e);
        }
    }

    private BookRequest createRequestFromResultSet(ResultSet resultSet, boolean areUserFieldsPresent) throws SQLException {
        long requestId = resultSet.getLong(bookReqIdCol);
        BookRequestType requestType = BookRequestType.fromString(resultSet.getString(bookReqTypeCol));
        BookRequestState requestState = BookRequestState.fromString(resultSet.getString(bookReqSateCol));
        String requestDate = resultSet.getString(bookReqDateCol);
        String expectedReturnDate = resultSet.getString(bookReqExpectedReturnDate);
        String closingDate = resultSet.getString(bookReqClosingDateCol);

        long bookId = resultSet.getLong(bookReqBookIdCol);
        String bookTitle = resultSet.getString(bookTitleCol);
        String bookImg = resultSet.getString(bookImgCol);
        String bookPdf= resultSet.getString(bookPdfCol);
        short bookAvailableQuantity = resultSet.getShort(bookQuantityCol);

        BookRequest bookRequest = new BookRequest(requestId, requestType, requestState, requestDate,expectedReturnDate, closingDate,
                new Book(bookId, bookTitle, bookImg, bookPdf, bookAvailableQuantity));

        if(areUserFieldsPresent){
            long userId = resultSet.getLong(bookReqUserIdCol);
            String username = resultSet.getString(usernameCol);
            String userPhoto = resultSet.getString(userPhotoCol);

            bookRequest.setUser(new User(userId, username, userPhoto));
        }

        return bookRequest;
    }
}