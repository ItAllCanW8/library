package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CoefficientsPage implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
//        LibCoefficientsService service = LibCoefficientsServiceImpl.getInstance();
//
//        try{
//            Map<String, String> coefs = service.loadCoefficients();
//            if(!coefs.isEmpty()) {
//                req.setAttribute(RequestParameter.BOOK_ISSUING_DAYS_NUM,
//                        coefs.get(RequestParameter.BOOK_ISSUING_DAYS_NUM));
//                req.setAttribute(RequestParameter.READING_ROOM_OPENING,
//                        coefs.get(RequestParameter.READING_ROOM_OPENING));
//                req.setAttribute(RequestParameter.READING_ROOM_CLOSING,
//                        coefs.get(RequestParameter.READING_ROOM_CLOSING));
//            }
//        } catch (ServiceException e) {
//            throw new CommandException(e);
//        }


        return new CommandResult(PagePath.SET_COEFFICIENTS, CommandResult.Type.REDIRECT);
    }
}
