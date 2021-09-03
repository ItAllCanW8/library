package by.epamtc.library.model.entity;

/**
 * The enum Book request state.
 *
 * @author Artur Mironchik
 */
public enum BookRequestState {
    /**
     * Left book request state.
     */
    LEFT("left"),
    /**
     * Approved book request state.
     */
    APPROVED("approved"),
    /**
     * Denied book request state.
     */
    DENIED("denied"),
    /**
     * Closed book request state.
     */
    CLOSED("closed");

    private final String value;

    BookRequestState(String value) {
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
     * From string book request state.
     *
     * @param value the value
     * @return the book request state
     */
    public static BookRequestState fromString(String value) {
        for (BookRequestState state : BookRequestState.values()) {
            if (state.value.equalsIgnoreCase(value))
                return state;
        }

        return null;
    }
}