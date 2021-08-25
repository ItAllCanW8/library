package by.epamtc.library.controller.attribute;

/**
 * Class represents constant names of command.
 *
 * @author Artur Mironchik
 */
public class CommandName {
    /**
     * Represents command that redirects a user to the home page.
     */
    public static final String HOME_URL = "/home.do";
    /**
     * Represents command that redirects a user to the list of books.
     */
    public static final String LOAD_BOOKS = "/load_books.do";
    /**
     * Represents command that redirects a user to the book info.
     */
    public static final String LOAD_BOOK_INFO = "/load_book_info.do?bookId=";
    /**
     * Represents command that redirects a user to the user profile.
     */
    public static final String USER_PROFILE = "/user_profile.do";
    /**
     * Represents command that redirects an admin to the list of users.
     */
    public static final String USERS = "/users.do";
    /**
     * Represents command that redirects a user to the list of books.
     */
    public static final String LOAD_USER_PROFILE = "/load_user_profile.do?userId=";
    /**
     * Represents command that redirects a librarian to the books management page.
     */
    public static final String TO_LIBRARIAN_BOOKS = "/to_librarian_books.do";
    /**
     * Represents logout command.
     */
    public static final String LOGOUT = "/logout.do";
    /**
     * Represents command that redirects a librarian to the list of all book requests.
     */
    public static final String BOOK_REQUESTS = "/book_requests.do";
    /**
     * Represents command that redirects a user to the list of his book requests.
     */
    public static final String READER_BOOK_REQUESTS = "/my_book_requests.do";
    /**
     * Represents command that redirects an admin to the list of user reports.
     */
    public static final String USER_REPORTS = "/user_reports.do";
    /**
     * Represents command that redirects an admin to user report info.
     */
    public static final String USER_REPORT = "/load_user_report.do?reportId=";

    private CommandName(){}
}
