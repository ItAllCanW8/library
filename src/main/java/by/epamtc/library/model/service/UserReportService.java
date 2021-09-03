package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.UserReport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface provides actions on user report.
 *
 * @author Artur Mironchik
 */
public interface UserReportService {
    /**
     * Create user report boolean.
     *
     * @param fields the fields
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException;

    /**
     * Load user reports list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<UserReport> loadUserReports() throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param reportId the report id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<UserReport> findById(long reportId) throws ServiceException;

    /**
     * Create response boolean.
     *
     * @param reportId the report id
     * @param response the response
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createResponse(long reportId, String response) throws ServiceException;

    /**
     * Sort list.
     *
     * @param sortingField the sorting field
     * @param sortingOrder the sorting order
     * @return the list
     * @throws ServiceException the service exception
     */
    List<UserReport> sort(String sortingField, String sortingOrder) throws ServiceException;

    /**
     * Find reports by state list.
     *
     * @param isProcessed the is processed
     * @return the list
     * @throws ServiceException the service exception
     */
    List<UserReport> findReportsByState(boolean isProcessed) throws ServiceException;
}
