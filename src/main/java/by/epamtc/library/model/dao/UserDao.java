package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean isEmailAvailable(String email) throws DaoException;
    boolean isPhoneNumAvailable(String phoneNumber) throws DaoException;
    Optional<String> findPasswordByEmail(String email) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    boolean add(User user, String encPass) throws DaoException;
    boolean changePhoto(long detailsId, String photoPath) throws DaoException;
}
