package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
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
import java.util.Optional;

/**
 * Command that loads user profile page by id.
 *
 * @author Artur Mironchik
 */
public class LoadUserProfileById implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String userId = req.getParameter(RequestParameter.USER_ID);
        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(CommandName.USERS, CommandResult.Type.FORWARD);
        try {
            Optional<User> userOptional = service.findById(Long.parseLong(userId));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                req.setAttribute(RequestParameter.USER, user);
                result = new CommandResult(PagePath.USER_PROFILE, CommandResult.Type.FORWARD);
            }
        } catch (NumberFormatException e) {
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
