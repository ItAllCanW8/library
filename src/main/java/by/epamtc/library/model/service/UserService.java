package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserRole;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean register(Map<String, String> fields) throws ServiceException;
    Optional<User> login(String email, String password) throws ServiceException;
    boolean changePhoto(long detailsId, String photoPath) throws ServiceException;
    boolean isEmailAvailable(String email) throws ServiceException;
    Optional<User> updateProfile(long userId, Map<String, String> newFields) throws ServiceException;
    boolean changePassword(long userId, Map<String, String> fields) throws ServiceException;
    boolean deactivateUser(long userId) throws ServiceException;
    boolean activateUser(long userId) throws ServiceException;
    boolean changeRoleToLibrarian(long userId) throws ServiceException;
    boolean changeRoleToReader(long userId) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    Optional<User> findUserById(long userId) throws ServiceException;
}