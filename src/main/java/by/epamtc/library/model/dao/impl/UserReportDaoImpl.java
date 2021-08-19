package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.UserReportDao;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.util.DateTimeHelper;

import java.sql.*;
import java.time.LocalDateTime;

public class UserReportDaoImpl implements UserReportDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public boolean userReportExists(UserReport report, long userId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_USER_REPORT_FOR_EXISTENCE)) {
            statement.setString(1, report.getSubject());
            statement.setString(2, report.getMessage());
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
            statement.setByte(1, report.isAvailable() ? (byte) 1 : 0);
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
}
