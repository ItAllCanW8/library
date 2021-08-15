package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.util.SortingHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortRecords implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String sortingField = req.getParameter(RequestParameter.SORTING_FIELD);
        String sortingOrder = req.getParameter(RequestParameter.SORTING_ORDER);
        SortingHelper.SortingObject sortingObject = SortingHelper.SortingObject.
                fromString(req.getParameter(RequestParameter.SORTING_OBJECT));

        CommandResult result = new CommandResult(CommandName.LOAD_BOOKS, CommandResult.Type.FORWARD);

        if(sortingObject != null && sortingObject.equals(SortingHelper.SortingObject.BOOKS)){
            try {
                BookService bookService = ServiceFactory.getInstance().getBookService();
                bookService.sort(sortingField, sortingOrder);

                result = new CommandResult(CommandName.LOAD_BOOKS, CommandResult.Type.REDIRECT);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return result;
    }
}
