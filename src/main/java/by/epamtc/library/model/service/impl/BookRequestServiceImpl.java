package by.epamtc.library.model.service.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.dao.factory.DaoFactory;
import by.epamtc.library.model.entity.*;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.BookRequestFactory;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.factory.ServiceFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRequestServiceImpl implements BookRequestService {
    private static final BookRequestDao bookRequestDao = DaoFactory.getInstance().getBookRequestDao();
    private static final BookService bookService = ServiceFactory.getInstance().getBookService();
    private static final LibraryFactory<BookRequest> bookRequestFactory = BookRequestFactory.getInstance();

    public
    BookRequestServiceImpl() {
    }

    @Override
    public boolean bookRequestExists(BookRequest request) throws ServiceException {
        try {
            return bookRequestDao.bookRequestExists(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createBookRequest(Map<String, String> fields, User reader) throws ServiceException {
        Optional<BookRequest> requestOptional = bookRequestFactory.create(fields);
        BookRequestType bookRequestType = BookRequestType.fromString(fields.get(RequestParameter.BOOK_REQUEST_TYPE));

        if(bookRequestType == null)
            return false;

        boolean isToReadingRoom = bookRequestType.equals(BookRequestType.TO_READING_ROOM);

        try {
            if (requestOptional.isPresent()) {
                long bookId = Long.parseLong(fields.get(RequestParameter.BOOK_ID));
                Optional<Book> bookOptional = bookService.findBookById(bookId);

                if (bookOptional.isPresent()) {
                    BookRequest request = requestOptional.get();
                    Book book = bookOptional.get();

                    if(!isToReadingRoom && book.getAvailableQuantity() <= 0)
                        return false;

                    request.setUser(reader);
                    request.setBook(book);

                    boolean isRequestCreated = !bookRequestDao.bookRequestExists(request) && bookRequestDao.add(request);
                    if(!isToReadingRoom && isRequestCreated){
                        bookService.updateAvailableQuantity(bookId, (short) (book.getAvailableQuantity() - 1));
                    }

                    return isRequestCreated;
                }
            }
        } catch (NumberFormatException | DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<BookRequest> loadBookRequests() throws ServiceException {
        try {
            return bookRequestDao.loadBookRequests();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeRequestState(long requestId, String newRequestStateStr) throws ServiceException {
        try {
            BookRequestState newRequestState = BookRequestState.fromString(newRequestStateStr);
            if(newRequestState != null)
                return bookRequestDao.changeRequestState(requestId, newRequestState.getValue());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean closeBookRequest(long requestId, long bookId, int bookQuantity, BookRequestType requestType)
            throws ServiceException {
        try {
            boolean isToReadingRoom = requestType.equals(BookRequestType.TO_READING_ROOM);
            boolean isRequestClosed = bookRequestDao.closeBookRequest(requestId);

            if(!isToReadingRoom && isRequestClosed){
                bookService.updateAvailableQuantity(bookId, (short) (bookQuantity + 1));
            }

            return isRequestClosed;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteBookRequest(long requestId) throws ServiceException {
        try{
            return bookRequestDao.deleteBookRequest(requestId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookRequest> loadBookRequestsByReaderId(long readerId) throws ServiceException {
        try {
            return bookRequestDao.loadBookRequestsByReaderId(readerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookRequest> loadReadingRoomByReaderId(long readerId) throws ServiceException {
        try {
            return bookRequestDao.loadReadingRoomByReaderId(readerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
