package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Login implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String email = req.getParameter(RequestParameter.EMAIL);
        String password = req.getParameter(RequestParameter.PASSWORD);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(PagePath.LOGIN, CommandResult.Type.FORWARD);
        try {
            Optional<User> userOptional = service.login(email, password);
            LOGGER.info(userOptional);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                LOGGER.info(user);
                if (user.getStatus().equals("active")) {
                    HttpSession session = req.getSession();
                    session.setAttribute(SessionAttribute.USER, user);
                    session.setAttribute(SessionAttribute.CURRENT_ROLE, user.getRole());
                    session.setAttribute(SessionAttribute.USER_ID, user.getId());

                    LOGGER.info(user);

                    result = new CommandResult(ServletAttribute.HOME_URL, CommandResult.Type.REDIRECT);
                } else {
                    req.setAttribute(RequestParameter.EMAIL, email);
                    req.setAttribute(JspAttribute.ACCOUNT_IS_DEACTIVATED, JspAttribute.ACCOUNT_IS_DEACTIVATED_MSG);
                }
            } else {
                req.setAttribute(RequestParameter.EMAIL, email);
                req.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, JspAttribute.ERROR_INPUT_DATA_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
