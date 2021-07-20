package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;

import java.util.Map;

public interface UserService {
    boolean register(Map<String, String> fields) throws ServiceException;
}