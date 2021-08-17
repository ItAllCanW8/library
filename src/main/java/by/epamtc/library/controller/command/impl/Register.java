package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.model.service.impl.UserServiceImpl;
import by.epamtc.library.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class Register implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String username = req.getParameter(RequestParameter.USERNAME);
        String email = req.getParameter(RequestParameter.EMAIL);
        String name = req.getParameter(RequestParameter.NAME);
        String surname = req.getParameter(RequestParameter.SURNAME);
        String dateOfBirth = req.getParameter(RequestParameter.DATE_OF_BIRTH);
        String phoneNumber = req.getParameter(RequestParameter.PHONE_NUMBER);
        String password = req.getParameter(RequestParameter.PASSWORD);
        String repeatedPassword = req.getParameter(RequestParameter.REPEATED_PASSWORD);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.USERNAME, username);
        fields.put(RequestParameter.NAME, name);
        fields.put(RequestParameter.SURNAME, surname);
        fields.put(RequestParameter.EMAIL, email);
        fields.put(RequestParameter.DATE_OF_BIRTH, dateOfBirth);
        fields.put(RequestParameter.PHONE_NUMBER, phoneNumber);
        fields.put(RequestParameter.PASSWORD, password);
        fields.put(RequestParameter.REPEATED_PASSWORD, repeatedPassword);

        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(ServletAttribute.LOGIN_URL, CommandResult.Type.REDIRECT);
        try {
            if (service.register(fields)) {
                HttpSession session = req.getSession();
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);

                MailSender mailSender = MailSender.getInstance();
                mailSender.setupLetter(email, Message.LIBRARY_LETTER_SUBJECT, Message.WELCOME_LETTER);
                mailSender.send();
            } else {
                req.setAttribute(RequestParameter.USERNAME, fields.get(RequestParameter.USERNAME));
                req.setAttribute(RequestParameter.NAME, fields.get(RequestParameter.NAME));
                req.setAttribute(RequestParameter.SURNAME, fields.get(RequestParameter.SURNAME));
                req.setAttribute(RequestParameter.DATE_OF_BIRTH, fields.get(RequestParameter.DATE_OF_BIRTH));
                req.setAttribute(RequestParameter.PHONE_NUMBER, fields.get(RequestParameter.PHONE_NUMBER));
                req.setAttribute(RequestParameter.EMAIL, fields.get(RequestParameter.EMAIL));
                req.setAttribute(RequestParameter.PASSWORD, fields.get(RequestParameter.PASSWORD));
                req.setAttribute(RequestParameter.REPEATED_PASSWORD, fields.get(RequestParameter.REPEATED_PASSWORD));
                req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
                result = new CommandResult(PagePath.REGISTER, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException("Error registering user");
        }
        return result;
    }
}
