package by.epamtc.library.model.service.impl;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.UserReportDao;
import by.epamtc.library.model.dao.impl.DaoFactory;
import by.epamtc.library.model.entity.UserReport;
import by.epamtc.library.model.entity.factory.EntityFactory;
import by.epamtc.library.model.entity.factory.impl.UserReportFactory;
import by.epamtc.library.model.service.UserReportService;
import by.epamtc.library.model.service.validation.UserReportValidator;
import by.epamtc.library.util.SortingHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * UserReportService implementation.
 *
 * @author Artur Mironchik
 */
public class UserReportServiceImpl implements UserReportService {
    private static final EntityFactory<UserReport> userReportFactory = UserReportFactory.getInstance();
    private static final UserReportDao userReportDao = DaoFactory.getInstance().getUserReportDao();

    /**
     * Instantiates a new User report service.
     */
    UserReportServiceImpl(){}

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
    public Optional<UserReport> findById(long reportId) throws ServiceException {
        try {
            return userReportDao.findById(reportId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createResponse(long reportId, String response) throws ServiceException {
        try {
            if (UserReportValidator.isResponseValid(response)) {
                Optional<UserReport> reportOptional = userReportDao.findById(reportId);
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

    @Override
    public List<UserReport> findReportsByState(boolean isProcessed) throws ServiceException {
        try {
            return userReportDao.findReportsByState(isProcessed);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
