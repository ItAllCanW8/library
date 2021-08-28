package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Command that loads user book requests page.
 *
 * @author Artur Mironchik
 */
public class MyBookRequests implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long readerId = (long) session.getAttribute(SessionAttribute.USER_ID);

        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        CommandResult result = new CommandResult(PagePath.READER_BOOK_REQUESTS, CommandResult.Type.FORWARD);
        try {
            req.setAttribute(RequestParameter.MAX_NUM_OF_BOOK_REQUESTS_FOR_SUB, service.loadMaxSubBooksCoeff());

            List<BookRequest> bookRequests = service.loadBookRequestsByReaderId(readerId);
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
