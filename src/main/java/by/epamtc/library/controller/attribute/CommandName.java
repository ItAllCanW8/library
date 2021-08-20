package by.epamtc.library.controller.attribute;

public class CommandName {
    public static final String LOAD_BOOKS = "/load_books.do";
    public static final String LOAD_BOOK_INFO = "/load_book_info.do?bookId=";
    public static final String USER_PROFILE = "/user_profile.do";
    public static final String USERS = "/users.do";
    public static final String LOAD_USER_PROFILE = "/load_user_profile.do?userId=";
    public static final String DEACTIVATE_ACCOUNT = "/deactivate_user_account.do?userId=";
    public static final String ACTIVATE_ACCOUNT = "/activate_user_account.do?userId=";
    public static final String TO_LIBRARIAN_BOOKS = "/to_librarian_books.do";
    public static final String ADD_BOOK = "/add_book.do";
    public static final String LOGOUT = "/logout.do";
    public static final String BOOK_REQUESTS = "/book_requests.do";
    public static final String READER_BOOK_REQUESTS = "/my_book_requests.do";
    public static final String USER_REPORTS = "/user_reports.do";
    public static final String USER_REPORT = "/load_user_report.do?reportId=";

    private CommandName(){}
}
