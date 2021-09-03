package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.ServiceFactory;
import by.epamtc.library.util.FileHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Command that deletes a book.
 *
 * @author Artur Mironchik
 */
public class DeleteBook implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String bookId = req.getParameter(RequestParameter.BOOK_ID);

        BookService service = ServiceFactory.getInstance().getBookService();
        CommandResult result = new CommandResult(CommandName.TO_LIBRARIAN_BOOKS, CommandResult.Type.REDIRECT);
        try {
            Optional<Book> bookOptional = service.findById(Long.parseLong(bookId));
            Book book = bookOptional.get();

            if(service.deleteBook(Long.parseLong(bookId))){
                FileHandler.deleteBookFiles(book.getImg(), book.getAuthorImg(), book.getPdf());
            } else {
                req.setAttribute(JspAttribute.ERROR_DELETING_BOOK, JspAttribute.ERROR_DELETING_BOOK_MSG);
            }
        } catch (NumberFormatException e) {
            req.setAttribute(JspAttribute.ERROR_DELETING_BOOK, JspAttribute.ERROR_DELETING_BOOK_MSG);
        }catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}