package by.epamtc.library.model.service.impl;

import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.UserReportService;
import by.epamtc.library.model.service.UserService;

/**
 * Class that represents service factory.
 *
 * @author Artur Mironchik
 */
public class ServiceFactory {
    private final UserService userService = new UserServiceImpl();
    private final BookService bookService = new BookServiceImpl();
    private final BookRequestService bookRequestService = new BookRequestServiceImpl();
    private final UserReportService userReportService = new UserReportServiceImpl();

    private ServiceFactory() {
    }

    private static class Holder {
        /**
         * The Instance.
         */
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets book service.
     *
     * @return the book service
     */
    public BookService getBookService() {
        return bookService;
    }

    /**
     * Gets book request service.
     *
     * @return the book request service
     */
    public BookRequestService getBookRequestService() {
        return bookRequestService;
    }

    /**
     * Gets user report service.
     *
     * @return the user report service
     */
    public UserReportService getUserReportService() {
        return userReportService;
    }
}
