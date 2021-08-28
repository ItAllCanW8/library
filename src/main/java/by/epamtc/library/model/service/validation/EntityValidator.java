package by.epamtc.library.model.service.validation;

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

    private EntityValidator(){}
}