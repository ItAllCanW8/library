package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.PagePath;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.factory.ServiceFactory;
import by.epamtc.library.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Users implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
//       UserService service = UserServiceImpl.getInstance();
        UserService service = ServiceFactory.getInstance().getUserService();
        CommandResult result = new CommandResult(PagePath.USERS, CommandResult.Type.FORWARD);
        try {
            List<User> users = service.findAllUsers();
            if (users.size() > 0) {
                req.setAttribute(RequestParameter.USERS, users);
            } else {
                req.setAttribute(JspAttribute.NO_USERS, JspAttribute.NO_USERS_MSG);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
