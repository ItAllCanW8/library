package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Command that deactivates user account.
 *
 * @author Artur Mironchik
 */
public class DeactivateAccount implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        UserService service = ServiceFactory.getInstance().getUserService();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String currentPassword = req.getParameter(RequestParameter.PASSWORD);
        CommandResult result = new CommandResult(CommandName.USER_PROFILE, CommandResult.Type.FORWARD);

        try {
            Optional<User> userOptional = service.login(user.getEmail(), currentPassword);
            if (userOptional.isPresent()) {
                if(service.deactivateUser(user.getId())){
                    MailSender mailSender = MailSender.getInstance();
                    mailSender.setupLetter(user.getEmail(), Message.LIBRARY_LETTER_SUBJECT,Message.HELLO_PREFIX
                            + user.getUsername() + Message.DEACTIVATION);
                    mailSender.send();

                    session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
                    result = new CommandResult(CommandName.LOGOUT, CommandResult.Type.REDIRECT);
                }
            } else {
                req.setAttribute(JspAttribute.ERROR_INVALID_CURR_PASSWORD, JspAttribute.ERROR_INVALID_CURR_PASSWORD_MSG);
            }
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
