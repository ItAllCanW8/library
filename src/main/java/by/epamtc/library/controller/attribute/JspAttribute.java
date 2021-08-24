package by.epamtc.library.controller.attribute;

public class JspAttribute {
    public static final String EMAIL_AVAILABLE_ERROR_MSG = "This email is already taken.";
    public static final String PHONE_AVAILABLE_ERROR_MSG = "This phone number is already taken.";
    public static final String INVALID_INPUT_DATA_MSG = "";
    public static final String ACCOUNT_IS_DEACTIVATED = "accountIsDeactivated";
    public static final String ACCOUNT_IS_DEACTIVATED_MSG = "Couldn't login cause your account is deactivated. " +
            "Pls contact administration";

    public static final String READING_ROOM_CLOSED_MSG = "Sorry, the reading room is closed now.";

    public static final String ERROR_INPUT_DATA = "errorInputData";
    public static final String ERROR_INPUT_DATA_MSG = "Input data isn't valid.";
    public static final String ERROR_INVALID_CURR_PASSWORD = "invalidCurrPassword";
    public static final String ERROR_INVALID_CURR_PASSWORD_MSG = "Current password isn't valid.";
    public static final String ERROR_BOOK_CREATION = "errorBookCreation";
    public static final String ERROR_BOOK_CREATION_MSG = "Couldn't create book, bad input data.";
    public static final String ERROR_BOOK_UPDATING = "errorBookUpdate";
    public static final String ERROR_DELETING_BOOK = "errorDeletingBook";
    public static final String ERROR_DELETING_BOOK_MSG = "Error deleting book.";
    public static final String ERROR_CHANGING_ROLE = "errorChangingRole";
    public static final String ERROR_CHANGING_ROLE_MSG = "Error changing user role.";

    public static final String NO_BOOKS = "noBooks";
    public static final String NO_BOOKS_MSG = "No books";

    public static final String NO_BOOK = "noBook";
    public static final String NO_BOOK_MSG = "No book";

    public static final String NO_USERS = "noUsers";
    public static final String NO_USERS_MSG = "No users";

    public static final String NO_BOOK_REQUESTS = "noBookRequests";
    public static final String NO_BOOK_REQUESTS_MSG = "No book requests";

    public static final String ERROR_MSG = "errorMsg";

    public static final String ERROR_REQUEST_CREATION = "errorRequestCreation";
    public static final String ERROR_REQUEST_CREATION_MSG = "Error creating request because you have already created such";
    public static final String ERROR_UNRELIABLE_ACCOUNT_MSG = "Error creating request because your account is flagged as" +
            " unreliable and you are not allowed to rent books for subscription. Please contact the administration";

    private JspAttribute(){}
}