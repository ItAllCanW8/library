package by.epamtc.library.model.service.validation;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Pattern;

public final class BookValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern TITLE_PATTERN = Pattern.compile("[А-Яа-я\\w\\p{Blank}]{3,255}");
    private static final Pattern AUTHOR_PATTERN = Pattern.compile("[А-Яа-яa-zA-Z\\s]{3,255}");
    private static final Pattern ISBN_PATTERN = Pattern.compile("[\\d]{13}");
    private static final Pattern GENRE_PATTERN = Pattern.compile("[А-Яа-яa-zA-Z]{3,45}");
    private static final Pattern QUANTITY_PATTERN = Pattern.compile("[\\d]{1,4}");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,1000}");

    public static boolean isBookFormValid(Map<String, String> fields) {
        boolean result = true;
        String title = fields.get(RequestParameter.BOOK_TITLE);
        if (!isTitleValid(title)) {
            fields.put(RequestParameter.BOOK_TITLE, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String author = fields.get(RequestParameter.BOOK_AUTHOR);
        if (!isAuthorValid(author)) {
            fields.put(RequestParameter.BOOK_AUTHOR, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String isbn = fields.get(RequestParameter.BOOK_ISBN);
        if (!isISBNValid(isbn)) {
            fields.put(RequestParameter.BOOK_ISBN, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String genre = fields.get(RequestParameter.BOOK_GENRE);
        if (!isGenreValid(genre)) {
            fields.put(RequestParameter.BOOK_GENRE, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String quantity = fields.get(RequestParameter.BOOK_QUANTITY);
        if (!isQuantityValid(quantity)) {
            fields.put(RequestParameter.BOOK_QUANTITY, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String description = fields.get(RequestParameter.BOOK_DESCRIPTION);
        if (!isDescriptionValid(description)) {
            fields.put(RequestParameter.BOOK_DESCRIPTION, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        return result;
    }

    public static boolean isTitleValid(String title) {
        boolean result = isFieldValid(title, TITLE_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Book title isn't valid: " + title);
        }
        return result;
    }

    public static boolean isAuthorValid(String author) {
        boolean result = isFieldValid(author, AUTHOR_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Author isn't valid: " + author);
        }
        return result;
    }
    public static boolean isISBNValid(String isbn) {
        boolean result = isFieldValid(isbn, ISBN_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "ISBN isn't valid: " + isbn);
        }
        return result;
    }
    public static boolean isGenreValid(String genre) {
        boolean result = isFieldValid(genre, GENRE_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Genre isn't valid: " + genre);
        }
        return result;
    }
    public static boolean isQuantityValid(String quantity) {
        boolean result = isFieldValid(quantity, QUANTITY_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Quantity isn't valid: " + quantity);
        }
        return result;
    }
    public static boolean isDescriptionValid(String description) {
        boolean result = isFieldValid(description, DESCRIPTION_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Description isn't valid: " + description);
        }
        return result;
    }

    private static boolean isFieldValid(String field, Pattern pattern){
        if (field == null)
            return false;

        return pattern.matcher(field).matches();
    }

    private BookValidator(){}
}
