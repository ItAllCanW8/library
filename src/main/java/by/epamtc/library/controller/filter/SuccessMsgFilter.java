package by.epamtc.library.controller.filter;

import by.epamtc.library.controller.attribute.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter moves a success message to the jsp page and removes this message from the session.
 *
 * @author Artur Mironchik
 */
public class SuccessMsgFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.SUCCESS_MESSAGE) != null) {
            request.setAttribute(SessionAttribute.SUCCESS_MESSAGE, true);
        }
        session.removeAttribute(SessionAttribute.SUCCESS_MESSAGE);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
