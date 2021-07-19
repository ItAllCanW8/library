package by.epamtc.library.controller.command.impl;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.controller.command.RoutingType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

//public class LoginCommand implements Command {
////    private static final String CONTROLLER_COMMAND = "/controller?command=";
//
//    @Override
//    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//
////        String email = request.getParameter(RequestParameter.EMAIL);
////        if (email == null || request.getParameter((RequestParameter.PASSWORD)) == null) {
////            session.setAttribute(SessionAttribute.LOGIN_ERROR, true);
////            return new CommandResult(Page.LOGIN_PAGE_REDIRECT, RoutingType.REDIRECT);
////        }
////        char[] password = request.getParameter(RequestParameter.PASSWORD).toCharArray();
////
////        UserService userService = ServiceFactory.getInstance().getUserService();
////        User user = userService.login(email, String.valueOf(password));
////        Arrays.fill(password, ' ');
////        if (user != null) {
////            String username = user.getName() + " " + user.getSurname();
////            session.setAttribute(SessionAttribute.USERNAME, username);
////            session.setAttribute(SessionAttribute.ROLE, user.getRoleId());
////        } else {
////            session.setAttribute(SessionAttribute.LOGIN_ERROR, true);
////            return new CommandResult(Page.LOGIN_PAGE_REDIRECT, RoutingType.REDIRECT);
////        }
//
////        CommandResult commandResult;
//////        Optional<String> previousCommand = Optional.ofNullable((String) session.getAttribute(SessionAttribute.PREVIOUS_COMMAND));
////        if (previousCommand.isPresent()) {
////            commandResult = new CommandResult(CONTROLLER_COMMAND + previousCommand.get(), RoutingType.REDIRECT);
////            session.removeAttribute(SessionAttribute.PREVIOUS_COMMAND);
////        } else {
////            commandResult = new CommandResult(Page.HOME_PAGE_REDIRECT, RoutingType.REDIRECT);
////        }
////        return commandResult;
//    }
//}
