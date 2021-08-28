package by.epamtc.library.model.service.validation;

import org.apache.logging.log4j.Level;

import java.util.regex.Pattern;

/**
 * The type Entity validator.
 *
 * @author Artur Mironchik
 */
final class EntityValidator {
    /**
     * Is field valid boolean.
     *
     * @param field   the field
     * @param pattern the pattern
     * @return the boolean
     */
    public static boolean isFieldValid(String field, Pattern pattern){
        return field != null && pattern.matcher(field).matches();
    }

    public static boolean isPhotoNameValid(String photoName) {
        if (photoName == null) {
            return false;
        }

        return photoName.length() > 0;
    }

    private EntityValidator(){}
}