package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
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
 * Command that finds books by genre.
 *
 * @author Artur Mironchik
 */
public class FindBooksByGenre implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String genre = req.getParameter(RequestParameter.BOOK_GENRE);

        BookService service = ServiceFactory.getInstance().getBookService();
        CommandResult result = new CommandResult(CommandName.LOAD_BOOKS, CommandResult.Type.REDIRECT);

        try{
            List<Book> books = service.findBooksByGenre(genre);

            if(books.size() > 0){
                req.setAttribute(RequestParameter.BOOKS, books);
                result = new CommandResult(PagePath.BOOKS, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
