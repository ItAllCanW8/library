package by.epamtc.library.model.entity;

/**
 * The enum Book request type.
 *
 * @author Artur Mironchik
 */
public enum BookRequestType {
    /**
     * To reading room book request type.
     */
    TO_READING_ROOM("to_reading_room"),
    /**
     * For subscription book request type.
     */
    FOR_SUBSCRIPTION("for_subscription");

    private final String value;

    BookRequestType(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * From string book request type.
     *
     * @param value the value
     * @return the book request type
     */
    public static BookRequestType fromString(String value) {
        for (BookRequestType type : BookRequestType.values()) {
            if (type.value.equalsIgnoreCase(value))
                return type;
        }

        return null;
    }
}
