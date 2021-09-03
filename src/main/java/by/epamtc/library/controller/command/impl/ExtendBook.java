package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExtendBook implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String requestId = req.getParameter(RequestParameter.REQUEST_ID);
        String expectedReturnDate = req.getParameter(RequestParameter.EXPECTED_RETURN_DATE);

        try{
            BookRequestService bookRequestService = ServiceFactory.getInstance().getBookRequestService();

            String extensionDaysCoeffStr = bookRequestService.loadExtensionDaysCoeff();

            if(extensionDaysCoeffStr != null){
                bookRequestService.extendBookRequest(Long.parseLong(requestId), expectedReturnDate,
                        Long.parseLong(extensionDaysCoeffStr));
            }
        } catch (NumberFormatException e){
            req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
        }
        catch (ServiceException e){
            throw new CommandException(e);
        }

        return new CommandResult(CommandName.READER_BOOK_REQUESTS, CommandResult.Type.REDIRECT);
    }
}
