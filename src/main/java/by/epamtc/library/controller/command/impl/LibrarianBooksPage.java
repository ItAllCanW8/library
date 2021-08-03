package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.*;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.impl.BookServiceImpl;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LibrarianBooksPage implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        BookService service = BookServiceImpl.getInstance();

        try {
            List<Book> books = service.loadBooks();
            if (books.size() > 0) {
                req.setAttribute(RequestParameter.BOOKS, books);
            } else {
                HttpSession session = req.getSession();
                session.removeAttribute(SessionAttribute.BOOKS);
                req.setAttribute(JspAttribute.NO_BOOKS, JspAttribute.NO_BOOKS_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new CommandResult(PagePath.LIBRARIAN_BOOKS, CommandResult.Type.FORWARD);

//        HttpSession session = req.getSession();
//        long employeeId = (long) session.getAttribute(SessionAttribute.USER_ID);
//        String position = req.getParameter(RequestParameter.POSITION);
//        String description = req.getParameter(RequestParameter.DESCRIPTION);
//        String country = req.getParameter(RequestParameter.COUNTRY);
//        String city = req.getParameter(RequestParameter.CITY);
//
//        Map<String, String> fields = new LinkedHashMap<>();
//        fields.put(RequestParameter.POSITION, position);
//        fields.put(RequestParameter.DESCRIPTION, description);
//        fields.put(RequestParameter.COUNTRY, country);
//        fields.put(RequestParameter.CITY, city);
//
//        BookService service = BookServiceImpl.getInstance();
//        CommandResult result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.REDIRECT);
//        try {
//            if (service.createVacancy(fields, employeeId)) {
//                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
//            } else {
//                if (VacancyValidator.isVacancyFormValid(fields)) {
//                    req.setAttribute(JspAttribute.ERROR_DUPLICATE_ATTRIBUTE, JspAttribute.ERROR_VACANCY_DUPLICATE_MESSAGE);
//                } else {
//                    req.setAttribute(RequestParameter.POSITION, fields.get(RequestParameter.POSITION));
//                    req.setAttribute(RequestParameter.DESCRIPTION, fields.get(RequestParameter.DESCRIPTION));
//                    req.setAttribute(RequestParameter.COUNTRY, fields.get(RequestParameter.COUNTRY));
//                    req.setAttribute(RequestParameter.CITY, fields.get(RequestParameter.CITY));
//                    req.setAttribute(JspAttribute.ERROR_VACANCY_CREATION_ATTRIBUTE, JspAttribute.ERROR_VACANCY_CREATION_MESSAGE);
//                }
//                result = new CommandResult(CommandName.TO_EMPLOYEE_VACANCIES, CommandResult.Type.FORWARD);
//            }
//        } catch (ServiceException e) {
//            logger.log(Level.ERROR, "Couldn't create vacancy");
//            throw new CommandException(e);
//        }
//        return result;
    }
}
