package by.epamtc.library.controller.listener;

import by.epamtc.library.controller.attribute.Localization;
import by.epamtc.library.controller.attribute.SessionAttribute;
import by.epamtc.library.model.entity.UserRole;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web listener of session creation.
 *
 * @author Artur Mironchik
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(SessionAttribute.CURRENT_LOCALIZATION, Localization.EN.getLocalization());
        session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        sessionEvent.getSession().invalidate();
    }
}
