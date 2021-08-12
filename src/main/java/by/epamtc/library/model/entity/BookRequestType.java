package by.epamtc.library.model.entity;

public enum BookRequestType {
    TO_READING_ROOM("to_reading_room"), FOR_SUBSCRIPTION("for_subscription");

    private final String value;

    BookRequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BookRequestType fromString(String value) {
        for (BookRequestType type : BookRequestType.values()) {
            if (type.value.equalsIgnoreCase(value))
                return type;
        }

        return null;
    }
}
