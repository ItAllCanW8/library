package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command that shows all book requests to librarian.
 *
 * @author Artur Mironchik
 */
public class BookRequests implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        CommandResult result = new CommandResult(PagePath.BOOK_REQUESTS, CommandResult.Type.FORWARD);
        try {
            List<BookRequest> bookRequests = service.loadBookRequests();
            if (bookRequests.size() > 0) {
                req.setAttribute(RequestParameter.BOOK_REQUESTS, bookRequests);
            } else {
                req.setAttribute(JspAttribute.NO_BOOK_REQUESTS, JspAttribute.NO_BOOK_REQUESTS_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}