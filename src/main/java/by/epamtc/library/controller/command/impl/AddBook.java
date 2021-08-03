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
import by.epamtc.library.model.service.impl.BookServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddBook implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long userId = (long) session.getAttribute(SessionAttribute.USER_ID);
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

        BookService service = BookServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.TO_LIBRARIAN_BOOKS, CommandResult.Type.REDIRECT);
        try {
            if (service.addBook(fields)) {
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                if (BookValidator.isBookFormValid(fields)) {
                    req.setAttribute(JspAttribute.ERROR_DUPLICATE_ATTRIBUTE, JspAttribute.ERROR_VACANCY_DUPLICATE_MESSAGE);
                } else {
                    req.setAttribute(RequestParameter.POSITION, fields.get(RequestParameter.POSITION));
                    req.setAttribute(RequestParameter.DESCRIPTION, fields.get(RequestParameter.DESCRIPTION));
                    req.setAttribute(RequestParameter.COUNTRY, fields.get(RequestParameter.COUNTRY));
                    req.setAttribute(RequestParameter.CITY, fields.get(RequestParameter.CITY));
                    req.setAttribute(JspAttribute.ERROR_VACANCY_CREATION_ATTRIBUTE, JspAttribute.ERROR_VACANCY_CREATION_MESSAGE);
                }
                result = new CommandResult(CommandName.TO_LIBRARIAN_BOOKS, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Couldn't create vacancy");
            throw new CommandException(e);
        }
        return result;
    }
}
