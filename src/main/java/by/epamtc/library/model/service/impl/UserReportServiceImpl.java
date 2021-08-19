package by.epamtc.library.model.service.impl;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.UserReportDao;
import by.epamtc.library.model.dao.factory.DaoFactory;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.UserReportFactory;
import by.epamtc.library.model.service.UserReportService;

import java.util.Map;
import java.util.Optional;

public class UserReportServiceImpl implements UserReportService {
    private static final LibraryFactory<UserReport> userReportFactory = UserReportFactory.getInstance();
    private static final UserReportDao userReportDao = DaoFactory.getInstance().getUserReportDao();

    @Override
    public boolean createUserReport(Map<String, String> fields, long userId) throws ServiceException {
        Optional<UserReport> reportOptional = userReportFactory.create(fields);
        try {
            if (reportOptional.isPresent()) {
                UserReport report = reportOptional.get();
                return (!userReportDao.userReportExists(report, userId) && userReportDao.add(report, userId));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }
}
