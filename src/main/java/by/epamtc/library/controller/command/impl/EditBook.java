package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.BookServiceImpl;
import by.epamtc.library.model.service.validation.BookValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class EditBook implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String bookId = req.getParameter(RequestParameter.BOOK_ID);

        String newTitle = req.getParameter(RequestParameter.BOOK_TITLE);
        String newAuthor = req.getParameter(RequestParameter.BOOK_AUTHOR);
        String newIsbn = req.getParameter(RequestParameter.BOOK_ISBN);
        String newQuantity = req.getParameter(RequestParameter.BOOK_QUANTITY);
        String newGenre = req.getParameter(RequestParameter.BOOK_GENRE);
        String newDescription = req.getParameter(RequestParameter.BOOK_DESCRIPTION);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.BOOK_TITLE, newTitle);
        fields.put(RequestParameter.BOOK_AUTHOR, newAuthor);
        fields.put(RequestParameter.BOOK_ISBN, newIsbn);
        fields.put(RequestParameter.BOOK_QUANTITY, newQuantity);
        fields.put(RequestParameter.BOOK_GENRE, newGenre);
        fields.put(RequestParameter.BOOK_DESCRIPTION, newDescription);

        BookService service = BookServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.LOAD_BOOK_INFO + bookId, CommandResult.Type.REDIRECT);
        try {
            if (!service.updateBook(Long.parseLong(bookId), fields)) {
                if (!BookValidator.isBookFormValid(fields)) {
                    req.setAttribute(RequestParameter.BOOK_TITLE, fields.get(RequestParameter.BOOK_TITLE));
                    req.setAttribute(RequestParameter.BOOK_AUTHOR, fields.get(RequestParameter.BOOK_AUTHOR));
                    req.setAttribute(RequestParameter.BOOK_ISBN, fields.get(RequestParameter.BOOK_ISBN));
                    req.setAttribute(RequestParameter.BOOK_QUANTITY, fields.get(RequestParameter.BOOK_QUANTITY));
                    req.setAttribute(RequestParameter.BOOK_GENRE, fields.get(RequestParameter.BOOK_GENRE));
                    req.setAttribute(RequestParameter.BOOK_DESCRIPTION, fields.get(RequestParameter.BOOK_DESCRIPTION));
                    req.setAttribute(JspAttribute.ERROR_BOOK_UPDATING, JspAttribute.ERROR_INPUT_DATA_MSG);
                } else {
                    req.setAttribute(JspAttribute.ERROR_BOOK_UPDATING, JspAttribute.ERROR_BOOK_DUPLICATE_MSG);
                }
                result = new CommandResult(CommandName.LOAD_BOOK_INFO + bookId, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
