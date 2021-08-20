package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.ServletAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.UserReportService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.util.SortingHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SortRecords implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String sortingField = req.getParameter(RequestParameter.SORTING_FIELD);
        String sortingOrder = req.getParameter(RequestParameter.SORTING_ORDER);
        SortingHelper.SortingObject sortingObject = SortingHelper.SortingObject.
                fromString(req.getParameter(RequestParameter.SORTING_OBJECT));
        CommandResult result = new CommandResult(ServletAttribute.HOME_URL, CommandResult.Type.REDIRECT);

        if (sortingObject != null) {
            try {
                switch (sortingObject){
                    case BOOKS:
                        BookService bookService = ServiceFactory.getInstance().getBookService();
                        List<Book> books = bookService.sort(sortingField, sortingOrder);

                        if (books.size() > 0) {
                            req.setAttribute(RequestParameter.BOOKS, books);
                            result = new CommandResult(PagePath.BOOKS, CommandResult.Type.FORWARD);
                        }
                        break;
                    case BOOK_REQUESTS:
                        BookRequestService bookRequestService = ServiceFactory.getInstance().getBookRequestService();
                        List<BookRequest> bookRequests = bookRequestService.sort(sortingField, sortingOrder);

                        if (bookRequests.size() > 0) {
                            req.setAttribute(RequestParameter.BOOK_REQUESTS, bookRequests);
                            result = new CommandResult(PagePath.BOOK_REQUESTS, CommandResult.Type.FORWARD);
                        }
                        break;
                    case USER_REPORTS:
                        UserReportService userReportService = ServiceFactory.getInstance().getUserReportService();
                        List<UserReport> userReports = userReportService.sort(sortingField, sortingOrder);

                        if (userReports.size() > 0) {
                            req.setAttribute(RequestParameter.USER_REPORTS, userReports);
                            result = new CommandResult(PagePath.USER_REPORTS, CommandResult.Type.FORWARD);
                        }
                        break;
                }

//                if (sortingObject.equals(SortingHelper.SortingObject.BOOKS)) {
//                    BookService bookService = ServiceFactory.getInstance().getBookService();
//                    List<Book> books = bookService.sort(sortingField, sortingOrder);
//
//                    if (books.size() > 0) {
//                        req.setAttribute(RequestParameter.BOOKS, books);
//                        result = new CommandResult(PagePath.BOOKS, CommandResult.Type.FORWARD);
//                    }
//                } else if (sortingObject.equals(SortingHelper.SortingObject.BOOK_REQUESTS)){
//                    BookRequestService bookRequestService = ServiceFactory.getInstance().getBookRequestService();
//                    List<BookRequest> bookRequests = bookRequestService.sort(sortingField, sortingOrder);
//
//                    if (bookRequests.size() > 0) {
//                        req.setAttribute(RequestParameter.BOOK_REQUESTS, bookRequests);
//                        result = new CommandResult(PagePath.BOOK_REQUESTS, CommandResult.Type.FORWARD);
//                    }
//                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return result;
    }
}
