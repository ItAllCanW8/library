package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command that loads page with all books.
 *
 * @author Artur Mironchik
 */
public class LoadBooks implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        loadBooks(req);
        return new CommandResult(PagePath.BOOKS, CommandResult.Type.FORWARD);
    }

    static void loadBooks(HttpServletRequest req) throws CommandException {
        BookService service = ServiceFactory.getInstance().getBookService();
        try {
            List<Book> books = service.loadBooks();
            if (books.size() > 0) {
                req.setAttribute(RequestParameter.BOOKS, books);
            } else {
                req.setAttribute(JspAttribute.NO_BOOKS, JspAttribute.NO_BOOKS_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
