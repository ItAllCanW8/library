package by.epamtc.library.model.entity;

public enum BookRequestState {
    LEFT("left"),
    APPROVED("approved"),
    DENIED("denied"),
    CLOSED("closed");

    private final String value;

    BookRequestState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BookRequestState fromString(String value) {
        for (BookRequestState state : BookRequestState.values()) {
            if (state.value.equalsIgnoreCase(value))
                return state;
        }

        return null;
    }
}
