package by.epamtc.library.model.service.impl;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.dao.impl.UserDaoImpl;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.service.UserService;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserServiceImpl implements UserService {
    private static final UserDao dao = UserDaoImpl.getInstance();
    private static final String PERCENT_SIGN = "%";
    private static final EntityFactory<User> userFactory = UserFactory.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile UserService instance;

    /**
     * Constructs a UserServiceImpl object.
     */
    private UserServiceImpl() {
    }

    /**
     * Returns a UserService object.
     */
    public static UserService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean register(Map<String, String> fields) throws ServiceException {
        try {
            Optional<User> userOptional = userFactory.create(fields);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (dao.isEmailAvailable(user.getEmail())) {
                    String password = fields.get(RequestParameter.PASSWORD);
                    String encryptedPassword = Encryptor.encrypt(password);
                    return dao.add(user, encryptedPassword);
                } else {
                    fields.put(RequestParameter.EMAIL, JspAttribute.EMAIL_AVAILABLE_ERROR_MESSAGE);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }
}
