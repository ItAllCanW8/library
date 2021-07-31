package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.UserServiceImpl;
import by.epamtc.library.model.service.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangePassword implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String currentPassword = req.getParameter(RequestParameter.PASSWORD);
        String newPassword = req.getParameter(RequestParameter.NEW_PASSWORD);
        String repeatedNewPassword = req.getParameter(RequestParameter.REPEATED_PASSWORD);

        Map<String, String> fields = new HashMap<>();
        fields.put(RequestParameter.NEW_PASSWORD, newPassword);
        fields.put(RequestParameter.REPEATED_PASSWORD, repeatedNewPassword);

        CommandResult result = new CommandResult(CommandName.USER_PROFILE, CommandResult.Type.FORWARD);
        try {
            Optional<User> userOptional = service.login(user.getEmail(), currentPassword);
            if (userOptional.isPresent()) {
                if (service.changePassword(user.getId(), fields)) {
                    session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
                    result = new CommandResult(CommandName.USER_PROFILE, CommandResult.Type.REDIRECT);
                } else {
                    if (!UserValidator.isChangePasswordFormValid(fields)) {
                        req.setAttribute(RequestParameter.PASSWORD, currentPassword);
                        req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
                    }
                }
            } else {
                req.setAttribute(RequestParameter.NEW_PASSWORD, newPassword);
                req.setAttribute(RequestParameter.REPEATED_PASSWORD, repeatedNewPassword);
                req.setAttribute(JspAttribute.ERROR_INVALID_CURR_PASSWORD, JspAttribute.ERROR_INVALID_CURR_PASSWORD_MSG);
            }
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Error changing user password");
        }
        return result;
    }
}
