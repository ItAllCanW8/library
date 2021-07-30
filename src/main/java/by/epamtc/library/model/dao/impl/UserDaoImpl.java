package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserDetails;
import by.epamtc.library.model.entity.UserRole;

import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserDaoImpl implements UserDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile UserDao instance;

    private static final String INVALID_ROLE_ERROR_MSG= "Invalid user role.";
    private static final String INVALID_DETAILS_ERROR_MSG = "Invalid user details.";

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserDaoImpl();
            }
            locker.unlock();
        }
        return instance;
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
    public boolean isPhoneNumAvailable(String phoneNumber) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_PHONE_NUM)) {
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private boolean insertUserDetails(UserDetails userDetails) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER_DETAILS)) {
            statement.setString(1, userDetails.getName());
            statement.setString(2, userDetails.getSurname());
            statement.setDate(3, Date.valueOf(userDetails.getDateOfBirth()));
            statement.setString(4, userDetails.getPhoneNumber());
            statement.setString(5, userDetails.getPhotoPath());

            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Optional<Long> findRoleId(UserRole role) throws SQLException, ConnectionPoolException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ROLE_ID_BY_NAME)) {
            statement.setString(1, role.name());
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getLong(1)) : Optional.empty());
        }
    }

    private Optional<Long> findDetailsId(String phoneNum) throws SQLException, ConnectionPoolException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_DETAILS_ID_BY_PHONE)) {
            statement.setString(1, phoneNum);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getLong(1)) : Optional.empty());
        }
    }

    @Override
    public boolean add(User user, String encPass) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, encPass);
            statement.setString(4, user.getStatus());

            if(insertUserDetails(user.getUserDetails())) {
                statement.setLong(5, findDetailsId(user.getUserDetails().getPhoneNumber()).
                        orElseThrow(() -> new DaoException(INVALID_DETAILS_ERROR_MSG)));
                statement.setLong(6, findRoleId(user.getRole()).
                        orElseThrow(() -> new DaoException(INVALID_ROLE_ERROR_MSG)));

                return (statement.executeUpdate() == 1);
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

            if(updateUserStatement.executeUpdate() == 1) {
                PreparedStatement updateUserDetailsSt = connection.prepareStatement(SqlQuery.UPDATE_USER_DETAILS);
                updateUserDetailsSt.setString(1, user.getUserDetails().getName());
                updateUserDetailsSt.setString(2, user.getUserDetails().getSurname());
                updateUserDetailsSt.setDate(3, Date.valueOf(user.getUserDetails().getDateOfBirth()));
                updateUserDetailsSt.setString(4, user.getUserDetails().getPhoneNumber());
                updateUserDetailsSt.setLong(5, user.getId());

                return (updateUserDetailsSt.executeUpdate() == 1);
            } else {
                throw new DaoException("Error updating user profile");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
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
        String status = resultSet.getString("status");
        UserRole role = UserRole.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT));
        return (new User(userId, role, new UserDetails(detailsId,name,surname,dateOfBirth,phoneNumber,photoPath),
                status,username, email));
    }
}
