package by.epamtc.library.controller.attribute;

/**
 * Class represents constant names of session attributes.
 *
 * @author Artur Mironchik
 */
public class SessionAttribute {
    /**
     * Represents logged user's id.
     */
    public static final String USER_ID = "userId";
    /**
     * Represents success message.
     */
    public static final String SUCCESS_MESSAGE = "successMessage";
    /**
     * Represents logged user with all user's fields.
     */
    public static final String USER = "user";
    /**
     * Represents logged user's role.
     */
    public static final String CURRENT_ROLE = "currentRole";
    /**
     * Represents current localization.
     */
    public static final String CURRENT_LOCALIZATION = "currentLocalization";

    private SessionAttribute(){}
}