package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.util.SortingHelper;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    boolean add(Book book) throws DaoException;
    List<Book> loadPopularBooks() throws DaoException;
    List<Book> loadBooks() throws DaoException;
    List<Book> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType)
            throws DaoException;
    Optional<Book> findBookById(long bookId) throws DaoException;
    Optional<String> findBookCoverById(long bookId) throws DaoException;
    Optional<String> findBookPdfById(long bookId) throws DaoException;
    List<Book> findBooksByKeyword(String keyword) throws DaoException;
    int findBookQuantityById(long bookId) throws DaoException;
    boolean changeCover(long bookId, String path) throws DaoException;
    boolean changeAuthorPhoto(long bookId, String path) throws DaoException;
    boolean changePdf(long bookId, String path) throws DaoException;
    boolean updateBook(Book book) throws DaoException;
    boolean deleteBook(long bookId) throws DaoException;
    boolean isTitleAvailable(String title) throws DaoException;
    boolean updateAvailableQuantity(long bookId, short newQuantity) throws DaoException;
}
