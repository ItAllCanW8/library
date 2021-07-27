package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> loadPopularBooks() throws DaoException;
    List<Book> loadBooks() throws DaoException;
}
