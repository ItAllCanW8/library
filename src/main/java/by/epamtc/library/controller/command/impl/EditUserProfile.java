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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class EditUserProfile implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long userId = (long) session.getAttribute(SessionAttribute.USER_ID);
        String newUsername = req.getParameter(RequestParameter.USERNAME);
        String newName = req.getParameter(RequestParameter.NAME);
        String newSurname = req.getParameter(RequestParameter.SURNAME);
        String newDateOfBirth = req.getParameter(RequestParameter.DATE_OF_BIRTH);
        String newPhoneNumber = req.getParameter(RequestParameter.PHONE_NUMBER);
        String newEmail = req.getParameter(RequestParameter.EMAIL);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.USERNAME, newUsername);
        fields.put(RequestParameter.NAME, newName);
        fields.put(RequestParameter.SURNAME, newSurname);
        fields.put(RequestParameter.DATE_OF_BIRTH, newDateOfBirth);
        fields.put(RequestParameter.PHONE_NUMBER, newPhoneNumber);
        fields.put(RequestParameter.EMAIL, newEmail);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.LOAD_USER_PROFILE + userId, CommandResult.Type.REDIRECT);
        try {
            Optional<User> userOptional = service.updateProfile(userId, fields);
            System.out.println(userOptional);
            if (userOptional.isPresent()) {
                System.out.println(userOptional.get());
                session.setAttribute(SessionAttribute.USER, userOptional.get());
            } else {
                if (!service.isEmailAvailable(newEmail)) {
                    req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.EMAIL_AVAILABLE_ERROR_MSG);
                } else {
                    req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
                }
            }
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Error editing user profile");
        }
        return result;
    }
}