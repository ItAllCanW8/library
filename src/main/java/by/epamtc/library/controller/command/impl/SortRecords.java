package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
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
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.SortingHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command that sorts given records.
 *
 * @author Artur Mironchik
 */
public class SortRecords implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String sortingField = req.getParameter(RequestParameter.SORTING_FIELD);
        String sortingOrder = req.getParameter(RequestParameter.SORTING_ORDER);
        SortingHelper.SortingEntity sortingEntity = SortingHelper.SortingEntity.
                fromString(req.getParameter(RequestParameter.SORTING_ENTITY));
        CommandResult result = new CommandResult(CommandName.HOME_URL, CommandResult.Type.REDIRECT);

        if (sortingEntity != null) {
            try {
                switch (sortingEntity){
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
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return result;
    }
}