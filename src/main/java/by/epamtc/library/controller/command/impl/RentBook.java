package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.util.mail.MailSender;

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
        String rentMethod = req.getParameter(RequestParameter.REQUEST_TYPE);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.REQUEST_TYPE, rentMethod);
        fields.put(RequestParameter.BOOK_ID, bookId);

        CommandResult result = new CommandResult(CommandName.READER_BOOK_REQUESTS, CommandResult.Type.REDIRECT);
        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        try {
            if (service.createBookRequest(fields, reader)) {
                if(BookRequestType.fromString(rentMethod) == BookRequestType.FOR_SUBSCRIPTION){
                    MailSender mailSender = MailSender.getInstance();
                    mailSender.setupLetter(reader.getEmail(), Message.LIBRARY_LETTER_SUBJECT, Message.BOOK_REQUEST_CREATED
                    + bookId);
                    mailSender.send();
                }
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
