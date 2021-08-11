package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.service.LibCoefficientsService;
import by.epamtc.library.model.service.impl.LibCoefficientsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCoefficients implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        System.out.println("set");

        String readingRoomOpening = req.getParameter(RequestParameter.READING_ROOM_OPENING);
        String readingRoomClosing = req.getParameter(RequestParameter.READING_ROOM_CLOSING);

        System.out.println(readingRoomOpening+ ""+ readingRoomClosing);
        int bookIssuingDaysNum = Integer.parseInt(req.getParameter(RequestParameter.BOOK_ISSUING_DAYS_NUM));
        LibCoefficientsService service = LibCoefficientsServiceImpl.getInstance();

        try{
            service.updateCoefficients(bookIssuingDaysNum, readingRoomOpening, readingRoomClosing);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }


        return new CommandResult(PagePath.SET_COEFFICIENTS, CommandResult.Type.REDIRECT);
    }
}
