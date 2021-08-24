package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;

import java.util.Map;

public interface LibCoefficientsDao {
    boolean updateCoefficients(int bookIssuingDaysNum, String readingRoomOpening, String readingRoomClosing)
            throws DaoException;
    Map<String, String> loadCoefficients() throws DaoException;
}
