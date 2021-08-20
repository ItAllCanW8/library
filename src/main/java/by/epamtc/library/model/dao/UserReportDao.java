package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.util.SortingHelper;

import java.util.List;
import java.util.Optional;

public interface UserReportDao {
    boolean userReportExists(String message,String subject, long userId) throws DaoException;
    boolean add(UserReport report, long userId) throws DaoException;
    List<UserReport> loadUserReports() throws DaoException;
    Optional<UserReport> findUserReportById(long reportId) throws DaoException;
    boolean updateUserReportResponse(long reportId, String response) throws DaoException;
    List<UserReport> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType)
            throws DaoException;
}
