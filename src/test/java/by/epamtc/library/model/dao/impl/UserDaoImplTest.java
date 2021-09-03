package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.UserDao;
import by.epamtc.library.model.entity.User;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();

    @BeforeAll
    static void setUpConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().init();
    }

    @AfterAll
    static void tearDown() throws ConnectionPoolException {
        ConnectionPool.getInstance().dispose();
    }

    @Test
    @Order(1)
    void findById() throws DaoException {
        Optional<User> fromDB = userDao.findByEmail("miponchik.a@gmail.com");
        if(fromDB.isPresent()){
            User user = fromDB.get();
            assertEquals(user.getUsername(), "Reader");
        }
    }

    @Test
    @Order(2)
    void findAllUsers() throws DaoException {
        List<User> users = userDao.findAllUsers();
        assert(users.size() > 0);
    }
}
