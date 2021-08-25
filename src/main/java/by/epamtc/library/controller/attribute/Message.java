package by.epamtc.library.controller.attribute;

/**
 * Class represents constant names of mail messages.
 *
 * @author Artur Mironchik
 */
public class Message {
    /**
     * The constant that represents the subject of the message.
     */
    public static final String LIBRARY_LETTER_SUBJECT = "Library";
    /**
     * The constant that represents the prefix of the message.
     */
    public static final String HELLO_PREFIX = "Hello, ";
    /**
     * The constant that represents the text of the message in case a reader created a book request for subscription.
     */
    public static final String BOOK_REQUEST_CREATED = "Hello. Your book request has been created. " +
            "Please wait for the librarian to review and approve it. Thank you for using our library. Have a good day! " +
            "http://localhost:8080/library/load_book_info.do?bookId=";
    /**
     * The constant that represents the text of the message in case a librarian approved the book request for subscription.
     */
    public static final String BOOK_REQUEST_APPROVED = "Hello. Your book request has been approved. Thank you for using our library." +
            " Have a good day! http://localhost:8080/library/load_book_info.do?bookId=";
    /**
     * The constant that represents the text of the message in case a librarian denied a book request for subscription.
     */
    public static final String BOOK_REQUEST_DENIED = "Hello. Your book request has been denied. " +
            "For more information please contact the administration. Thank you for using our library. " +
            "http://localhost:8080/library/load_book_info.do?bookId=";
    /**
     * The constant that represents the text of the message after user registration.
     */
    public static final String WELCOME_LETTER = ". Thank you for registering in our library. Welcome!";
    /**
     * The constant that represents the text of the message after user changing password.
     */
    public static final String PASSWORD_CHANGED = ". Your account's password has been changed. " +
            "Thank you for using our library.";
    /**
     * The constant that represents the text of the message after the user has deactivated their account.
     */
    public static final String DEACTIVATION = ". Your account has been deactivated. If you want to continue" +
            " using our library, please contact the administration.";
    /**
     * The constant that represents the prefix of the message after the status change of user account.
     */
    public static final String STATUS_CHANGED_PREFIX = ". Your account status has been changed to ";
    /**
     * The constant that represents the text of the message after the status change of user account.
     */
    public static final String STATUS_CHANGED_POSTFIX = ". If you have any questions please contact administration. Have " +
            "a good day!";
    /**
     * The constant that represents the text of the message after user created a report.
     */
    public static final String USER_REPORT_CREATION = ". Thanks for the report you left. We will review your report and after processing you" +
            " will receive a response to your email.";
    /**
     * The constant that represents the text of the message after admin response to the user report.
     */
    public static final String USER_REPORT_RESPONSE = ". The report you left has already been processed. Response: ";

    private Message(){}
}