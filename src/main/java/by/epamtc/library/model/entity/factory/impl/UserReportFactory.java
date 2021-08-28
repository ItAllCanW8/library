package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.entity.factory.EntityFactory;
import by.epamtc.library.util.DateTimeHelper;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static by.epamtc.library.model.service.validation.UserReportValidator.isUserReportFormValid;

/**
 * EntityFactory implementation used to create a UserReport object.
 *
 * @author Artur Mironchik
 */
public class UserReportFactory implements EntityFactory<UserReport> {
    private static final boolean DEFAULT_PROCESSED_VALUE = false;

    private static class HOLDER{
        /**
         * The constant INSTANCE.
         */
        public static EntityFactory<UserReport> INSTANCE = new UserReportFactory();
    }

    /**
     * Get instance entity factory.
     *
     * @return the entity factory
     */
    public static EntityFactory<UserReport> getInstance(){
        return HOLDER.INSTANCE;
    }

    @Override
    public Optional<UserReport> create(Map<String, String> fields) {
        Optional<UserReport> result = Optional.empty();
        if (isUserReportFormValid(fields)) {
            String subject = fields.get(RequestParameter.USER_REPORT_SUBJECT);
            String message = fields.get(RequestParameter.USER_REPORT_MESSAGE);
            String creationDate = LocalDateTime.now().format(DateTimeHelper.formatter);
            result = Optional.of(new UserReport(DEFAULT_PROCESSED_VALUE, subject, message, creationDate));
        }
        return result;
    }
}
