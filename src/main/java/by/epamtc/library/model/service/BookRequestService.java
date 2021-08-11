package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.User;

import java.util.List;
import java.util.Map;

public interface BookRequestService {
    boolean bookRequestExists(BookRequest request) throws ServiceException;
    boolean createBookRequest(Map<String, String> fields, User reader) throws ServiceException;
    List<BookRequest> loadBookRequests() throws ServiceException;
    List<BookRequest> loadBookRequestsByReaderId(long readerId) throws ServiceException;
    boolean changeRequestState(long requestId, String newRequestStateStr) throws ServiceException;
}