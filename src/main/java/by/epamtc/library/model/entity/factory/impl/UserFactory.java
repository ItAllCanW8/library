package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserDetails;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.UserStatus;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.service.validation.UserValidator;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserFactory implements LibraryFactory<User> {
    private static final UserRole DEFAULT_ROLE = UserRole.READER;
    private static final String DEFAULT_PHOTO_NAME = "default_avatar.png";
    private static final UserStatus DEFAULT_STATUS = UserStatus.ACTIVE;
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
        Optional<User> result = Optional.empty();

        if (UserValidator.isRegisterFormValid(fields)) {
            String email = fields.get(RequestParameter.EMAIL);
            String username = fields.get(RequestParameter.USERNAME);

            String name = fields.get(RequestParameter.NAME);
            String surname = fields.get(RequestParameter.SURNAME);
            String dateOfBirth = fields.get(RequestParameter.DATE_OF_BIRTH);
            String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);

            result = Optional.of(new User(DEFAULT_ROLE,
                    new UserDetails(name, surname, LocalDate.parse(dateOfBirth),phoneNumber, DEFAULT_PHOTO_NAME),
                    DEFAULT_STATUS, username, email));
        }
        return result;
    }
}
