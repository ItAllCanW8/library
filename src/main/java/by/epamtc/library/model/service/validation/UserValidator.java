package by.epamtc.library.model.service.validation;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class that validates a user.
 *
 * @author Artur Mironchik
 */
public final class UserValidator{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern NAME_PATTERN = Pattern.compile("[а-яА-Яa-zA-Z]{3,255}");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[а-яА-Я\\w]{3,255}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("((\\w)|(\\w[.-]\\w)){1,42}@((\\w)|(\\w[.-]\\w)){1,41}.[a-zA-Zа-яА-Я]{2,4}");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("(\\+?(((\\d+-\\d+)+)|(\\d{2,20})|((\\d+\\s\\d+)+)))|" +
            "(\\(\\+?\\d+\\)[-\\s]?(((\\d+-\\d+)+)|(\\d+)|((\\d+\\s\\d+)+)))");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{6,255}");

    private static final int PHONE_NUMBER_MAX_LENGTH = 20;
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("([1-2][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[12][0-9]|[3][01])");

    private UserValidator() {
    }

    /**
     * Is register form valid boolean.
     *
     * @param fields the fields
     * @return the boolean
     */
    public static boolean isRegisterFormValid(Map<String, String> fields) {
        areUserFieldsValid(fields);
        boolean result = areUserFieldsValid(fields);
        String password = fields.get(RequestParameter.PASSWORD);
        if (!isPasswordValid(password)) {
            fields.put(RequestParameter.PASSWORD, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String repeatedPassword = fields.get(RequestParameter.REPEATED_PASSWORD);
        if (!isRepeatedPasswordValid(password, repeatedPassword)) {
            fields.put(RequestParameter.REPEATED_PASSWORD, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        return result;
    }

    private static boolean areUserFieldsValid(Map<String, String> fields) {
        boolean result = true;
        String username = fields.get(RequestParameter.USERNAME);
        if (!isUsernameValid(username)) {
            fields.put(RequestParameter.USERNAME, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String name = fields.get(RequestParameter.NAME);
        if (!isNameValid(name)) {
            fields.put(RequestParameter.NAME, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String surname = fields.get(RequestParameter.SURNAME);
        if (!isNameValid(surname)) {
            fields.put(RequestParameter.SURNAME, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String dateOfBirth = fields.get(RequestParameter.DATE_OF_BIRTH);
        if (!isDateFormatValid(dateOfBirth)) {
            fields.put(RequestParameter.DATE_OF_BIRTH, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
        if (!isPhoneNumberValid(phoneNumber)) {
            fields.put(RequestParameter.PHONE_NUMBER, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String email = fields.get(RequestParameter.EMAIL);
        if (!isEmailValid(email)) {
            fields.put(RequestParameter.EMAIL, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        return result;
    }

    /**
     * Is username valid boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public static boolean isUsernameValid(String username) {
        boolean result = EntityValidator.isFieldValid(username, USERNAME_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Username isn't valid: " + username);
        }
        return result;
    }

    /**
     * Is name valid boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        boolean result = EntityValidator.isFieldValid(name, NAME_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Name isn't valid: " + name);
        }
        return result;
    }

    /**
     * Is phone number valid boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean result = EntityValidator.isFieldValid(phoneNumber, PHONE_NUMBER_PATTERN) &&
                phoneNumber.length() <= PHONE_NUMBER_MAX_LENGTH;
        if (!result) {
            LOGGER.log(Level.DEBUG, "Phone number isn't valid: " + phoneNumber);
        }
        return result;
    }

    /**
     * Is email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmailValid(String email) {
        boolean result = EntityValidator.isFieldValid(email, EMAIL_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Email isn't valid: " + email);
        }
        return result;
    }

    /**
     * Is photo name valid boolean.
     *
     * @param photoName the photo name
     * @return the boolean
     */
    public static boolean isPhotoNameValid(String photoName) {
        boolean result = EntityValidator.isPhotoNameValid(photoName);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Photo name isn't valid: " + photoName);
        }
        return result;
    }

    /**
     * Is repeated password valid boolean.
     *
     * @param password     the password
     * @param repeatedPass the repeated pass
     * @return the boolean
     */
    public static boolean isRepeatedPasswordValid(String password, String repeatedPass) {
        boolean result = EntityValidator.isFieldValid(password, PASSWORD_PATTERN) && password.equals(repeatedPass);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Repeated password isn't valid: " + repeatedPass);
        }
        return result;
    }

    /**
     * Is password valid boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isPasswordValid(String password) {
        boolean result = EntityValidator.isFieldValid(password, PASSWORD_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Password isn't valid: " + password);
        }
        return result;
    }

    /**
     * Is date format valid boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public static boolean isDateFormatValid(String date) {
        boolean result = EntityValidator.isFieldValid(date, DATE_FORMAT_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Date isn't valid: " + date);
        }
        return result;
    }

    /**
     * Is edit form valid boolean.
     *
     * @param fields the fields
     * @return the boolean
     */
    public static boolean isEditFormValid(Map<String, String> fields) {
        return areUserFieldsValid(fields);
    }

    /**
     * Is change password form valid boolean.
     *
     * @param fields the fields
     * @return the boolean
     */
    public static boolean isChangePasswordFormValid(Map<String, String> fields) {
        boolean result = true;
        String newPassword = fields.get(RequestParameter.NEW_PASSWORD);

        if (!isPasswordValid(newPassword)) {
            fields.put(RequestParameter.NEW_PASSWORD, "");
            result = false;
        }
        String repeatedNewPassword = fields.get(RequestParameter.REPEATED_PASSWORD);

        if (!isRepeatedPasswordValid(newPassword, repeatedNewPassword)) {
            fields.put(RequestParameter.REPEATED_PASSWORD, "");
            result = false;
        }
        return result;
    }
}
