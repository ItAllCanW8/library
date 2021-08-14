package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.BookRequestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookRequest implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        long requestId = Long.parseLong(req.getParameter(RequestParameter.REQUEST_ID));
        BookRequestService service = BookRequestServiceImpl.getInstance();

        try {
            if(service.deleteBookRequest(requestId))
                req.setAttribute(SessionAttribute.SUCCESS_MESSAGE, true);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new CommandResult(CommandName.BOOK_REQUESTS, CommandResult.Type.REDIRECT);
    }
}
