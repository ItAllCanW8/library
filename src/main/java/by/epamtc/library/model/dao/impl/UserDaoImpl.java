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
    private static final String INVALID_DETAILS_ERROR_MSG = "Invalid user details.";

    private UserDaoImpl() {
    }

    private static class Holder {
        static final UserDao INSTANCE = new UserDaoImpl();
    }

    public static UserDao getInstance() {
        return UserDaoImpl.Holder.INSTANCE;
    }

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
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
            throw new DaoException(e);
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

            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
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
                            orElseThrow(() -> new DaoException(INVALID_DETAILS_ERROR_MSG)));
                    insertUserSt.setLong(6, findRoleId(user.getRole(), connection).
                            orElseThrow(() -> new DaoException(INVALID_ROLE_ERROR_MSG)));

                    return (insertUserSt.executeUpdate() == 1);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean changePhoto(long detailsId, String photoPath) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_PHOTO)) {
            statement.setString(1, photoPath);
            statement.setLong(2, detailsId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
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
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateProfile(User user) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement updateUserStatement = connection.prepareStatement(SqlQuery.UPDATE_USER)) {
            updateUserStatement.setString(1, user.getUsername());
            updateUserStatement.setString(2, user.getEmail());
            updateUserStatement.setLong(3, user.getId());

            if (updateUserStatement.executeUpdate() == 1) {
                try (PreparedStatement updateUserDetailsSt = connection.prepareStatement(SqlQuery.UPDATE_USER_DETAILS)) {
                    updateUserDetailsSt.setString(1, user.getUserDetails().getName());
                    updateUserDetailsSt.setString(2, user.getUserDetails().getSurname());
                    updateUserDetailsSt.setDate(3, Date.valueOf(user.getUserDetails().getDateOfBirth()));
                    updateUserDetailsSt.setString(4, user.getUserDetails().getPhoneNumber());
                    updateUserDetailsSt.setLong(5, user.getUserDetails().getId());

                    return (updateUserDetailsSt.executeUpdate() == 1);
                }
            } else {
                throw new DaoException("Error updating user profile");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updatePassword(long userId, String newPassword) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setLong(2, userId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateUserStatus(long userId, UserStatus newStatus) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_STATUS)) {
            statement.setString(1, newStatus.getValue());
            statement.setLong(2, userId);

            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateUserRole(long userId, UserRole newRole) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_ROLE)) {
            statement.setLong(1, findRoleId(newRole, connection).orElseThrow(()
                    -> new DaoException(INVALID_ROLE_ERROR_MSG)));
            statement.setLong(2, userId);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong("user_id");
        long detailsId = resultSet.getLong("details_id_fk");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
        String phoneNumber = resultSet.getString("phone_number");
        String photoPath = resultSet.getString("photo_path");
        UserStatus status = UserStatus.fromString(resultSet.getString("status"));
        UserRole role = UserRole.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT));
        return (new User(userId, role, new UserDetails(detailsId, name, surname, dateOfBirth, phoneNumber, photoPath),
                status, username, email));
    }
}
