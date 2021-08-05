package by.epamtc.library.model.service.impl;

import by.epamtc.library.controller.attribute.JspAttribute;
import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.dao.impl.UserDaoImpl;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.UserStatus;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.UserFactory;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.model.service.validation.UserValidator;
import by.epamtc.library.util.Encryptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserServiceImpl implements UserService {
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final LibraryFactory<User> userFactory = UserFactory.getInstance();
    private static final Lock lock = new ReentrantLock();
    private static volatile UserService instance;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new UserServiceImpl();
            }
            lock.unlock();
        }
        return instance;
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
                    return userDao.findUserByEmail(email);
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
                Optional<User> userOptional = userDao.findUserById(userId);
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
    public boolean activateUser(long userId) throws ServiceException {
        try {
            return userDao.updateUserStatus(userId, UserStatus.ACTIVE);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
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
    public Optional<User> findUserById(long userId) throws ServiceException {
        try {
            Optional<User> userOptional = userDao.findUserById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (DaoException e) {
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

        System.out.println("new user "+ user);
    }
}