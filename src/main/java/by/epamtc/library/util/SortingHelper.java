package by.epamtc.library.util;

public final class SortingHelper {
    public enum SortingColumn {
        AVAILABLE_QUANTITY("available_qunatity");

        private final String value;

        SortingColumn(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static SortingColumn fromString(String value) {
            for (SortingColumn field : SortingColumn.values()) {
                if (field.value.equalsIgnoreCase(value))
                    return field;
            }

            return null;
        }
    }

    public enum SortingObject{
        BOOKS("books"), BOOK_REQUESTS("book_requests");

        private final String value;

        SortingObject(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static SortingObject fromString(String value) {
            for (SortingObject object : SortingObject.values()) {
                if (object.value.equalsIgnoreCase(value))
                    return object;
            }

            return null;
        }
    }

    public enum SortingOrderType {
        ASC("asc"), DESC("desc");

        private final String value;

        SortingOrderType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static SortingOrderType fromString(String value) {
            for (SortingOrderType field : SortingOrderType.values()) {
                if (field.value.equalsIgnoreCase(value))
                    return field;
            }

            return null;
        }
    }
}
