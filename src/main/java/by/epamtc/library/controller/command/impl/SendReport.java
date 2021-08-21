package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserReportService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class SendReport implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long userId = (long) session.getAttribute(SessionAttribute.USER_ID);
        String subject = req.getParameter(RequestParameter.USER_REPORT_SUBJECT);
        String comment = req.getParameter(RequestParameter.USER_REPORT_MESSAGE);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.USER_REPORT_SUBJECT, subject);
        fields.put(RequestParameter.USER_REPORT_MESSAGE, comment);

        UserReportService service = ServiceFactory.getInstance().getUserReportService();
        CommandResult result = new CommandResult(PagePath.CONTACT, CommandResult.Type.FORWARD);
        try {
            if (service.createUserReport(fields, userId)) {
                MailSender mailSender = MailSender.getInstance();
                User user = (User) session.getAttribute(SessionAttribute.USER);

                mailSender.setupLetter(user.getEmail(), Message.LIBRARY_LETTER_SUBJECT, Message.HELLO_PREFIX
                        + user.getUsername() + Message.USER_REPORT_CREATION);
                mailSender.send();

                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);

                result = new CommandResult(ServletAttribute.CONTACT_URL, CommandResult.Type.REDIRECT);
            } else {
                req.setAttribute(RequestParameter.USER_REPORT_SUBJECT, fields.get(RequestParameter.USER_REPORT_SUBJECT));
                req.setAttribute(RequestParameter.USER_REPORT_MESSAGE, fields.get(RequestParameter.USER_REPORT_MESSAGE));
                req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
