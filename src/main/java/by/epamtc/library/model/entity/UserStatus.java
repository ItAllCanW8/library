package by.epamtc.library.model.entity;

public enum UserStatus {
    ACTIVE("active"), DEACTIVATED("deactivated");

    private final String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserStatus fromString(String value) {
        for (UserStatus status : UserStatus.values()) {
            if (status.value.equalsIgnoreCase(value))
                return status;
        }

        return null;
    }
}
