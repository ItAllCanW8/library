package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.service.BookRequestService;
import by.epamtc.library.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class ReadingRoom implements Command {
    private static final String readingRoomOpeningCol = "reading_room_opening";
    private static final String readingRoomClosingCol = "reading_room_closing";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        long readerId = (long) session.getAttribute(SessionAttribute.USER_ID);

        BookRequestService bookRequestService = ServiceFactory.getInstance().getBookRequestService();
        CommandResult result = new CommandResult(PagePath.READING_ROOM, CommandResult.Type.FORWARD);
        try {
            boolean isReadingRoomOpened = isReadingRoomOpened(req, bookRequestService);

            List<BookRequest> bookRequests = bookRequestService.loadReadingRoomByReaderId(readerId,isReadingRoomOpened);

            if(!isReadingRoomOpened){
                req.setAttribute(JspAttribute.NO_BOOK_REQUESTS, JspAttribute.READING_ROOM_CLOSED_MSG);
            }
            else if (bookRequests.size() > 0) {
                req.setAttribute(RequestParameter.BOOK_REQUESTS, bookRequests);
            } else {
                req.setAttribute(JspAttribute.NO_BOOK_REQUESTS, JspAttribute.NO_BOOK_REQUESTS_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }

    static boolean isReadingRoomOpened(HttpServletRequest req, BookRequestService bookRequestService) throws ServiceException {
        Map<String, String> workingHours = bookRequestService.loadRRWorkingHours();
        LocalTime opening = LocalTime.parse(workingHours.get(readingRoomOpeningCol));
        LocalTime closing = LocalTime.parse(workingHours.get(readingRoomClosingCol));
        LocalTime now = LocalTime.now();

        req.setAttribute(RequestParameter.READING_ROOM_OPENING, opening);
        req.setAttribute(RequestParameter.READING_ROOM_CLOSING, closing);

        return now.isAfter(opening) && now.isBefore(closing);
    }
}