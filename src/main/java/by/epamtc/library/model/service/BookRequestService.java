package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;

import java.util.Map;

public interface BookRequestService {
    boolean createBookRequest(Map<String, String> fields, User reader) throws ServiceException;
}
