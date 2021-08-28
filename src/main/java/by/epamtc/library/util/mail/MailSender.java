package by.epamtc.library.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;


/**
 * Class provides methods for mail sending.
 *
 * @author Artur Mironchik
 */
public class MailSender {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MAIL_PROPERTY_FILE_PATH = "/mail.properties";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final Properties properties;

    private MimeMessage message;
    private String recipientEmail;
    private String letterSubject;
    private String letterContent;

    private MailSender() {
    }

    static {
        properties = new Properties();
        try {
            properties.load(MailSender.class.getResourceAsStream(MAIL_PROPERTY_FILE_PATH));
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error loading mail properties", e );
        }
    }

    private static class Holder {
        /**
         * The Instance.
         */
        static final MailSender INSTANCE = new MailSender();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MailSender getInstance() {
        return MailSender.Holder.INSTANCE;
    }

    /**
     * Gets recipient email.
     *
     * @return the recipient email
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Sets recipient email.
     *
     * @param recipientEmail the recipient email
     */
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    /**
     * Gets letter subject.
     *
     * @return the letter subject
     */
    public String getLetterSubject() {
        return letterSubject;
    }

    /**
     * Sets letter subject.
     *
     * @param letterSubject the letter subject
     */
    public void setLetterSubject(String letterSubject) {
        this.letterSubject = letterSubject;
    }

    /**
     * Gets letter content.
     *
     * @return the letter content
     */
    public String getLetterContent() {
        return letterContent;
    }

    /**
     * Sets letter content.
     *
     * @param letterContent the letter content
     */
    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    /**
     * Sets letter.
     *
     * @param recipientEmail the recipient email
     * @param letterSubject  the letter subject
     * @param letterContent  the letter content
     */
    public void setupLetter(String recipientEmail, String letterSubject, String letterContent) {
        this.recipientEmail = recipientEmail;
        this.letterSubject = letterSubject;
        this.letterContent = letterContent;
    }

    /**
     * Send.
     */
    public void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {
            LOGGER.log(Level.ERROR, "Invalid email: " + recipientEmail , e);
        } catch (MessagingException e) {
            LOGGER.log(Level.ERROR, "Error generating or sending message: ", e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionCreator.createSession(properties);

        message = new MimeMessage(mailSession);
        message.setSubject(letterSubject);
        message.setContent(letterContent, CONTENT_TYPE);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
    }
}