package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookRequestService {
    boolean bookRequestExists(BookRequest request) throws ServiceException;
    boolean createBookRequest(Map<String, String> fields, User reader) throws ServiceException;
    List<BookRequest> loadBookRequests() throws ServiceException;
    List<BookRequest> loadBookRequestsByReaderId(long readerId) throws ServiceException;
    List<BookRequest> loadReadingRoomByReaderId(long readerId) throws ServiceException;
    List<BookRequest> sort(String sortingField, String sortingOrder) throws ServiceException;
    List<BookRequest> findBookRequestsByType(String type) throws ServiceException;
    List<BookRequest> findBookRequestsByState(String state) throws ServiceException;
    boolean changeRequestState(long requestId, String newRequestStateStr, long bookId, short bookQuantity)
            throws ServiceException;
    boolean closeBookRequest(long userId, long requestId, long bookId, short bookQuantity, BookRequestType requestType,
             String expectedReturnDate) throws ServiceException;
    boolean deleteBookRequest(long requestId) throws ServiceException;
    Optional<String> findEmailByRequestId(long requestId) throws ServiceException;
}