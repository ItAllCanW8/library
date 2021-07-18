package by.epamtc.library.controller;

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
        req.getRequestDispatcher("WEB-INF/jsp/home/test.jsp").forward(req,resp);
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
