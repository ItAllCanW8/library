package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.controller.command.RoutingType;
import by.epamtc.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPage implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        if (page != null) {
            return new CommandResult(page, RoutingType.FORWARD);
        } else {
            return new CommandResult("WEB-INF/jsp/error.jsp", RoutingType.FORWARD);
        }
    }
}
