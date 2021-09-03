package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command that finds users by role.
 *
 * @author Artur Mironchik
 */
public class FindUsersByRole implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String role = req.getParameter(RequestParameter.USER_ROLE);

        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(CommandName.USERS, CommandResult.Type.REDIRECT);

        try{
            List<User> users = service.findUsersByRole(role);

            if(users.size() > 0){
                req.setAttribute(RequestParameter.USERS, users);
                result = new CommandResult(PagePath.USERS, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
