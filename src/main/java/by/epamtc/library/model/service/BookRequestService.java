package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.entity.User;

import java.util.List;
import java.util.Map;

public interface BookRequestService {
    boolean bookRequestExists(BookRequest request) throws ServiceException;
    boolean createBookRequest(Map<String, String> fields, User reader) throws ServiceException;
    List<BookRequest> loadBookRequests() throws ServiceException;
    List<BookRequest> loadBookRequestsByReaderId(long readerId) throws ServiceException;
    List<BookRequest> loadReadingRoomByReaderId(long readerId) throws ServiceException;
    List<BookRequest> sort(String sortingField, String sortingOrder) throws ServiceException;
    boolean changeRequestState(long requestId, String newRequestStateStr) throws ServiceException;
    boolean closeBookRequest(long requestId, long bookId, int bookQuantity, BookRequestType requestType) throws ServiceException;
    boolean deleteBookRequest(long requestId) throws ServiceException;
}