package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.entity.User;
import by.epamtc.library.model.entity.UserDetails;
import by.epamtc.library.model.entity.UserRole;

import java.sql.*;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserDaoImpl implements UserDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile UserDao instance;

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
                        orElseThrow(() -> new DaoException("Invalid user details")));
                statement.setLong(6, findRoleId(user.getRole()).
                        orElseThrow(() -> new DaoException("Invalid role")));

                return (statement.executeUpdate() == 1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return false;
    }
}
