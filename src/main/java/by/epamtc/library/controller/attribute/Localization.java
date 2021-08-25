package by.epamtc.library.controller.attribute;

/**
 * Enumeration of application's locales.
 *
 * @author Artur Mironchik
 */
public enum Localization {
    /**
     * Represents russian locale.
     */
    RU("ru_RU"),
    /**
     * Represents english locale.
     */
    EN("en_EN");

    private final String localization;

    Localization(String localization) {
        this.localization = localization;
    }

    /**
     * Defines locale from String object.
     *
     * @param locale String object.
     * @return Localization object.
     */
    public static Localization defineLocalization(String locale) {
        Localization result = EN;
        if (locale != null && !locale.isEmpty()) {
            if (locale.equalsIgnoreCase(RU.localization)) {
                result = RU;
            }
        }
        return result;
    }

    /**
     * Getter method of locale.
     *
     * @return String object of localization.
     */
    public String getLocalization() {
        return localization;
    }
}
