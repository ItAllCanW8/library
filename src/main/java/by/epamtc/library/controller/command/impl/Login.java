package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserStatus;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Command that logins user to account.
 *
 * @author Artur Mironchik
 */
public class Login implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String email = req.getParameter(RequestParameter.EMAIL);
        String password = req.getParameter(RequestParameter.PASSWORD);

        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(PagePath.LOGIN, CommandResult.Type.FORWARD);
        try {
            Optional<User> userOptional = service.login(email, password);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (!user.getStatus().equals(UserStatus.DEACTIVATED)) {
                    HttpSession session = req.getSession();
                    session.setAttribute(SessionAttribute.USER, user);
                    session.setAttribute(SessionAttribute.CURRENT_ROLE, user.getRole());
                    session.setAttribute(SessionAttribute.USER_ID, user.getId());

                    result = new CommandResult(CommandName.USER_PROFILE, CommandResult.Type.REDIRECT);
                } else {
                    req.setAttribute(RequestParameter.EMAIL, email);
                    req.setAttribute(JspAttribute.ACCOUNT_IS_DEACTIVATED, JspAttribute.ACCOUNT_IS_DEACTIVATED_MSG);
                }
            } else {
                req.setAttribute(RequestParameter.EMAIL, email);
                req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
