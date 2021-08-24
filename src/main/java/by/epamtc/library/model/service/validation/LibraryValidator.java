package by.epamtc.library.model.service.validation;

import java.util.regex.Pattern;

public class LibraryValidator {
    protected static boolean isFieldValid(String field, Pattern pattern){
        if (field == null)
            return false;

        return pattern.matcher(field).matches();
    }
}
