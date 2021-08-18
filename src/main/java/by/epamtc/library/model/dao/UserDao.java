package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean isEmailAvailable(String email) throws DaoException;
    boolean isPhoneNumAvailable(String phoneNum) throws DaoException;
    Optional<String> findPasswordByEmail(String email) throws DaoException;
    Optional<String> findEmailById(long userId) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    boolean add(User user, String encPass) throws DaoException;
    boolean changePhoto(long detailsId, String photoPath) throws DaoException;
    Optional<User> findUserById(long userId) throws DaoException;
    boolean updateProfile(User user) throws DaoException;
    boolean updatePassword(long userId, String newPassword) throws DaoException;
    boolean updateUserStatus(long userId, UserStatus newStatus) throws DaoException;
    boolean updateUserRole(long userId, UserRole newRole) throws DaoException;
    List<User> findAllUsers() throws DaoException;
    List<User> findUsersByRole(String role) throws DaoException;
    List<User> findUsersByStatus(UserStatus userStatus) throws DaoException;
}

