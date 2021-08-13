package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.BookRequestServiceImpl;
import by.epamtc.library.model.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReadingRoom implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long readerId = (long) session.getAttribute(SessionAttribute.USER_ID);

        BookRequestService bookRequestService = BookRequestServiceImpl.getInstance();
        CommandResult result = new CommandResult(PagePath.READING_ROOM, CommandResult.Type.FORWARD);
        try {
            List<BookRequest> bookRequests = bookRequestService.loadBookRequestsByReaderId(readerId);;
            if (bookRequests.size() > 0) {
                BookService bookService = BookServiceImpl.getInstance();
                List<Book> books = new ArrayList<>(bookRequests.size());

                for (BookRequest bookRequest : bookRequests) {
                    if(bookRequest.getState() != BookRequestState.CLOSED ) {
                        Optional<Book> bookOptional = bookService.findBookById(bookRequest.getBook().getId());

                        if (bookOptional.isPresent())
                            books.add(bookOptional.get());
                    }
                }
                req.setAttribute(RequestParameter.BOOKS, books);
            } else {
                req.setAttribute(JspAttribute.NO_BOOK_REQUESTS, JspAttribute.NO_BOOK_REQUESTS_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
