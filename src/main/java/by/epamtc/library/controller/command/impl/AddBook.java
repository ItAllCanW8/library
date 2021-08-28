package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Command that adds a book.
 *
 * @author Artur Mironchik
 */
public class AddBook implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();

        String title = req.getParameter(RequestParameter.BOOK_TITLE);
        String author = req.getParameter(RequestParameter.BOOK_AUTHOR);
        String isbn = req.getParameter(RequestParameter.BOOK_ISBN);
        String quantity = req.getParameter(RequestParameter.BOOK_QUANTITY);
        String genre = req.getParameter(RequestParameter.BOOK_GENRE);
        String description = req.getParameter(RequestParameter.BOOK_DESCRIPTION);

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put(RequestParameter.BOOK_TITLE, title);
        fields.put(RequestParameter.BOOK_AUTHOR, author);
        fields.put(RequestParameter.BOOK_ISBN, isbn);
        fields.put(RequestParameter.BOOK_QUANTITY, quantity);
        fields.put(RequestParameter.BOOK_GENRE, genre);
        fields.put(RequestParameter.BOOK_DESCRIPTION, description);

        BookService service = ServiceFactory.getInstance().getBookService();
        CommandResult result = new CommandResult(CommandName.TO_LIBRARIAN_BOOKS, CommandResult.Type.REDIRECT);
        try {
            if (service.addBook(fields)) {
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                req.setAttribute(RequestParameter.BOOK_TITLE, fields.get(RequestParameter.BOOK_TITLE));
                req.setAttribute(RequestParameter.BOOK_AUTHOR, fields.get(RequestParameter.BOOK_AUTHOR));
                req.setAttribute(RequestParameter.BOOK_GENRE, fields.get(RequestParameter.BOOK_GENRE));
                req.setAttribute(RequestParameter.BOOK_QUANTITY, fields.get(RequestParameter.BOOK_QUANTITY));
                req.setAttribute(RequestParameter.BOOK_DESCRIPTION, fields.get(RequestParameter.BOOK_DESCRIPTION));
                req.setAttribute(RequestParameter.BOOK_ISBN, fields.get(RequestParameter.BOOK_ISBN));
                req.setAttribute(JspAttribute.ERROR_BOOK_CREATION, JspAttribute.ERROR_BOOK_CREATION_MSG);

                result = new CommandResult(CommandName.TO_LIBRARIAN_BOOKS, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
