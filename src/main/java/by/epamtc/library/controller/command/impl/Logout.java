package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.ServletAttribute;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        session.removeAttribute(SessionAttribute.USER_ID);
        session.removeAttribute(SessionAttribute.USER);
        session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
        return (new CommandResult(ServletAttribute.HOME_URL, CommandResult.Type.RETURN_WITH_REDIRECT));
    }
}
