package by.epamtc.library.controller.filter;

import by.epamtc.library.controller.attribute.CommandName;
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

/**
 * Filter checks user's role and determines if user has such permissions to process the command.
 *
 * @author Artur Mironchik
 */
public class PermissionFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<UserRole, EnumSet<CommandType>> permissionCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        EnumSet<CommandType> sameCommands = EnumSet.of(CommandType.CHANGE_LANGUAGE, CommandType.LOAD_BOOKS,
                CommandType.LOAD_BOOK_INFO, CommandType.HOME, CommandType.LOAD_BOOK_COVER,
                CommandType.FIND_BOOKS_BY_KEYWORD, CommandType.FIND_BOOKS_BY_GENRE, CommandType.FIND_BOOKS_BY_AUTHOR,
                CommandType.SORT_RECORDS);

        EnumSet<CommandType> guestCommands = EnumSet.of(CommandType.REGISTER, CommandType.LOGIN);
        guestCommands.addAll(sameCommands);

        EnumSet<CommandType> authorizedUserCommands = EnumSet.of(CommandType.LOGOUT, CommandType.LOAD_USER_PROFILE,
                CommandType.LOAD_PROFILE_PHOTO, CommandType.EDIT_USER_PROFILE, CommandType.USER_PROFILE,
                CommandType.DEACTIVATE_ACCOUNT, CommandType.UPLOAD_PHOTO, CommandType.CHANGE_PASSWORD, CommandType.VIEW_PDF,
                CommandType.FIND_BOOK_REQUESTS_BY_TYPE, CommandType.FIND_BOOK_REQUESTS_BY_STATE, CommandType.SEND_REPORT);

        EnumSet<CommandType> adminCommands = EnumSet.of(CommandType.CHANGE_ROLE_TO_LIBRARIAN,
                CommandType.CHANGE_ROLE_TO_READER,CommandType.CHANGE_USER_STATUS, CommandType.USERS,
                CommandType.FIND_USERS_BY_ROLE, CommandType.FIND_USERS_BY_STATUS, CommandType.USER_REPORTS,
                CommandType.LOAD_USER_REPORT, CommandType.CREATE_REPORT_RESPONSE, CommandType.FIND_REPORTS_BY_STATE);
            adminCommands.addAll(authorizedUserCommands);
        adminCommands.addAll(sameCommands);

        EnumSet<CommandType> librarianCommands = EnumSet.of(CommandType.TO_LIBRARIAN_BOOKS, CommandType.ADD_BOOK,
                CommandType.UPLOAD_BOOK_COVER, CommandType.UPLOAD_AUTHOR_PHOTO, CommandType.UPLOAD_PDF,
                CommandType.EDIT_BOOK, CommandType.DELETE_BOOK, CommandType.BOOK_REQUESTS,
                CommandType.CHANGE_BOOK_REQUEST_STATE, CommandType.DELETE_BOOK_REQUEST);
        librarianCommands.addAll(sameCommands);
        librarianCommands.addAll(authorizedUserCommands);

        EnumSet<CommandType> readerCommands = EnumSet.of(CommandType.RENT_BOOK, CommandType.READING_ROOM,
                CommandType.MY_BOOK_REQUESTS, CommandType.RETURN_BOOK);
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

        if (optionalCommand.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        EnumSet<CommandType> commands = permissionCommands.get(role);
        Optional<CommandType> command = CommandProvider.defineCommandType(request);

        if (commands == null || command.isEmpty() || !commands.contains(command.get())) {
            LOGGER.log(Level.ERROR, "User hasn't got permission to execute command " + command.get());
            RequestDispatcher dispatcher = request.getRequestDispatcher(CommandName.HOME_URL);
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() { }
}
