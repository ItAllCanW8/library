package by.epamtc.library.model.service.impl;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.UserReportDao;
import by.epamtc.library.model.dao.factory.DaoFactory;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.UserReportFactory;
import by.epamtc.library.model.service.UserReportService;
import by.epamtc.library.model.service.validation.UserReportValidator;
import by.epamtc.library.util.SortingHelper;

import java.util.ArrayList;
import java.util.List;
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
                return (!userReportDao.userReportExists(report.getMessage(),report.getSubject(), userId) && userReportDao.add(report, userId));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<UserReport> loadUserReports() throws ServiceException {
        try {
            return userReportDao.loadUserReports();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<UserReport> findUserReportById(long reportId) throws ServiceException {
        try {
            Optional<UserReport> reportOptional = userReportDao.findUserReportById(reportId);
            if (reportOptional.isPresent()) {
                UserReport report = reportOptional.get();
                reportOptional = Optional.of(report);
            }
            return reportOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createResponse(long reportId, String response) throws ServiceException {
        try {
            if (UserReportValidator.isResponseValid(response)) {
                Optional<UserReport> reportOptional = userReportDao.findUserReportById(reportId);
                if (reportOptional.isPresent()) {
                    return userReportDao.updateUserReportResponse(reportId, response);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<UserReport> sort(String sortingField, String sortingOrder) throws ServiceException {
        try {
            SortingHelper.SortingColumn sortingColumn = SortingHelper.SortingColumn.fromString(sortingField);
            SortingHelper.SortingOrderType sortingOrderType = SortingHelper.SortingOrderType.fromString(sortingOrder);

            if(sortingColumn != null && sortingOrderType != null)
                return userReportDao.sort(sortingColumn, sortingOrderType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return new ArrayList<>(0);
    }
}
