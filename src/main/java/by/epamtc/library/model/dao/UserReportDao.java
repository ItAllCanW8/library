package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.util.SortingHelper;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with user reports table.
 *
 * @author Artur Mironchik
 */
public interface UserReportDao {
    /**
     * Checks if user's report exists.
     *
     * @param message String object of user report message.
     * @param subject String object of user report subject.
     * @param userId long value of user id.
     * @return boolean value. True if the user report exists, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean userReportExists(String message,String subject, long userId) throws DaoException;
    /**
     * Adds user report to the table.
     *
     * @param report UserReport object.
     * @return boolean value. True if the user report has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(UserReport report, long userId) throws DaoException;
    /**
     * Loads all user reports.
     *
     * @return List object of user reports.
     * @throws DaoException if the database throws SQLException.
     */
    List<UserReport> loadUserReports() throws DaoException;
    /**
     * Finds user report by id.
     *
     * @param reportId long value of user's report id.
     * @return Optional object of user report if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<UserReport> findById(long reportId) throws DaoException;
    /**
     * Updates user report response.
     *
     * @param reportId long value of user reports id.
     * @return boolean value. True if the user report response has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateUserReportResponse(long reportId, String response) throws DaoException;

    /**
     * Sorts user reports.
     *
     * @param sortingColumn SortingColumn object of user reports sorting column;
     * @param sortingOrderType SortingOrderType object of sorting order type;
     * @return List object of user reports.
     * @throws DaoException if the database throws SQLException.
     */
    List<UserReport> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType)
            throws DaoException;

    /**
     * Finds user reports by state.
     *
     * @param isProcessed boolean value of reports state.
     * @return List object of user reports.
     * @throws DaoException if the database throws SQLException.
     */
    List<UserReport> findReportsByState(boolean isProcessed) throws DaoException;
}