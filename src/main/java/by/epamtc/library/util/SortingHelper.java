package by.epamtc.library.util;

/**
 * Class provides enums for sorting records.
 *
 * @author Artur Mironchik
 */
public final class SortingHelper {
    private SortingHelper(){}

    /**
     * The enum Sorting column.
     */
    public enum SortingColumn {
        /**
         * Available quantity sorting column.
         */
        AVAILABLE_QUANTITY("available_quantity"),
        /**
         * Request date sorting column.
         */
        REQUEST_DATE("request_date"),
        /**
         * Creation date sorting column.
         */
        CREATION_DATE("creation_date");

        private final String value;

        SortingColumn(String value) {
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
         * From string sorting column.
         *
         * @param value the value
         * @return the sorting column
         */
        public static SortingColumn fromString(String value) {
            for (SortingColumn field : SortingColumn.values()) {
                if (field.value.equalsIgnoreCase(value))
                    return field;
            }

            return null;
        }
    }

    /**
     * The enum Sorting entity.
     */
    public enum SortingEntity {
        /**
         * Books sorting entity.
         */
        BOOKS("books"),
        /**
         * Book requests sorting entity.
         */
        BOOK_REQUESTS("book_requests"),
        /**
         * User reports sorting entity.
         */
        USER_REPORTS("user_reports");

        private final String value;

        SortingEntity(String value) {
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
         * From string sorting entity.
         *
         * @param value the value
         * @return the sorting entity
         */
        public static SortingEntity fromString(String value) {
            for (SortingEntity object : SortingEntity.values()) {
                if (object.value.equalsIgnoreCase(value))
                    return object;
            }

            return null;
        }
    }

    /**
     * The enum Sorting order type.
     */
    public enum SortingOrderType {
        /**
         * Asc sorting order type.
         */
        ASC("asc"),
        /**
         * Desc sorting order type.
         */
        DESC("desc");

        private final String value;

        SortingOrderType(String value) {
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
         * From string sorting order type.
         *
         * @param value the value
         * @return the sorting order type
         */
        public static SortingOrderType fromString(String value) {
            for (SortingOrderType field : SortingOrderType.values()) {
                if (field.value.equalsIgnoreCase(value))
                    return field;
            }

            return null;
        }
    }
}
