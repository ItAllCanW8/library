package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.LoggingNote;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoadLoggingNotes implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(PagePath.LOGGING_NOTES, CommandResult.Type.FORWARD);
        try {
            List<LoggingNote> loggingNotes = service.loadLoggingNotes();
            if (loggingNotes.size() > 0) {
                req.setAttribute(RequestParameter.LOGGING_NOTES, loggingNotes);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
