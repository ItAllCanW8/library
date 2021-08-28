package by.epamtc.library.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Class provides session for email sending.
 *
 * @author Artur Mironchik
 */
public class SessionCreator {
    private static final String EMAIL = "mail.user.name";
    private static final String PASSWORD = "mail.user.password";

    /**
     * Create session session.
     *
     * @param configProperties the config properties
     * @return the session
     */
    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty(EMAIL);
        String userPassword = configProperties.getProperty(PASSWORD);
        return Session.getDefaultInstance(configProperties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
