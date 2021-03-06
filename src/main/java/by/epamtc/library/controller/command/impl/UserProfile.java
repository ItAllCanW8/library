package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command that loads user profile page.
 *
 * @author Artur Mironchik
 */
public class UserProfile implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        req.setAttribute(RequestParameter.USERNAME, user.getUsername());
        req.setAttribute(RequestParameter.EMAIL, user.getEmail());
        req.setAttribute(RequestParameter.USER_DETAILS, user.getUserDetails());
        return (new CommandResult(PagePath.USER_PROFILE, CommandResult.Type.FORWARD));
    }
}
