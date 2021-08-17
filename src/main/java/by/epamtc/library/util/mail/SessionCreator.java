package by.epamtc.library.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

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
