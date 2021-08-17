package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindBookRequestsByState implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String state = req.getParameter(RequestParameter.REQUEST_STATE);

        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        CommandResult result = new CommandResult(CommandName.BOOK_REQUESTS, CommandResult.Type.REDIRECT);

        try{
            List<BookRequest> bookRequests = service.findBookRequestsByState(state);

            if(bookRequests.size() > 0){
                req.setAttribute(RequestParameter.BOOK_REQUESTS, bookRequests);
                result = new CommandResult(PagePath.BOOK_REQUESTS, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
