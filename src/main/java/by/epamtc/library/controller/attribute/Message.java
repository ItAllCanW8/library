package by.epamtc.library.controller.attribute;

public class Message {
    public static final String LIBRARY_LETTER_SUBJECT = "Library";

    public static final String HELLO_PREFIX = "Hello, ";

    public static final String BOOK_REQUEST_CREATED = "Hello. Your book request has been created. " +
            "Please wait for the librarian to review and approve it. Thank you for using our library. Have a good day! " +
            "http://localhost:8080/library/load_book_info.do?bookId=";

    public static final String BOOK_REQUEST_APPROVED = "Hello. Your book request has been approved. Thank you for using our library." +
            " Have a good day! http://localhost:8080/library/load_book_info.do?bookId=";

    public static final String BOOK_REQUEST_DENIED = "Hello. Your book request has been denied. " +
            "For more information please contact the administration. Thank you for using our library. " +
            "http://localhost:8080/library/load_book_info.do?bookId=";

    public static final String WELCOME_LETTER = ". Thank you for registering in our library. Welcome!";

    public static final String PASSWORD_CHANGED = ". Your account's password has been changed. " +
            "Thank you for using our library.";

    public static final String DEACTIVATION_LETTER = "Hello. Your account has been deactivated. If you want to continue" +
            " using our library, please contact the administration.";

    public static final String ACTIVATION_LETTER = "Hello. Your account has been activated. Enjoy our library!";

    public static final String USER_REPORT_CREATION = ". Thanks for the report you left. We will review your report and after processing you" +
            " will receive a response to your email.";

    private Message(){}
}
