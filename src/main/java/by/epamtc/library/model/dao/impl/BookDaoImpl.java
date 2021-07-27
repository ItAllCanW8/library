package by.epamtc.library.model.dao.impl;

import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookDaoImpl implements BookDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final Lock lock = new ReentrantLock();
    private static volatile BookDao instance;

    private static final Logger LOGGER = LogManager.getLogger();

    private BookDaoImpl() {
    }

    public static BookDao getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new BookDaoImpl();
            }
            lock.unlock();
        }
        return instance;
    }
    @Override
    public List<Book> loadPopularBooks() throws DaoException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_POPULAR_BOOKS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(createBookFromResultSet(resultSet));
                LOGGER.info(books.get(0));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return books;
    }

    private Book createBookFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong("book_id");
        String title = resultSet.getString("title");
        String author_pseudo = resultSet.getString("author_pseudo");
        String isbn = resultSet.getString("isbn");
        String genre = resultSet.getString("genre");
        String shortDescription = resultSet.getString("short_description");
        String pdf = resultSet.getString("pdf");
        String img = resultSet.getString("img");
        int availableQuantity = resultSet.getInt("available_quantity");

        return (new Book(id, title, author_pseudo, isbn, availableQuantity, genre, shortDescription,pdf,img));
    }
}
