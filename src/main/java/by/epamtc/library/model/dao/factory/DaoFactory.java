package by.epamtc.library.model.dao.factory;

import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.dao.UserReportDao;
import by.epamtc.library.model.dao.impl.BookDaoImpl;
import by.epamtc.library.model.dao.impl.BookRequestDaoImpl;
import by.epamtc.library.model.dao.impl.UserDaoImpl;
import by.epamtc.library.model.dao.impl.UserReportDaoImpl;

/**
 * Class that represents factory pattern for Dao layer.
 *
 * @author Artur Mironchik
 */
public class DaoFactory {
    private final UserDao userDao = new UserDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();
    private final BookRequestDao bookRequestDao = new BookRequestDaoImpl();
    private final UserReportDao userReportDao = new UserReportDaoImpl();

    private DaoFactory() {
    }

    private static class Holder {
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return DaoFactory.Holder.INSTANCE;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public BookRequestDao getBookRequestDao() {
        return bookRequestDao;
    }

    public UserReportDao getUserReportDao() {
        return userReportDao;
    }
}