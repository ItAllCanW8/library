package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.util.SortingHelper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface used for interactions with book requests table.
 *
 * @author Artur Mironchik
 */
public interface BookRequestDao {
    /**
     * Checks if book request exists.
     *
     * @param request BookRequest object of user book request.
     * @return boolean value. True if the book request exists, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean bookRequestExists(BookRequest request) throws DaoException;

    /**
     * Adds book request to the table.
     *
     * @param request BookRequest book request.
     * @return boolean value. True if the book has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(BookRequest request) throws DaoException;

    /**
     * Loads all book requests.
     *
     * @return List object of books.
     * @throws DaoException if the database throws SQLException.
     */
    List<BookRequest> loadBookRequests() throws DaoException;

    /**
     * Loads book requests by reader id.
     *
     * @param readerId long value of reader's id.
     * @return List object of book requests.
     * @throws DaoException if the database throws SQLException.
     */
    List<BookRequest> loadBookRequestsByReaderId(long readerId) throws DaoException;

    /**
     * Loads reading room by reader id.
     *
     * @param readerId long value of reader's id.
     * @return List object of book requests.
     * @throws DaoException if the database throws SQLException.
     */
    List<BookRequest> loadReadingRoomByReaderId(long readerId) throws DaoException;

    /**
     * Sorts book requests.
     *
     * @param sortingColumn SortingColumn object of book requests sorting column;
     * @param sortingOrderType SortingOrderType object of sorting order type;
     * @return List object of book requests.
     * @throws DaoException if the database throws SQLException.
     */
    List<BookRequest> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType)
            throws DaoException;

    /**
     * Finds book requests by type.
     *
     * @param requestType BookRequestType object that represents book request type.
     * @return List object of book requests.
     * @throws DaoException if the database throws SQLException.
     */
    List<BookRequest> findBookRequestsByType(BookRequestType requestType) throws DaoException;

    /**
     * Finds book requests by state.
     *
     * @param requestState BookRequestState object that represents book request state.
     * @return List object of book requests.
     * @throws DaoException if the database throws SQLException.
     */
    List<BookRequest> findBookRequestsByState(BookRequestState requestState) throws DaoException;

    /**
     * Changes book request state.
     *
     * @param requestId long value of book request id.
     * @param newRequestState String object that represents new state of book request.
     * @param expectedReturnDate Optional object of String that represents book's expected return date.
     * @return true if request state was changed, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean changeRequestState(long requestId, String newRequestState, Optional<String> expectedReturnDate)
            throws DaoException;

    /**
     * Closes book request.
     *
     * @param requestId long value of book request id.
     * @return true if book request was closed, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean closeBookRequest(long requestId) throws DaoException;

    /**
     * Deletes book request.
     *
     * @param requestId long value of book request id.
     * @return true if book request was deleted, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean deleteBookRequest(long requestId) throws DaoException;

    /**
     * Finds user email by book request id.
     *
     * @param requestId long value of request id.
     * @return Optional object of String if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> findEmailByRequestId(long requestId) throws DaoException;

    /**
     * Loads coefficient that represents the number of days for which the book is issued.
     *
     * @return Optional object of String if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> loadNumberOfDaysCoeff() throws DaoException;

    /**
     * Loads working hours of reading room.
     *
     * @return Map with String keys and values.
     * @throws DaoException if the database throws SQLException.
     */
    Map<String, String> loadRRWorkingHours() throws DaoException;


    void deleteReadingRoomRequests(long userId) throws  DaoException;

    /**
     * Loads max allowed number of book requests for subscription and number of such user book requests.
     *
     * @return Map with String keys and values.
     * @throws DaoException if the database throws SQLException.
     */
    Map<String, String> loadUserAndMaxCountOfBooks(long userId) throws DaoException;

    /**
     * Loads max allowed number of book requests for subscription.
     *
     * @return String object that represents coefficient.
     * @throws DaoException if the database throws SQLException.
     */
    String loadMaxSubBooksCoeff() throws DaoException;
    String loadExtensionDaysCoeff() throws DaoException;

    boolean extendBookRequest(long requestId, String expectedReturnDate, long extensionDaysCoeffStr)
            throws DaoException;
}