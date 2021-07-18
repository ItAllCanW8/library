package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

//        String email = request.getParameter(Parameters.EMAIL_PARAMETER);
//        char[] password = request.getParameter(Parameters.PASSWORD_PARAMETER).toCharArray();
//
//        UserService userService = ServiceFactory.getInstance().getUserService();
//        User user = userService.login(email, String.valueOf(password));
//        Arrays.fill(password, ' ');
//        if (user != null) {
//            String username = user.getName() + " " + user.getSurname();
//            session.setAttribute(Attributes.USERNAME_ATTRIBUTE, username);
//            session.setAttribute(Attributes.ROLE_ATTRIBUTE, user.getRoleId());
//        }
//        return new CommandResult(Pages.HOME_PAGE_REDIRECT, CommandResultType.REDIRECT);
        return null;
    }
}
