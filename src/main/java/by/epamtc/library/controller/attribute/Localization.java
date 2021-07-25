package by.epamtc.library.controller.attribute;

public enum Localization {
    RU("ru_RU"),
    EN("en_EN");

    private final String localization;

    Localization(String localization) {
        this.localization = localization;
    }

    public static Localization defineLocalization(String locale) {
        Localization result = EN;
        if (locale != null && !locale.isEmpty()) {
            if (locale.equalsIgnoreCase(RU.localization)) {
                result = RU;
            }
        }
        return result;
    }
    public String getLocalization() {
        return localization;
    }
}
