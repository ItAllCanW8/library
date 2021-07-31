package by.epamtc.library.model.service.validation;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class UserValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,255}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("((\\w)([-.](\\w))?){1,64}@((\\w)([-.](\\w))?){1,251}.[a-zA-Z]{2,4}");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("(\\+?(((\\d+-\\d+)+)|(\\d{2,20})|((\\d+\\s\\d+)+)))|" +
            "(\\(\\+?\\d+\\)[-\\s]?(((\\d+-\\d+)+)|(\\d+)|((\\d+\\s\\d+)+)))");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[а-яА-Я\\w\\s\\p{Punct}]{6,255}");

    private static final int PHONE_NUMBER_MAX_LENGTH = 20;
    private static final int PHOTO_NAME_MAX_LENGTH = 45;
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("([1-2][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[12][0-9]|[3][01])");

    private UserValidator() {
    }

    public static boolean isRegisterFormValid(Map<String, String> fields) {
        boolean result = true;

        String username = fields.get(RequestParameter.USERNAME);
        if (!isNameValid(username)) {
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
        String password = fields.get(RequestParameter.PASSWORD);
        if (!isPasswordValid(password)) {
            fields.put(RequestParameter.PASSWORD, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String repeatPassword = fields.get(RequestParameter.REPEATED_PASSWORD);
        if (!isRepeatPasswordValid(password, repeatPassword)) {
            fields.put(RequestParameter.REPEATED_PASSWORD, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        return result;
    }

    public static boolean isUserRoleValid(String role) {
        if (role == null || role.isEmpty()) {
            return false;
        }

        boolean result = Arrays.stream(UserRole.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(role.toUpperCase());

        if (!result) {
            LOGGER.log(Level.DEBUG, "Role isn't valid: " + role);
        }

        return result;
    }

    public static boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }

        Matcher matcher = NAME_PATTERN.matcher(name);
        boolean result = matcher.matches();

        if (!result) {
            LOGGER.log(Level.DEBUG, "Name isn't valid: " + name);
        }

        return result;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);
        boolean result = matcher.matches() && phoneNumber.length() <= PHONE_NUMBER_MAX_LENGTH;

        if (!result) {
            LOGGER.log(Level.DEBUG, "Phone number isn't valid: " + phoneNumber);
        }
        return result;
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        boolean result = matcher.matches();
        if (!result) {
            LOGGER.log(Level.DEBUG, "Email isn't valid: " + email);
        }
        return result;
    }

    public static boolean isPhotoNameValid(String photoName) {
        if (photoName == null) {
            return false;
        }
        boolean result = photoName.length() > 0 && photoName.length() <= PHOTO_NAME_MAX_LENGTH;
        if (!result) {
            LOGGER.log(Level.DEBUG, "Photo name isn't valid: " + photoName);
        }
        return result;
    }

    public static boolean isRepeatPasswordValid(String password, String repeatedPass) {
        if (repeatedPass == null) {
            return false;
        }

        boolean result = isPasswordValid(password) && password.equals(repeatedPass);

        if (!result) {
            LOGGER.log(Level.DEBUG, "Repeated password isn't valid: " + repeatedPass);
        }
        return result;
    }

    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }

        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        boolean result = matcher.matches();

        if (!result) {
            LOGGER.log(Level.DEBUG, "Password isn't valid: " + password);
        }
        return result;
    }

    public static boolean isDateFormatValid(String date) {
        if (date == null) {
            return false;
        }

        Matcher matcher = DATE_FORMAT_PATTERN.matcher(date);
        boolean result = matcher.matches() && LocalDate.parse(date).isBefore(LocalDate.now());

        if (!result) {
            LOGGER.log(Level.DEBUG, "Date isn't valid: " + date);
        }
        return result;
    }

    public static boolean isEditFormValid(Map<String, String> fields) {
        boolean result = true;
        String username = fields.get(RequestParameter.USERNAME);
        if (!isNameValid(username)) {
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

    public static boolean isChangePasswordFormValid(Map<String, String> fields) {
        boolean result = true;
        String newPassword = fields.get(RequestParameter.NEW_PASSWORD);

        if (!isPasswordValid(newPassword)) {
            fields.put(RequestParameter.NEW_PASSWORD, "");
            result = false;
        }
        String repeatedNewPassword = fields.get(RequestParameter.REPEATED_PASSWORD);

        if (!isRepeatPasswordValid(newPassword, repeatedNewPassword)) {
            fields.put(RequestParameter.REPEATED_PASSWORD, "");
            result = false;
        }
        return result;
    }
}
