package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
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
import java.util.Optional;

public class LoadBookInfo implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String bookId = req.getParameter(RequestParameter.BOOK_ID);
        BookService service = BookServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.LOAD_BOOKS, CommandResult.Type.FORWARD);
        try {
            try {
                Optional<Book> bookOptional = service.findBookById(Long.parseLong(bookId));
                if (bookOptional.isPresent()) {
                    Book book = bookOptional.get();
//                    if (book.getIsAvailable()) {
                    req.setAttribute(RequestParameter.BOOK, book);
                    result = new CommandResult(PagePath.BOOK_INFO, CommandResult.Type.FORWARD);
//                    } else {
//                        req.setAttribute(JspAttribute.NO_VACANCY_ATTRIBUTE, JspAttribute.NO_VACANCY_MESSAGE);
//                    }
                } else {
                    req.setAttribute(JspAttribute.NO_BOOK, JspAttribute.NO_BOOK_MSG);
                }
            } catch (NumberFormatException e) {
                req.setAttribute(JspAttribute.ERROR_INPUT_DATA, JspAttribute.ERROR_INPUT_DATA_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}