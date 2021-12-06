package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.LoggingNote;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with users table.
 *
 * @author Artur Mironchik
 */
public interface UserDao {
    /**
     * Checks if email is available.
     *
     * @param email String object of user's email.
     * @return boolean value. True if the email is available, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean isEmailAvailable(String email) throws DaoException;

    /**
     * Checks if phone number is available.
     *
     * @param phoneNum String object of user's number.
     * @return boolean value. True if the number is available, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean isPhoneNumAvailable(String phoneNum) throws DaoException;

    /**
     * Finds password by user's email.
     *
     * @param email String object of user's email.
     * @return Optional object of user's password if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> findPasswordByEmail(String email) throws DaoException;

    /**
     * Finds user email by id.
     *
     * @param userId long value of user id.
     * @return Optional object of String if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> findEmailById(long userId) throws DaoException;

    /**
     * Finds user status by id.
     *
     * @param userId long value of user id.
     * @return Optional object of String if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> findStatusById(long userId) throws DaoException;

    /**
     * Finds user by email.
     *
     * @param email String object user email.
     * @return Optional object of user if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Adds user to the table.
     *
     * @param user     User object.
     * @param encPass String object of user's encrypted password.
     * @return boolean value. True if the user has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(User user, String encPass) throws DaoException;

    /**
     * Changes user's photo name.
     *
     * @param detailsId long value of user details id.
     * @param photoPath String object of user's photo name.
     * @return boolean value. True if the user's photo name has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean changePhoto(long detailsId, String photoPath) throws DaoException;

    /**
     * Finds user by id.
     *
     * @param userId long value of user's id.
     * @return Optional object of user if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findById(long userId) throws DaoException;

    /**
     * Updates user's profile.
     *
     * @param user User object.
     * @return boolean value. True if the user's profile has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateProfile(User user) throws DaoException;

    /**
     * Updates user's password.
     *
     * @param userId      long value of user's id.
     * @param newPassword String object of new user's password.
     * @return boolean value. True if the user's password has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updatePassword(long userId, String newPassword) throws DaoException;

    /**
     * Changes user's status.
     *
     * @param userId long value of user's id.
     * @param newStatus   UserStatus object of user's status.
     * @return boolean value. True if the user's role has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateUserStatus(long userId, UserStatus newStatus) throws DaoException;

    /**
     * Changes user's role.
     *
     * @param userId long value of user's id.
     * @param newRole   UserRole object of user's role.
     * @return boolean value. True if the user's role has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateUserRole(long userId, UserRole newRole) throws DaoException;

    /**
     * Finds all users.
     *
     * @return List object of users.
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Finds all users with given role.
     *
     * @param role String object of user role
     * @return List object of users.
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findUsersByRole(String role) throws DaoException;

    /**
     * Finds all users with given status.
     *
     * @param userStatus UserStatus object of user status
     * @return List object of users.
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findUsersByStatus(UserStatus userStatus) throws DaoException;

    List<LoggingNote> loadLoggingNotes() throws DaoException;
}