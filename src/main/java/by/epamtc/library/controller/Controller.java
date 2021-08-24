package by.epamtc.library.controller;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.ServletAttribute;
import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandProvider;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.exception.CommandException;
import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.model.connection.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(urlPatterns = ServletAttribute.SERVLET_PATTERN, name = ServletAttribute.SERVLET_NAME)
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Command> command = CommandProvider.defineCommand(req);
        try {
            CommandResult result = command.isPresent() ? command.get().execute(req, resp)
                    : new CommandResult(CommandResult.DEFAULT_PATH);
            result.redirect(req, resp);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, "Couldn't process req: " + e);
            req.setAttribute(JspAttribute.ERROR_MSG, e.getMessage());
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }
}