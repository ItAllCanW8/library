package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Command that changes book request state.
 *
 * @author Artur Mironchik
 */
public class ChangeBookRequestState implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String requestId = req.getParameter(RequestParameter.REQUEST_ID);
        String newRequestStateStr = req.getParameter(RequestParameter.REQUEST_STATE);
        String bookIdStr = req.getParameter(RequestParameter.BOOK_ID);
        String bookQuantityStr = req.getParameter(RequestParameter.BOOK_QUANTITY);

        CommandResult result = new CommandResult(CommandName.BOOK_REQUESTS, CommandResult.Type.REDIRECT);

        BookRequestService service = ServiceFactory.getInstance().getBookRequestService();
        try {
            if(service.changeRequestState(Long.parseLong(requestId), newRequestStateStr, Long.parseLong(bookIdStr),
                    Short.parseShort(bookQuantityStr))){
                MailSender mailSender = MailSender.getInstance();
                Optional<String> emailOptional = service.findEmailByRequestId(Long.parseLong(requestId));

                if(emailOptional.isPresent()){
                    if(BookRequestState.fromString(newRequestStateStr) == BookRequestState.APPROVED){
                        mailSender.setupLetter(emailOptional.get(), Message.LIBRARY_LETTER_SUBJECT,
                                Message.BOOK_REQUEST_APPROVED + bookIdStr);
                    } else
                        mailSender.setupLetter(emailOptional.get(), Message.LIBRARY_LETTER_SUBJECT,
                                Message.BOOK_REQUEST_DENIED + bookIdStr);

                    mailSender.send();
                }
            }
        } catch (NumberFormatException e){
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
            result = new CommandResult(CommandName.BOOK_REQUESTS, CommandResult.Type.FORWARD);
        }catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}