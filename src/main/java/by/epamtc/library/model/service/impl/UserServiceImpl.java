package by.epamtc.library.model.service.impl;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.dao.impl.DaoFactory;
import by.epamtc.library.model.entity.LoggingNote;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.UserStatus;
import by.epamtc.library.model.entity.factory.EntityFactory;
import by.epamtc.library.model.entity.factory.impl.UserFactory;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.validation.UserValidator;
import by.epamtc.library.util.Encryptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * UserService implementation.
 *
 * @author Artur Mironchik
 */
public class UserServiceImpl implements UserService {
    private static final UserDao userDao = DaoFactory.getInstance().getUserDao();
    private static final EntityFactory<User> userFactory = UserFactory.getInstance();

    /**
     * Instantiates a new User service.
     */
    UserServiceImpl() {
    }

    @Override
    public boolean register(Map<String, String> fields) throws ServiceException {
        try {
            Optional<User> userOptional = userFactory.create(fields);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if(!userDao.isEmailAvailable(user.getEmail())) {
                    fields.put(RequestParameter.EMAIL, JspAttribute.EMAIL_AVAILABLE_ERROR_MSG);
                    return false;
                }

                if(!userDao.isPhoneNumAvailable(user.getUserDetails().getPhoneNumber())) {
                    fields.put(RequestParameter.PHONE_NUMBER, JspAttribute.PHONE_AVAILABLE_ERROR_MSG);
                    return false;
                }

                String password = fields.get(RequestParameter.PASSWORD);
                String encPass = Encryptor.encrypt(password);

                return userDao.add(user, encPass);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        try {
            if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password) && !userDao.isEmailAvailable(email)) {
                Optional<String> passFromDb = userDao.findPasswordByEmail(email);
                if (passFromDb.isPresent() && Encryptor.check(password, passFromDb.get())) {
                    return userDao.findByEmail(email);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean changePhoto(long detailsId, String photoPath) throws ServiceException {
        try {
            return (UserValidator.isPhotoNameValid(photoPath) && userDao.changePhoto(detailsId, photoPath));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return (UserValidator.isEmailValid(email) && userDao.isEmailAvailable(email));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isPhoneNumAvailable(String phoneNum) throws ServiceException {
        try {
            return (UserValidator.isPhoneNumberValid(phoneNum) && userDao.isPhoneNumAvailable(phoneNum));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> updateProfile(long userId, Map<String, String> newFields) throws ServiceException {
        try {
            if (UserValidator.isEditFormValid(newFields)) {
                Optional<User> userOptional = userDao.findById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();

                    boolean isEmailAvailable = user.getEmail().equals(newFields.get(RequestParameter.EMAIL)) ||
                            userDao.isEmailAvailable(newFields.get(RequestParameter.EMAIL));
                    boolean isPhoneNumAvailable = user.getUserDetails().getPhoneNumber().
                            equals(newFields.get(RequestParameter.PHONE_NUMBER)) ||
                            userDao.isPhoneNumAvailable(newFields.get(RequestParameter.PHONE_NUMBER));

                    if (isEmailAvailable && isPhoneNumAvailable) {
                        updateUserFields(user, newFields);
                        return (userDao.updateProfile(user) ? Optional.of(user) : Optional.empty());
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean changePassword(long userId, Map<String, String> fields) throws ServiceException {
        try {
            if (UserValidator.isChangePasswordFormValid(fields)) {
                String newPassword = fields.get(RequestParameter.NEW_PASSWORD);
                String encryptedPassword = Encryptor.encrypt(newPassword);
                return userDao.updatePassword(userId, encryptedPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean deactivateUser(long userId) throws ServiceException {
        try {
            return userDao.updateUserStatus(userId, UserStatus.DEACTIVATED);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeUserStatus(long userId, String newStatusStr) throws ServiceException {
        try {
            UserStatus newStatus = UserStatus.fromString(newStatusStr);

            if(newStatus != null) {
                return userDao.updateUserStatus(userId, newStatus);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean changeRoleToLibrarian(long userId) throws ServiceException {
        try {
            return userDao.updateUserRole(userId, UserRole.LIBRARIAN);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeRoleToReader(long userId) throws ServiceException {
        try {
            return userDao.updateUserRole(userId, UserRole.READER);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByRole(String role) throws ServiceException{
        try {
            if(role != null && !role.isEmpty())
                return userDao.findUsersByRole(role);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<User> findUsersByStatus(String status) throws ServiceException {
        try {
            UserStatus userStatus = UserStatus.fromString(status);

            if(userStatus != null)
                return userDao.findUsersByStatus(userStatus);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return new ArrayList<>(0);
    }

    @Override
    public Optional<User> findById(long userId) throws ServiceException {
        try {
            Optional<User> userOptional = userDao.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> findEmailById(long userId) throws ServiceException {
        try {
            Optional<String> emailOptional = userDao.findEmailById(userId);
            if (emailOptional.isPresent()) {
                String email = emailOptional.get();
                emailOptional = Optional.of(email);
            }
            return emailOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> findStatusById(long userId) throws ServiceException {
        try {
            Optional<String> statusOptional = userDao.findStatusById(userId);
            if (statusOptional.isPresent()) {
                String status = statusOptional.get();
                statusOptional = Optional.of(status);
            }
            return statusOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<LoggingNote> loadLoggingNotes() throws ServiceException {
        try{
            return userDao.loadLoggingNotes();
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    private void updateUserFields(User user, Map<String, String> fields) {
        String newUsername = fields.get(RequestParameter.USERNAME);
        user.setUsername(newUsername);

        String newName = fields.get(RequestParameter.NAME);
        user.getUserDetails().setName(newName);

        String newSurname = fields.get(RequestParameter.SURNAME);
        user.getUserDetails().setSurname(newSurname);

        LocalDate newDateOfBirth = LocalDate.parse(fields.get(RequestParameter.DATE_OF_BIRTH));
        user.getUserDetails().setDateOfBirth(newDateOfBirth);

        String newPhoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
        user.getUserDetails().setPhoneNumber(newPhoneNumber);

        String newEmail = fields.get(RequestParameter.EMAIL);
        user.setEmail(newEmail);
    }
}