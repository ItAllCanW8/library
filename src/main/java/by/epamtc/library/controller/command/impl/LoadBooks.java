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
import by.epamtc.library.model.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoadBooks implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        BookService service = BookServiceImpl.getInstance();
        CommandResult result = new CommandResult(PagePath.BOOKS, CommandResult.Type.FORWARD);
        try {
            List<Book> books = service.loadPopularBooks();
            if (books.size() > 0) {
                req.setAttribute(RequestParameter.BOOKS, books);
            } else {
                req.setAttribute(JspAttribute.NO_BOOKS, JspAttribute.NO_BOOKS_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
