package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserDetails;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserFactory implements LibraryFactory<User> {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final UserRole DEFAULT_ROLE = UserRole.READER;
    private static final String DEFAULT_PHOTO_NAME = "default_avatar.png";
    private static final String DEFAULT_STATUS = "active";
    private static final Lock lock = new ReentrantLock();
    private static LibraryFactory<User> instance;

    private UserFactory() {
    }

    public static LibraryFactory<User> getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new UserFactory();
            }
            lock.unlock();
        }
        return instance;
    }

    @Override
    public Optional<User> create(Map<String, String> fields) {
        Optional<User> result;
//        if (isRegisterFormValid(fields)) {
            String email = fields.get("email");
            String username = fields.get("username");

            String name = fields.get("name");
            String surname = fields.get("surname");
            String dateOfBirth = fields.get("dateOfBirth");
            String phoneNumber = fields.get("phoneNumber");

//            LOGGER.info(email);
//            LOGGER.info(username);
//            LOGGER.info(name);
//            LOGGER.info(surname);
//            LOGGER.info(dateOfBirth);
//            LOGGER.info(phoneNumber);
//            LOGGER.info(LocalDate.parse(dateOfBirth));

            result = Optional.of(new User(DEFAULT_ROLE,
                    new UserDetails(name, surname, LocalDate.parse(dateOfBirth),phoneNumber, DEFAULT_PHOTO_NAME),
                    DEFAULT_STATUS, username,email));
//        }
        return result;
    }
}
