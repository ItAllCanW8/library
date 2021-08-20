package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.UserReport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserReportService {
    boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException;
    List<UserReport> loadUserReports() throws ServiceException;
    Optional<UserReport> findUserReportById(long reportId) throws ServiceException;
    boolean createResponse(long reportId, String response) throws ServiceException;
    List<UserReport> sort(String sortingField, String sortingOrder) throws ServiceException;
}
