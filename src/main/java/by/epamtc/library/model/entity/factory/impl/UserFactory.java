package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserDetails;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.UserStatus;
import by.epamtc.library.model.entity.factory.EntityFactory;
import by.epamtc.library.model.service.validation.UserValidator;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * EntityFactory implementation used to create a User object.
 *
 * @author Artur Mironchik
 */
public class UserFactory implements EntityFactory<User> {
    private static final UserRole DEFAULT_ROLE = UserRole.READER;
    private static final String DEFAULT_PHOTO_NAME = "default_avatar.png";
    private static final UserStatus DEFAULT_STATUS = UserStatus.ACTIVE;

    private UserFactory() {
    }

    private static class Holder {
        /**
         * The Instance.
         */
        static final EntityFactory<User> INSTANCE = new UserFactory();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static EntityFactory<User> getInstance() { return UserFactory.Holder.INSTANCE; }

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
