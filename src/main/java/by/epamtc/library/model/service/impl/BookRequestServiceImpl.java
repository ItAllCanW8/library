package by.epamtc.library.model.service.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.dao.impl.BookDaoImpl;
import by.epamtc.library.model.dao.impl.BookRequestDaoImpl;
import by.epamtc.library.model.dao.impl.UserDaoImpl;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.BookRequestFactory;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.BookService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRequestServiceImpl implements BookRequestService {
    private static final BookRequestDao bookRequestDao = BookRequestDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final BookDao bookDao = BookDaoImpl.getInstance();
    private static final LibraryFactory<BookRequest> bookRequestFactory = BookRequestFactory.getInstance();
    private static final BookService bookService = BookServiceImpl.getInstance();

    private BookRequestServiceImpl() {
    }

    private static class Holder {
        static final BookRequestService INSTANCE = new BookRequestServiceImpl();
    }

    public static BookRequestService getInstance() {
        return BookRequestServiceImpl.Holder.INSTANCE;
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
        try {
            if (requestOptional.isPresent()) {
                long bookId = Long.parseLong(fields.get(RequestParameter.BOOK_ID));
                Optional<Book> bookOptional = bookService.findBookById(bookId);

                if (bookOptional.isPresent()) {
                    BookRequest request = requestOptional.get();
                    Book book = bookOptional.get();

                    if(Integer.parseInt(book.getAvailableQuantity()) <= 0)
                        return false;

                    request.setUser(reader);
                    request.setBook(book);

                    boolean isRequestCreated = !bookRequestDao.bookRequestExists(request) && bookRequestDao.add(request);
                    if(isRequestCreated){
                        bookDao.updateAvailableQuantity(bookId,
                                Integer.parseInt(book.getAvailableQuantity()) - 1 );
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
}
