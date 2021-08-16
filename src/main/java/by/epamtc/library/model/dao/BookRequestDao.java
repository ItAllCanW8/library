package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.util.SortingHelper;

import java.util.List;

public interface BookRequestDao {
    boolean bookRequestExists(BookRequest request) throws DaoException;
    boolean add(BookRequest request) throws DaoException;
    List<BookRequest> loadBookRequests() throws DaoException;
    List<BookRequest> loadBookRequestsByReaderId(long readerId) throws DaoException;
    List<BookRequest> loadReadingRoomByReaderId(long readerId) throws DaoException;
    List<BookRequest> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType)
            throws DaoException;
    boolean changeRequestState(long requestId, String newRequestState) throws DaoException;
    boolean closeBookRequest(long requestId) throws DaoException;
    boolean deleteBookRequest(long requestId) throws DaoException;
}
