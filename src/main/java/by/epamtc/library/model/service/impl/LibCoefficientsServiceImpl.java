package by.epamtc.library.model.service.impl;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.LibCoefficientsDao;
import by.epamtc.library.model.dao.impl.LibCoefficientsDaoImpl;
import by.epamtc.library.model.service.LibCoefficientsService;

import java.util.Map;

public class LibCoefficientsServiceImpl implements LibCoefficientsService {
    private static final LibCoefficientsDao libCoefDao = LibCoefficientsDaoImpl.getInstance();

    private LibCoefficientsServiceImpl() {
    }

    private static class Holder {
        static final LibCoefficientsService INSTANCE = new LibCoefficientsServiceImpl();
    }

    public static LibCoefficientsService getInstance() {
        return LibCoefficientsServiceImpl.Holder.INSTANCE;
    }

    @Override
    public boolean updateCoefficients(int bookIssuingDaysNum, String readingRoomOpening, String readingRoomClosing) throws ServiceException {
        try{
            return libCoefDao.updateCoefficients(bookIssuingDaysNum, readingRoomOpening, readingRoomClosing);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<String, String> loadCoefficients() throws ServiceException {
        try{
            return libCoefDao.loadCoefficients();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
