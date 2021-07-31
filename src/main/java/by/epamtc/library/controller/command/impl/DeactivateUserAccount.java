package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class DeactivateUserAccount implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String currentPassword = req.getParameter(RequestParameter.PASSWORD);
        CommandResult result = new CommandResult(ServletAttribute.HOME_URL, CommandResult.Type.REDIRECT);
        try {
            Optional<User> userOptional = service.login(user.getEmail(), currentPassword);
            if (userOptional.isPresent()) {
                service.deactivateUser(user.getId());
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                req.setAttribute(JspAttribute.ERROR_INVALID_CURR_PASSWORD, JspAttribute.ERROR_INVALID_CURR_PASSWORD_MSG);
                result = new CommandResult(CommandName.USER_PROFILE, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException | NumberFormatException e) {
//            logger.log(Level.ERROR, "Couldn't delete user account");
            throw new CommandException(e);
        }
        return result;
    }
}
