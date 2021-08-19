package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;

import java.util.Map;

public interface UserReportService {
    boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException;
}
