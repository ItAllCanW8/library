package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;

import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeactivateUserById implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        String userId = req.getParameter(RequestParameter.USER_ID);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.USERS, CommandResult.Type.REDIRECT);
        try {
            if(service.deactivateUser(Long.parseLong(userId))) {
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            }
            else{
                req.setAttribute(JspAttribute.ERROR_DEACTIVATING, JspAttribute.ERROR_DEACTIVATING_MSG);
            }

        } catch (ServiceException e) {
            throw new CommandException("Error deactivating user account");
        }
        return result;
    }
}
