package by.epamtc.library.controller.filter;

import by.epamtc.library.controller.attribute.ServletAttribute;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandProvider;
import by.epamtc.library.controller.command.CommandType;
import by.epamtc.library.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PermissionFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<UserRole, EnumSet<CommandType>> permissionCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        EnumSet<CommandType> sameCommands = EnumSet.of(CommandType.CHANGE_LANGUAGE, CommandType.LOAD_BOOKS,
                CommandType.LOAD_BOOK_INFO, CommandType.HOME, CommandType.LOAD_BOOK_COVER);

        EnumSet<CommandType> guestCommands = EnumSet.of(CommandType.REGISTER, CommandType.LOGIN);
        guestCommands.addAll(sameCommands);

        EnumSet<CommandType> authorizedUserCommands = EnumSet.of(CommandType.LOGOUT, CommandType.LOAD_USER_PROFILE,
                CommandType.LOAD_PROFILE_PHOTO, CommandType.EDIT_USER_PROFILE, CommandType.USER_PROFILE,
                CommandType.UPLOAD_PHOTO, CommandType.CHANGE_PASSWORD, CommandType.VIEW_PDF);

        EnumSet<CommandType> adminCommands = EnumSet.of(CommandType.CHANGE_ROLE_TO_LIBRARIAN,
                CommandType.CHANGE_ROLE_TO_READER, CommandType.ACTIVATE_USER_ACCOUNT, CommandType.DEACTIVATE_USER_ACCOUNT,
                CommandType.USERS);
        adminCommands.addAll(authorizedUserCommands);
        adminCommands.addAll(sameCommands);

        EnumSet<CommandType> librarianCommands = EnumSet.of(CommandType.TO_LIBRARIAN_BOOKS, CommandType.ADD_BOOK,
                CommandType.UPLOAD_BOOK_COVER, CommandType.UPLOAD_AUTHOR_PHOTO, CommandType.UPLOAD_PDF,
                CommandType.EDIT_BOOK, CommandType.DELETE_BOOK);
        librarianCommands.addAll(sameCommands);
        librarianCommands.addAll(authorizedUserCommands);

        EnumSet<CommandType> readerCommands = EnumSet.of(CommandType.RENT_BOOK);
        readerCommands.addAll(sameCommands);
        readerCommands.addAll(authorizedUserCommands);

        permissionCommands.put(UserRole.GUEST, guestCommands);
        permissionCommands.put(UserRole.READER, readerCommands);
        permissionCommands.put(UserRole.LIBRARIAN, librarianCommands);
        permissionCommands.put(UserRole.ADMIN, adminCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(SessionAttribute.CURRENT_ROLE);
        Optional<Command> optionalCommand = CommandProvider.defineCommand(request);

        if (!optionalCommand.isPresent()) {
            chain.doFilter(request, response);
            return;
        }

        EnumSet<CommandType> commands = permissionCommands.get(role);
        Optional<CommandType> command = CommandProvider.defineCommandType(request);

        if (commands == null || !command.isPresent() || !commands.contains(command.get())) {
            LOGGER.log(Level.ERROR, "User hasn't got permission to execute command " + command);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ServletAttribute.HOME_URL);
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() { }
}