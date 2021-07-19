package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.controller.command.RoutingType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowLogin implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
//        request.getSession().removeAttribute(SessionAttribute.LOGIN_ERROR);
//        response.addHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setDateHeader("Expires", 0);
        return new CommandResult("WEB-INF/jsp/authorization/login.jsp", RoutingType.FORWARD);
    }
}