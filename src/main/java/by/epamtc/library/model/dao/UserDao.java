package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.User;

public interface UserDao {
    boolean isEmailAvailable(String email) throws DaoException;
    boolean isPhoneNumAvailable(String phoneNumber) throws DaoException;
    boolean add(User user, String encPass) throws DaoException;
}
