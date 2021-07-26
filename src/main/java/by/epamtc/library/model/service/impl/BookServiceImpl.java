package by.epamtc.library.model.service.impl;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.impl.BookDaoImpl;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.BookFactory;
import by.epamtc.library.model.service.BookService;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookServiceImpl implements BookService {
    private static final BookDao bookDao = BookDaoImpl.getInstance();
    private static final LibraryFactory<Book> bookFactory = BookFactory.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile BookService instance;

    private BookServiceImpl() {
    }
    public static BookService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new BookServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public List<Book> loadPopularBooks() throws ServiceException {
        try {
            return bookDao.loadPopularBooks();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
