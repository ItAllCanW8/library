package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Register implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");

        LOGGER.info(email);
        LOGGER.info(username);
        LOGGER.info(name);
        LOGGER.info(surname);
        LOGGER.info(dateOfBirth);
        LOGGER.info(phoneNumber);
        LOGGER.info(LocalDate.parse(dateOfBirth));

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("username", username);
        fields.put("name", name);
        fields.put("surname", surname);
        fields.put("email", email);
        fields.put("dateOfBirth", dateOfBirth);
        fields.put("phoneNumber", phoneNumber);
        fields.put("password", password);
        fields.put("repeatPassword", repeatedPassword);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult("/login", CommandResult.Type.REDIRECT);
        try {
            if (service.register(fields)) {
                HttpSession session = request.getSession();
                session.setAttribute("successMessage", Boolean.TRUE);
            } else {
                request.setAttribute("username", fields.get("username"));
                request.setAttribute("name", fields.get("name"));
                request.setAttribute("surname", fields.get("surname"));
                request.setAttribute("dateOfBirth", fields.get("dateOfBirth"));
                request.setAttribute("phoneNumber", fields.get("phoneNumber"));
                request.setAttribute("email", fields.get("email"));
                request.setAttribute("password", fields.get("password"));
                request.setAttribute("repeatPassword", fields.get("repeatPassword"));
//                request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MESSAGE);
                result = new CommandResult("/WEB-INF/jsp/authorization/register.jsp", CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Couldn't register user");
            throw new CommandException(e);
        }
        return result;
    }
}
