package by.epamtc.library.model.dao.impl;

import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.BookRequestDao;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.dao.UserReportDao;

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
        /**
         * The Instance.
         */
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoFactory getInstance() {
        return DaoFactory.Holder.INSTANCE;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets book dao.
     *
     * @return the book dao
     */
    public BookDao getBookDao() {
        return bookDao;
    }

    /**
     * Gets book request dao.
     *
     * @return the book request dao
     */
    public BookRequestDao getBookRequestDao() {
        return bookRequestDao;
    }

    /**
     * Gets user report dao.
     *
     * @return the user report dao
     */
    public UserReportDao getUserReportDao() {
        return userReportDao;
    }
}