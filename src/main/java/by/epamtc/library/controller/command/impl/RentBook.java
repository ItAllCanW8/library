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
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.BookRequestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;


public class RentBook implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        User reader = (User) session.getAttribute(SessionAttribute.USER);
        String bookId = req.getParameter(RequestParameter.BOOK_ID);
        String rentMethod = req.getParameter(RequestParameter.BOOK_REQUEST_TYPE);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.BOOK_REQUEST_TYPE, rentMethod);
        fields.put(RequestParameter.BOOK_ID, bookId);

        CommandResult result = new CommandResult(CommandName.LOAD_BOOK_INFO + bookId, CommandResult.Type.REDIRECT);
        BookRequestService service = BookRequestServiceImpl.getInstance();
        try {
            if (service.createBookRequest(fields, reader)) {
//                MailSender mailSender = MailSender.MailSenderHolder.HOLDER.getMailSender();
//                String applicantEmail = applicant.getEmail();
//                mailSender.setupEmail(applicantEmail, MailMessage.HR_SYSTEM_MAIL_SUBJECT, MailMessage.CREATION_APPLICANT_REQUEST_MAIL_TEXT);
//                mailSender.send();
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                req.setAttribute(JspAttribute.ERROR_REQUEST_CREATION, JspAttribute.ERROR_REQUEST_CREATION_MSG);
                result = new CommandResult(CommandName.LOAD_BOOK_INFO + bookId, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return result;
    }
}