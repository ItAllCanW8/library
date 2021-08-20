package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.Message;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangeBookRequestState implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String requestId = req.getParameter(RequestParameter.REQUEST_ID);
        String newRequestStateStr = req.getParameter(RequestParameter.REQUEST_STATE);

        CommandResult result = new CommandResult(CommandName.BOOK_REQUESTS, CommandResult.Type.REDIRECT);

        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        try {
            if(service.changeRequestState(Long.parseLong(requestId), newRequestStateStr)){
                MailSender mailSender = MailSender.getInstance();
                Optional<String> emailOptional = service.findEmailByRequestId(Long.parseLong(requestId));

                if(emailOptional.isPresent()){
                    String bookId = req.getParameter(RequestParameter.BOOK_ID);

                    if(BookRequestState.fromString(newRequestStateStr) == BookRequestState.APPROVED){
                        mailSender.setupLetter(emailOptional.get(), Message.LIBRARY_LETTER_SUBJECT,
                                Message.BOOK_REQUEST_APPROVED + bookId);
                    } else
                        mailSender.setupLetter(emailOptional.get(), Message.LIBRARY_LETTER_SUBJECT,
                                Message.BOOK_REQUEST_DENIED + bookId);

                    mailSender.send();
                }
            } else
                result = new CommandResult(PagePath.ERROR, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return result;
    }
}