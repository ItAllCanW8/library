package by.epamtc.library.model.entity;

/**
 * The enum User status.
 *
 * @author Artur Mironchik
 */
public enum UserStatus {
    /**
     * Active user status.
     */
    ACTIVE("active"),
    /**
     * Deactivated user status.
     */
    DEACTIVATED("deactivated"),
    /**
     * Unreliable user status.
     */
    UNRELIABLE("unreliable");

    private final String value;

    UserStatus(String value) {
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
     * From string user status.
     *
     * @param value the value
     * @return the user status
     */
    public static UserStatus fromString(String value) {
        for (UserStatus status : UserStatus.values()) {
            if (status.value.equalsIgnoreCase(value))
                return status;
        }

        return null;
    }
}
