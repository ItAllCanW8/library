package by.epamtc.library.model.service.validation;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Class that validates a user report.
 *
 * @author Artur Mironchik
 */
public final class UserReportValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern SUBJECT_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,255}");
    private static final Pattern MESSAGE_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,1000}");

    private UserReportValidator() {
    }

    /**
     * Is user report form valid boolean.
     *
     * @param fields the fields
     * @return the boolean
     */
    public static boolean isUserReportFormValid(Map<String, String> fields) {
        boolean result = true;
        String subject = fields.get(RequestParameter.USER_REPORT_SUBJECT);
        if (!isSubjectValid(subject)) {
            fields.put(RequestParameter.USER_REPORT_SUBJECT, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        String comment = fields.get(RequestParameter.USER_REPORT_MESSAGE);
        if (!isCommentValid(comment)) {
            fields.put(RequestParameter.USER_REPORT_MESSAGE, JspAttribute.INVALID_INPUT_DATA_MSG);
            result = false;
        }
        return result;
    }

    /**
     * Is subject valid boolean.
     *
     * @param subject the subject
     * @return the boolean
     */
    public static boolean isSubjectValid(String subject) {
        boolean result = EntityValidator.isFieldValid(subject, SUBJECT_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Report subject isn't valid: " + subject);
        }
        return result;
    }

    /**
     * Is comment valid boolean.
     *
     * @param message the message
     * @return the boolean
     */
    public static boolean isCommentValid(String message) {
        boolean result = EntityValidator.isFieldValid(message, MESSAGE_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Report message isn't valid: " + message);
        }
        return result;
    }

    /**
     * Is response valid boolean.
     *
     * @param response the response
     * @return the boolean
     */
    public static boolean isResponseValid(String response) {
        boolean result = EntityValidator.isFieldValid(response, MESSAGE_PATTERN);
        if (!result) {
            LOGGER.log(Level.DEBUG, "Report response isn't valid: " + response);
        }
        return result;
    }
}
