package by.epamtc.library.model.service.factory;

import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.BookRequestServiceImpl;
import by.epamtc.library.model.service.impl.BookServiceImpl;
import by.epamtc.library.model.service.impl.UserServiceImpl;

public class ServiceFactory {
    private final UserService userService = new UserServiceImpl();
    private final BookService bookService = new BookServiceImpl();
    private final BookRequestService bookRequestService = new BookRequestServiceImpl();

    private ServiceFactory() {
    }

    private static class Holder {
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public BookRequestService getBookRequestService() {
        return bookRequestService;
    }
}
