package by.epamtc.library.model.entity;

public enum BookRequestType {
    TO_READING_ROOM("toReadingRoom"), FOR_SUBSCRIPTION("forSubscription");

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

        throw new IllegalArgumentException("No constant BookRequestType with value " + value + " found");
    }
}
