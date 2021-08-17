package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.model.service.impl.BookRequestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeBookRequestState implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String requestId = req.getParameter(RequestParameter.REQUEST_ID);
        String newRequestStateStr = req.getParameter(RequestParameter.REQUEST_STATE);

        CommandResult result = new CommandResult(CommandName.BOOK_REQUESTS, CommandResult.Type.REDIRECT);

        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        try {
            if(service.changeRequestState(Long.parseLong(requestId), newRequestStateStr)){
                if(BookRequestState.fromString(newRequestStateStr) == BookRequestState.APPROVED){

                }
            } else
                result = new CommandResult(PagePath.ERROR_PAGE, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return result;
    }
}