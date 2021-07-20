package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class Register implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String surname = request.getParameter("surname");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("username", username);
        fields.put("firstName", firstName);
        fields.put("surname", surname);
        fields.put("email", email);
        fields.put("dateOfBirth", dateOfBirth);
        fields.put("phoneNumber", phoneNumber);
        fields.put("password", password);
        fields.put("repeatPassword", repeatedPassword);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(ServletAttribute.LOGIN_URL_PATTERN, CommandResult.Type.REDIRECT);
        try {
            if (service.register(fields)) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                request.setAttribute(RequestParameter.FIRST_NAME, fields.get(RequestParameter.FIRST_NAME));
                request.setAttribute(RequestParameter.LAST_NAME, fields.get(RequestParameter.LAST_NAME));
                request.setAttribute(RequestParameter.DATE_OF_BIRTH, fields.get(RequestParameter.DATE_OF_BIRTH));
                request.setAttribute(RequestParameter.PHONE_NUMBER, fields.get(RequestParameter.PHONE_NUMBER));
                request.setAttribute(RequestParameter.EMAIL, fields.get(RequestParameter.EMAIL));
                request.setAttribute(RequestParameter.PASSWORD, fields.get(RequestParameter.PASSWORD));
                request.setAttribute(RequestParameter.REPEATED_PASSWORD, fields.get(RequestParameter.REPEATED_PASSWORD));
                request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                result = new CommandResult(PagePath.REGISTER, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Couldn't register user");
            throw new CommandException(e);
        }
        return result;
    }
}
