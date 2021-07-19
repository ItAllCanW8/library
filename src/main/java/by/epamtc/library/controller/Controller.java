package by.epamtc.library.controller;

import by.epamtc.library.controller.command.Command;
import by.epamtc.library.controller.command.CommandResult;
import by.epamtc.library.controller.command.factory.CommandFactory;
import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.model.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("WEB-INF/jsp/home/home.jsp").forward(req,resp);

//        String commandName = req.getParameter("command");
//        req.setCharacterEncoding("UTF-8");
//        if (commandName == null || "".equals(commandName)) {
//            req.getRequestDispatcher("WEB-INF/jsp/home/home.jsp").forward(req, resp);
//        }
//
//        Command command = CommandFactory.getInstance().getCommand(commandName);
//        CommandResult commandResult;
//        try {
//            commandResult = command.execute(req, resp);
//        } catch (Exception e) {
//            LOGGER.error("Unable to execute command.", e);
//            throw new ServletException("Unable to execute command.", e);
//        }
//
//        String page = commandResult.getPage();
//        switch (commandResult.getRoutingType()) {
//            case FORWARD:
//                req.getRequestDispatcher(page).forward(req, resp);
//                break;
//            case REDIRECT:
//                resp.sendRedirect(req.getContextPath() + page);
//                break;
//            default:
//                LOGGER.error("Unknown routing type!");
//                resp.sendRedirect(Page.ERROR_PAGE);
        }
//    }

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
