package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserDetails;
import by.epamtc.library.model.entity.UserRole;
import by.epamtc.library.model.entity.UserStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String INVALID_ROLE_ERROR_MSG = "Invalid user role.";

    private static final String userIdCol = "user_id";
    private static final String detailsIdCol = "details_id_fk";
    private static final String usernameCol = "username";
    private static final String emailCol = "email";
    private static final String nameCol = "name";
    private static final String surnameCol = "surname";
    private static final String dateOfBirthCol = "date_of_birth";
    private static final String phoneNumberCol = "phone_number";
    private static final String photoPathCol = "photo_path";
    private static final String statusCol = "status";
    private static final String roleCol = "role";

    public UserDaoImpl() {
    }

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error checking if user's email is available", e);
        }
    }

    @Override
    public boolean isPhoneNumAvailable(String phoneNum) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_PHONE_NUM)) {
            statement.setString(1, phoneNum);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error checking if user's phone number is available", e);
        }
    }

    private boolean insertUserDetails(UserDetails userDetails, Connection connection) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SqlQuery.INSERT_USER_DETAILS)) {

            statement.setString(1, userDetails.getName());
            statement.setString(2, userDetails.getSurname());
            statement.setDate(3, Date.valueOf(userDetails.getDateOfBirth()));
            statement.setString(4, userDetails.getPhoneNumber());
            statement.setString(5, userDetails.getPhotoPath());

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException e) {
            throw new DaoException("Error inserting user's details", e);
        }
    }

    private Optional<Long> findRoleId(UserRole role, Connection connection) throws SQLException, ConnectionPoolException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ROLE_ID_BY_NAME)) {
            statement.setString(1, role.name());
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getLong(1)) : Optional.empty());
        }
    }

    private Optional<Long> findDetailsId(String phoneNum, Connection connection) throws SQLException, ConnectionPoolException {
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_DETAILS_ID_BY_PHONE)) {
            statement.setString(1, phoneNum);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getLong(1)) : Optional.empty());
        }
    }

    @Override
    public boolean add(User user, String encPass) throws DaoException {
        try (Connection connection = pool.takeConnection()) {
            if (insertUserDetails(user.getUserDetails(), connection)) {
                try (PreparedStatement insertUserSt = connection.prepareStatement(SqlQuery.INSERT_USER)) {
                    insertUserSt.setString(1, user.getUsername());
                    insertUserSt.setString(2, user.getEmail());
                    insertUserSt.setString(3, encPass);
                    insertUserSt.setString(4, user.getStatus().getValue());

                    insertUserSt.setLong(5, findDetailsId(user.getUserDetails().getPhoneNumber(), connection).
                            orElseThrow(() -> new DaoException("Error adding user. Invalid user details.")));
                    insertUserSt.setLong(6, findRoleId(user.getRole(), connection).
                            orElseThrow(() -> new DaoException(INVALID_ROLE_ERROR_MSG)));

                    insertUserSt.execute();

                    return insertUserSt.getUpdateCount() == 1;
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error adding user", e);
        }
        return false;
    }

    @Override
    public boolean changePhoto(long detailsId, String photoPath) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_PHOTO)) {
            statement.setString(1, photoPath);
            statement.setLong(2, detailsId);
            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error changing user's photo", e);
        }
    }

    @Override
    public Optional<User> findUserById(long userId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(createUserFromResultSet(resultSet)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding user by id " + userId, e);
        }
    }

    @Override
    public boolean updateProfile(User user) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement updateUserStatement = connection.prepareStatement(SqlQuery.UPDATE_USER)) {
            updateUserStatement.setString(1, user.getUsername());
            updateUserStatement.setString(2, user.getEmail());
            updateUserStatement.setLong(3, user.getId());
            updateUserStatement.execute();

            if (updateUserStatement.getUpdateCount() == 1) {
                try (PreparedStatement updateUserDetailsSt = connection.prepareStatement(SqlQuery.UPDATE_USER_DETAILS)) {
                    updateUserDetailsSt.setString(1, user.getUserDetails().getName());
                    updateUserDetailsSt.setString(2, user.getUserDetails().getSurname());
                    updateUserDetailsSt.setDate(3, Date.valueOf(user.getUserDetails().getDateOfBirth()));
                    updateUserDetailsSt.setString(4, user.getUserDetails().getPhoneNumber());
                    updateUserDetailsSt.setLong(5, user.getUserDetails().getId());

                    updateUserDetailsSt.execute();

                    return updateUserDetailsSt.getUpdateCount() == 1;
                }
            } else {
                throw new DaoException("Error updating user profile");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating user profile", e);
        }
    }

    @Override
    public boolean updatePassword(long userId, String newPassword) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setLong(2, userId);
            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating user password",e);
        }
    }

    @Override
    public boolean updateUserStatus(long userId, UserStatus newStatus) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_STATUS)) {
            statement.setString(1, newStatus.getValue());
            statement.setLong(2, userId);

            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating user status",e);
        }
    }

    @Override
    public boolean updateUserRole(long userId, UserRole newRole) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_ROLE)) {
            statement.setLong(1, findRoleId(newRole, connection).orElseThrow(()
                    -> new DaoException(INVALID_ROLE_ERROR_MSG)));
            statement.setLong(2, userId);
            statement.execute();

            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating user role", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_USERS);
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding all users",e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByRole(String role) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USERS_BY_ROLE)) {
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding users by role",e);
        }
        return users;
    }

    @Override
    public List<User> findUsersByStatus(UserStatus userStatus) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USERS_BY_STATUS)) {
            statement.setString(1, userStatus.getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding users by status",e);
        }
        return users;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_PASSWORD)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getString(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding user password by email",e);
        }
    }

    @Override
    public Optional<String> findEmailById(long userId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_EMAIL_BY_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getString(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding email by user id " + userId,e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(createUserFromResultSet(resultSet)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error finding user by email " + email,e);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong(userIdCol);
        long detailsId = resultSet.getLong(detailsIdCol);
        String username = resultSet.getString(usernameCol);
        String email = resultSet.getString(emailCol);
        String name = resultSet.getString(nameCol);
        String surname = resultSet.getString(surnameCol);
        LocalDate dateOfBirth = resultSet.getDate(dateOfBirthCol).toLocalDate();
        String phoneNumber = resultSet.getString(phoneNumberCol);
        String photoPath = resultSet.getString(photoPathCol);
        UserStatus status = UserStatus.fromString(resultSet.getString(statusCol));
        UserRole role = UserRole.valueOf(resultSet.getString(roleCol).toUpperCase(Locale.ROOT));
        return (new User(userId, role, new UserDetails(detailsId, name, surname, dateOfBirth, phoneNumber, photoPath),
                status, username, email));
    }
}
