package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;

import java.util.Map;

public interface LibCoefficientsService {
    boolean updateCoefficients(int bookIssuingDaysNum, String readingRoomOpening, String readingRoomClosing)
            throws ServiceException;
    Map<String, String> loadCoefficients() throws ServiceException;
}
