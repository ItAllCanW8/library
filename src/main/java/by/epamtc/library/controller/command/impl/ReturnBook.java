package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.BookRequestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReturnBook implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        long requestId = Long.parseLong(req.getParameter(RequestParameter.REQUEST_ID));
        long bookId = Long.parseLong(req.getParameter(RequestParameter.BOOK_ID));
        BookRequestType requestType = BookRequestType.fromString(req.getParameter(RequestParameter.BOOK_REQUEST_TYPE));
        CommandResult result = new CommandResult(CommandName.READER_BOOK_REQUESTS, CommandResult.Type.REDIRECT);

        BookRequestService service = BookRequestServiceImpl.getInstance();
        try {
            if(!service.closeBookRequest(requestId, bookId, requestType))
                result = new CommandResult(PagePath.ERROR_PAGE, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return result;
    }
}