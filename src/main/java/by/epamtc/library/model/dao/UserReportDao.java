package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.UserReport;

public interface UserReportDao {
    boolean userReportExists(UserReport report, long userId) throws DaoException;
    boolean add(UserReport report, long userId) throws DaoException;
}
