package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.attribute.CommandName;
import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ServiceException;

import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeactivateUserById implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String userId = req.getParameter(RequestParameter.USER_ID);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(CommandName.USERS, CommandResult.Type.REDIRECT);
        try {
            if(!service.deactivateUser(Long.parseLong(userId)))
                req.setAttribute(JspAttribute.ERROR_CHANGING_STATUS, JspAttribute.ERROR_CHANGING_STATUS_MSG);
        } catch (ServiceException e) {
            throw new CommandException("Error deactivating user account");
        }
        return result;
    }
}
