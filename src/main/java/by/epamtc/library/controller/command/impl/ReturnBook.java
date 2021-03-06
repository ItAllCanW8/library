package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command that closes book request.
 *
 * @author Artur Mironchik
 */
public class ReturnBook implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long userId = (long) session.getAttribute(SessionAttribute.USER_ID);

        String requestIdStr = req.getParameter(RequestParameter.REQUEST_ID);
        String bookIdStr = req.getParameter(RequestParameter.BOOK_ID);
        String bookQuantityStr = req.getParameter(RequestParameter.BOOK_QUANTITY);
        String expectedReturnDate = req.getParameter(RequestParameter.EXPECTED_RETURN_DATE);

        BookRequestType requestType = BookRequestType.fromString(req.getParameter(RequestParameter.REQUEST_TYPE));
        CommandResult result = new CommandResult(CommandName.READER_BOOK_REQUESTS, CommandResult.Type.REDIRECT);

        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        try {
            service.closeBookRequest(userId,Long.parseLong(requestIdStr), Long.parseLong(bookIdStr),
                    Short.parseShort(bookQuantityStr), requestType, expectedReturnDate);
        } catch (NumberFormatException e) {
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
            result = new CommandResult(CommandName.READER_BOOK_REQUESTS, CommandResult.Type.FORWARD);
        }catch (ServiceException e) {
            throw new CommandException(e);
        }

        return result;
    }
}