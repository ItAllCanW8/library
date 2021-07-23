package by.epamtc.library.model.service.impl;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.dao.impl.UserDaoImpl;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.UserFactory;
import by.epamtc.library.model.service.UserService;
import by.epamtc.library.util.Encryptor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserServiceImpl implements UserService {
    private static final UserDao dao = UserDaoImpl.getInstance();
    private static final String PERCENT_SIGN = "%";
    private static final LibraryFactory<User> userFactory = UserFactory.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile UserService instance;

    private UserServiceImpl() {
    }

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

                if(!dao.isEmailAvailable(user.getEmail())) {
                    fields.put("email", "This email is already taken.");
                    return false;
                }

                if(!dao.isPhoneNumAvailable(user.getUserDetails().getPhoneNumber())) {
                    fields.put("phoneNumber", "This phone number is already taken.");
                    return false;
                }
                String password = fields.get("password");
                String encPass = Encryptor.encrypt(password);

                return dao.add(user, encPass);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }
}
