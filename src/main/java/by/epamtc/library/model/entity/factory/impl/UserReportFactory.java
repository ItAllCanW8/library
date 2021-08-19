package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.util.DateTimeHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static by.epamtc.library.model.service.validation.UserReportValidator.isUserReportFormValid;

public class UserReportFactory implements LibraryFactory<UserReport> {
    private static final boolean DEFAULT_AVAILABILITY_VALUE = true;

    public static class HOLDER{
        public static LibraryFactory<UserReport> INSTANCE = new UserReportFactory();
    }

    public static LibraryFactory<UserReport> getInstance(){
        return HOLDER.INSTANCE;
    }

    @Override
    public Optional<UserReport> create(Map<String, String> fields) {
        Optional<UserReport> result = Optional.empty();
        if (isUserReportFormValid(fields)) {
            String subject = fields.get(RequestParameter.USER_REPORT_SUBJECT);
            String message = fields.get(RequestParameter.USER_REPORT_MESSAGE);
            String creationDate = LocalDateTime.now().format(DateTimeHelper.formatter);
            result = Optional.of(new UserReport(DEFAULT_AVAILABILITY_VALUE, subject, message, creationDate));
        }
        return result;
    }
}
