package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean register(Map<String, String> fields) throws ServiceException;
    Optional<User> login(String email, String password) throws ServiceException;
    boolean changePhoto(long detailsId, String photoPath) throws ServiceException;
}