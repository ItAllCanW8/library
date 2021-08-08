package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    boolean add(Book book) throws DaoException;
    List<Book> loadPopularBooks() throws DaoException;
    List<Book> loadBooks() throws DaoException;
    Optional<Book> findBookById(long bookId) throws DaoException;
    boolean changeCover(long bookId, String path) throws DaoException;
    boolean changeAuthorPhoto(long bookId, String path) throws DaoException;
    boolean changePdf(long bookId, String path) throws DaoException;
    boolean updateBook(Book book) throws DaoException;
    boolean deleteBook(long bookId) throws DaoException;
    boolean isTitleAvailable(String title) throws DaoException;
    boolean updateAvailableQuantity(long bookId, int newQuantity) throws DaoException;
}
