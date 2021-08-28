package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface provides actions on user.
 *
 * @author Artur Mironchik
 */
public interface UserService {
    /**
     * Register boolean.
     *
     * @param fields the fields
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean register(Map<String, String> fields) throws ServiceException;

    /**
     * Login optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> login(String email, String password) throws ServiceException;

    /**
     * Change photo boolean.
     *
     * @param detailsId the details id
     * @param photoPath the photo path
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changePhoto(long detailsId, String photoPath) throws ServiceException;

    /**
     * Is email available boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isEmailAvailable(String email) throws ServiceException;

    /**
     * Is phone num available boolean.
     *
     * @param phoneNum the phone num
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isPhoneNumAvailable(String phoneNum) throws ServiceException;

    /**
     * Update profile optional.
     *
     * @param userId    the user id
     * @param newFields the new fields
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> updateProfile(long userId, Map<String, String> newFields) throws ServiceException;

    /**
     * Change password boolean.
     *
     * @param userId the user id
     * @param fields the fields
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changePassword(long userId, Map<String, String> fields) throws ServiceException;

    /**
     * Deactivate user boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deactivateUser(long userId) throws ServiceException;

    /**
     * Change user status boolean.
     *
     * @param userId       the user id
     * @param newStatusStr the new status str
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserStatus(long userId, String newStatusStr) throws ServiceException;

    /**
     * Change role to librarian boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeRoleToLibrarian(long userId) throws ServiceException;

    /**
     * Change role to reader boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeRoleToReader(long userId) throws ServiceException;

    /**
     * Find all users list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Find users by role list.
     *
     * @param role the role
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersByRole(String role) throws ServiceException;

    /**
     * Find users by status list.
     *
     * @param status the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersByStatus(String status) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findById(long userId) throws ServiceException;

    /**
     * Find email by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<String> findEmailById(long userId) throws ServiceException;

    /**
     * Find status by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<String> findStatusById(long userId) throws ServiceException;
}