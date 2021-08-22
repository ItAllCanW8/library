package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.factory.ServiceFactory;

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

        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(CommandName.LOAD_USER_PROFILE + userId, CommandResult.Type.REDIRECT);
        try {
            Optional<User> userOptional = service.updateProfile(userId, fields);

            if (userOptional.isPresent()) {
                session.setAttribute(SessionAttribute.USER, userOptional.get());
            } else {
                if (!service.isEmailAvailable(newEmail)) {
                    req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.EMAIL_AVAILABLE_ERROR_MSG);
                }
                else if (!service.isPhoneNumAvailable(newPhoneNumber)) {
                    req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.PHONE_AVAILABLE_ERROR_MSG);
                } else {
                    req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
                }
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}