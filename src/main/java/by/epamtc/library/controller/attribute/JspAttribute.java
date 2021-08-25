package by.epamtc.library.controller.attribute;

/**
 * Class represents constant names of jsp attributes.
 *
 * @author Artur Mironchik
 */
public class JspAttribute {
    /**
     * The constant that represents EMAIL_AVAILABLE_ERROR_MSG.
     */
    public static final String EMAIL_AVAILABLE_ERROR_MSG = "This email is already taken.";
    /**
     * The constant that represents PHONE_AVAILABLE_ERROR_MSG.
     */
    public static final String PHONE_AVAILABLE_ERROR_MSG = "This phone number is already taken.";
    /**
     * The constant that represents INVALID_INPUT_DATA_MSG.
     */
    public static final String INVALID_INPUT_DATA_MSG = "";
    /**
     * The constant that represents ACCOUNT_IS_DEACTIVATED.
     */
    public static final String ACCOUNT_IS_DEACTIVATED = "accountIsDeactivated";
    /**
     * The constant that represents ACCOUNT_IS_DEACTIVATED_MSG.
     */
    public static final String ACCOUNT_IS_DEACTIVATED_MSG = "Couldn't login cause your account is deactivated. " +
            "Pls contact administration";
    /**
     * The constant that represents READING_ROOM_CLOSED_MSG.
     */
    public static final String READING_ROOM_CLOSED_MSG = "Sorry, the reading room is closed now.";
    /**
     * The constant that represents TOO_MUCH_SUB_BOOK_REQUESTS_MSG.
     */
    public static final String TOO_MUCH_SUB_BOOK_REQUESTS_MSG = "Sorry, the number of your book requests for a subscription is" +
            " greater than or equal to the maximum";
    /**
     * The constant that represents ERROR_INPUT_DATA.
     */
    public static final String ERROR_INPUT_DATA = "errorInputData";
    /**
     * The constant that represents ERROR_INPUT_DATA_MSG.
     */
    public static final String ERROR_INPUT_DATA_MSG = "Input data isn't valid.";
    /**
     * The constant that represents ERROR_INVALID_CURR_PASSWORD.
     */
    public static final String ERROR_INVALID_CURR_PASSWORD = "invalidCurrPassword";
    /**
     * The constant that represents ERROR_INVALID_CURR_PASSWORD_MSG.
     */
    public static final String ERROR_INVALID_CURR_PASSWORD_MSG = "Current password isn't valid.";
    /**
     * The constant that represents ERROR_BOOK_CREATION.
     */
    public static final String ERROR_BOOK_CREATION = "errorBookCreation";
    /**
     * The constant that represents ERROR_BOOK_CREATION_MSG.
     */
    public static final String ERROR_BOOK_CREATION_MSG = "Couldn't create book, bad input data.";
    /**
     * The constant that represents ERROR_BOOK_UPDATING.
     */
    public static final String ERROR_BOOK_UPDATING = "errorBookUpdate";
    /**
     * The constant that represents ERROR_DELETING_BOOK.
     */
    public static final String ERROR_DELETING_BOOK = "errorDeletingBook";
    /**
     * The constant that represents ERROR_DELETING_BOOK_MSG.
     */
    public static final String ERROR_DELETING_BOOK_MSG = "Error deleting book.";
    /**
     * The constant that represents ERROR_CHANGING_ROLE.
     */
    public static final String ERROR_CHANGING_ROLE = "errorChangingRole";
    /**
     * The constant that represents ERROR_CHANGING_ROLE_MSG.
     */
    public static final String ERROR_CHANGING_ROLE_MSG = "Error changing user role.";
    /**
     * The constant that represents NO_BOOKS.
     */
    public static final String NO_BOOKS = "noBooks";
    /**
     * The constant that represents NO_BOOKS_MSG.
     */
    public static final String NO_BOOKS_MSG = "No books";
    /**
     * The constant that represents NO_BOOK.
     */
    public static final String NO_BOOK = "noBook";
    /**
     * The constant that represents NO_BOOK_MSG.
     */
    public static final String NO_BOOK_MSG = "No book";
    /**
     * The constant that represents NO_USERS.
     */
    public static final String NO_USERS = "noUsers";
    /**
     * The constant that represents NO_USERS_MSG.
     */
    public static final String NO_USERS_MSG = "No users";
    /**
     * The constant that represents NO_BOOK_REQUESTS.
     */
    public static final String NO_BOOK_REQUESTS = "noBookRequests";
    /**
     * The constant that represents NO_BOOK_REQUESTS_MSG.
     */
    public static final String NO_BOOK_REQUESTS_MSG = "No book requests";
    /**
     * The constant that represents ERROR_MSG.
     */
    public static final String ERROR_MSG = "errorMsg";
    /**
     * The constant that represents ERROR_REQUEST_CREATION.
     */
    public static final String ERROR_REQUEST_CREATION = "errorRequestCreation";
    /**
     * The constant that represents ERROR_REQUEST_CREATION_MSG.
     */
    public static final String ERROR_REQUEST_CREATION_MSG = "Error creating request because you have already created such";
    /**
     * The constant that represents ERROR_UNRELIABLE_ACCOUNT_MSG.
     */
    public static final String ERROR_UNRELIABLE_ACCOUNT_MSG = "Error creating request because your account is flagged as" +
            " unreliable and you are not allowed to rent books for subscription. Please contact the administration";

    private JspAttribute(){}
}