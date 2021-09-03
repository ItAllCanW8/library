package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.Message;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Command that changes user status.
 *
 * @author Artur Mironchik
 */
public class ChangeUserStatus implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String userId = req.getParameter(RequestParameter.USER_ID);
        String newStatusStr = req.getParameter(RequestParameter.USER_STATUS);
        String username = req.getParameter(RequestParameter.USERNAME);

        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(CommandName.USERS, CommandResult.Type.REDIRECT);
        try {
            if(service.changeUserStatus(Long.parseLong(userId), newStatusStr)){
                Optional<String> emailOptional = service.findEmailById(Long.parseLong(userId));

                if(emailOptional.isPresent()){
                    MailSender mailSender = MailSender.getInstance();
                    mailSender.setupLetter(emailOptional.get(), Message.LIBRARY_LETTER_SUBJECT, Message.HELLO_PREFIX
                            + username + Message.STATUS_CHANGED_PREFIX + newStatusStr + Message.STATUS_CHANGED_POSTFIX);
                    mailSender.send();
                }
            }
        } catch (NumberFormatException e){
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
            result = new CommandResult(CommandName.USERS, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
