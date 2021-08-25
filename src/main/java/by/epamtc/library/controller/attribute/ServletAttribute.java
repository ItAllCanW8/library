package by.epamtc.library.controller.attribute;

/**
 * Class represents constant names of servlet attributes.
 *
 * @author Artur Mironchik
 */
public class ServletAttribute {
    /**
     * Represents url pattern of the login page.
     */
    public static final String LOGIN_URL = "/login";
    /**
     * Represents url pattern of the contact page.
     */
    public static final String CONTACT_URL = "/contact";
    /**
     * Represents a name of the controller.
     */
    public static final String SERVLET_NAME = "Controller";
    /**
     * Represents url pattern of the controller.
     */
    public static final String SERVLET_PATTERN = "*.do";

    private ServletAttribute() {
    }
}