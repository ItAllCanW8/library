package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface provides actions on book request.
 *
 * @author Artur Mironchik
 */
public interface BookRequestService {
    /**
     * Create book request boolean.
     *
     * @param fields the fields
     * @param reader the reader
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createBookRequest(Map<String, String> fields, User reader) throws ServiceException;

    /**
     * Load book requests list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<BookRequest> loadBookRequests() throws ServiceException;

    /**
     * Load book requests by reader id list.
     *
     * @param readerId the reader id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<BookRequest> loadBookRequestsByReaderId(long readerId) throws ServiceException;

    /**
     * Load reading room by reader id list.
     *
     * @param readerId            the reader id
     * @param isReadingRoomOpened the is reading room opened
     * @return the list
     * @throws ServiceException the service exception
     */
    List<BookRequest> loadReadingRoomByReaderId(long readerId, boolean isReadingRoomOpened) throws ServiceException;

    /**
     * Load rr working hours map.
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<String, String> loadRRWorkingHours() throws ServiceException;

    /**
     * Is user books num less than max boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserBooksNumLessThanMax(long userId) throws ServiceException;

    /**
     * Load max sub books coeff string.
     *
     * @return the string
     * @throws ServiceException the service exception
     */
    String loadMaxSubBooksCoeff() throws ServiceException;

    /**
     * Sort list.
     *
     * @param sortingField the sorting field
     * @param sortingOrder the sorting order
     * @return the list
     * @throws ServiceException the service exception
     */
    List<BookRequest> sort(String sortingField, String sortingOrder) throws ServiceException;

    /**
     * Find book requests by type list.
     *
     * @param type the type
     * @return the list
     * @throws ServiceException the service exception
     */
    List<BookRequest> findBookRequestsByType(String type) throws ServiceException;

    /**
     * Find book requests by state list.
     *
     * @param state the state
     * @return the list
     * @throws ServiceException the service exception
     */
    List<BookRequest> findBookRequestsByState(String state) throws ServiceException;

    /**
     * Change request state boolean.
     *
     * @param requestId          the request id
     * @param newRequestStateStr the new request state str
     * @param bookId             the book id
     * @param bookQuantity       the book quantity
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeRequestState(long requestId, String newRequestStateStr, long bookId, short bookQuantity)
            throws ServiceException;

    /**
     * Close book request boolean.
     *
     * @param userId             the user id
     * @param requestId          the request id
     * @param bookId             the book id
     * @param bookQuantity       the book quantity
     * @param requestType        the request type
     * @param expectedReturnDate the expected return date
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean closeBookRequest(long userId, long requestId, long bookId, short bookQuantity, BookRequestType requestType,
             String expectedReturnDate) throws ServiceException;

    /**
     * Delete book request boolean.
     *
     * @param requestId the request id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteBookRequest(long requestId) throws ServiceException;

    /**
     * Find email by request id optional.
     *
     * @param requestId the request id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<String> findEmailByRequestId(long requestId) throws ServiceException;

    String loadExtensionDaysCoeff() throws ServiceException;

    boolean extendBookRequest(long requestId, String expectedReturnDate, long extensionDaysCoeffStr)
            throws ServiceException;
}