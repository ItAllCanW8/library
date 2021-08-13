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

public class FindBooksByKeyword implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String keyword = req.getParameter(RequestParameter.SEARCH_KEYWORD);
        BookService service = BookServiceImpl.getInstance();

        try{
            List<Book> books = service.findBooksByKeyword(keyword);

            if(books.size() == 0) {
                req.setAttribute(JspAttribute.NO_BOOKS, JspAttribute.NO_BOOKS_MSG);
            }
            req.setAttribute(RequestParameter.BOOKS, books);
            req.setAttribute(RequestParameter.SEARCH_KEYWORD, keyword);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new CommandResult(PagePath.BOOKS, CommandResult.Type.FORWARD);
    }
}
