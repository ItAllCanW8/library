package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.UserReportDao;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.util.DateTimeHelper;
import by.epamtc.library.util.SortingHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserReportDao implementation.
 *
 * @author Artur Mironchik
 */
public class UserReportDaoImpl implements UserReportDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String reportIdCol = "report_id";
    private static final String messageCol = "message";
    private static final String responseCol = "response";
    private static final String isProcessedCol = "is_processed";
    private static final String subjectCol = "subject";
    private static final String creationDateCol = "creation_date";

    private static final String userIdCol = "user_id_fk";
    private static final String usernameCol = "username";
    private static final String emailCol = "email";
    private static final String roleCol = "role";

    /**
     * Constructs a UserReportDaoImpl object.
     */
    UserReportDaoImpl(){}

    @Override
    public boolean userReportExists(String message,String subject, long userId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_USER_REPORT_FOR_EXISTENCE)) {
            statement.setString(1, subject);
            statement.setString(2, message);
            statement.setLong(3, userId);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error checking if user report exists",e);
        }
    }

    @Override
    public boolean add(UserReport report, long userId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER_REPORT)) {
            statement.setByte(1, report.isProcessed() ? (byte) 1 : 0);
            statement.setString(2, report.getSubject());
            statement.setString(3, report.getMessage());
            statement.setString(4, LocalDateTime.now().format(DateTimeHelper.formatter));
            statement.setLong(5, userId);

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error creating user report",e);
        }
    }

    @Override
    public List<UserReport> loadUserReports() throws DaoException {
        List<UserReport> reports = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_USER_REPORTS);

            while (resultSet.next()) {
                reports.add(createUserReportFromResultSet(resultSet, false));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return reports;
    }

    @Override
    public Optional<UserReport> findById(long reportId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_REPORT_BY_ID)) {
            statement.setLong(1, reportId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(createUserReportFromResultSet(resultSet, true)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateUserReportResponse(long reportId, String response) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_REPORT_RESPONSE)) {
            statement.setString(1, response);
            statement.setLong(2, reportId);

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<UserReport> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType) throws DaoException {
        List<UserReport> userReports = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SORT_USER_REPORTS + sortingColumn.getValue() + " "
                    + sortingOrderType.getValue());

            while (resultSet.next()) {
                userReports.add(createUserReportFromResultSet(resultSet, false));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error sorting user reports by " + sortingColumn + " " + sortingOrderType, e);
        }
        return userReports;
    }

    @Override
    public List<UserReport> findReportsByState(boolean isProcessed) throws DaoException {
        List<UserReport> userReports = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_REPORTS_BY_AVAILABILITY)) {
            statement.setByte(1, (byte) (isProcessed ? 1 : 0));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userReports.add(createUserReportFromResultSet(resultSet, false));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding user reports by state.", e);
        }
        return userReports;
    }

    private UserReport createUserReportFromResultSet(ResultSet resultSet, boolean areMsgAndResponsePresent) throws SQLException {
        long id = resultSet.getLong(reportIdCol);
        boolean isProcessed = resultSet.getByte(isProcessedCol) == 1;
        String subject = resultSet.getString(subjectCol);
        String creationDate = resultSet.getString(creationDateCol);

        long userId = resultSet.getLong(userIdCol);
        String username = resultSet.getString(usernameCol);
        String email = resultSet.getString(emailCol);
        UserRole role = UserRole.valueOf(resultSet.getString(roleCol).toUpperCase());

        UserReport report = new UserReport(id, isProcessed, subject, creationDate, new User(userId, username, email, role));

        if(areMsgAndResponsePresent) {
            report.setResponse(resultSet.getString(responseCol));
            report.setMessage(resultSet.getString(messageCol));
        }

        return report;
    }

}
