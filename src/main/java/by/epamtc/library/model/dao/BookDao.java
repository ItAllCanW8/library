package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.util.SortingHelper;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with books table.
 *
 * @author Artur Mironchik
 */
public interface BookDao {
    /**
     * Adds book to the table.
     *
     * @param book Book object.
     * @return boolean value. True if the book has been added, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean add(Book book) throws DaoException;

    /**
     * Loads popular books.
     *
     * @return List object of books.
     * @throws DaoException if the database throws SQLException.
     */
    List<Book> loadPopularBooks() throws DaoException;

    /**
     * Loads all books.
     *
     * @return List object of books.
     * @throws DaoException if the database throws SQLException.
     */
    List<Book> loadBooks() throws DaoException;

    /**
     * Sorts books.
     *
     * @param sortingColumn SortingColumn object of books sorting column;
     * @param sortingOrderType SortingOrderType object of sorting order type;
     * @return List object of books.
     * @throws DaoException if the database throws SQLException.
     */
    List<Book> sort(SortingHelper.SortingColumn sortingColumn, SortingHelper.SortingOrderType sortingOrderType)
            throws DaoException;

    /**
     * Finds book by id.
     *
     * @param bookId long value of book id.
     * @return Optional object of book if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Book> findBookById(long bookId) throws DaoException;

    /**
     * Finds book cover by id.
     *
     * @param bookId long value of book id.
     * @return Optional object of String if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> findBookCoverById(long bookId) throws DaoException;

    /**
     * Finds book pdf by id.
     *
     * @param bookId long value of book id.
     * @return Optional object of String if exists, Optional.empty() otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    Optional<String> findBookPdfById(long bookId) throws DaoException;

    /**
     * Finds books by keyword.
     *
     * @param keyword String object. Key word used to find books.
     * @return List object of books.
     * @throws DaoException if the database throws SQLException.
     */
    List<Book> findBooksByKeyword(String keyword) throws DaoException;

    /**
     * Finds books by genre.
     *
     * @param genre String object. Genre used to find books.
     * @return List object of books.
     * @throws DaoException if the database throws SQLException.
     */
    List<Book> findBooksByGenre(String genre) throws DaoException;

    /**
     * Finds books by author.
     *
     * @param author String object. Author name used to find books.
     * @return List object of books.
     * @throws DaoException if the database throws SQLException.
     */
    List<Book> findBooksByAuthor(String author) throws DaoException;

    /**
     * Changes book cover.
     *
     * @param bookId long value of book id.
     * @param path String object of book cover path.
     * @return true if book cover was changed, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean changeCover(long bookId, String path) throws DaoException;

    /**
     * Changes book author photo.
     *
     * @param bookId long value of book id.
     * @param path String object of book author photo path.
     * @return true if author photo was changed, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean changeAuthorPhoto(long bookId, String path) throws DaoException;

    /**
     * Changes book pdf.
     *
     * @param bookId long value of book id.
     * @param path String object of book pdf path.
     * @return true if book pdf was changed, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean changePdf(long bookId, String path) throws DaoException;

    /**
     * Updates book.
     *
     * @param book Book object.
     * @return boolean value. True if the book has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateBook(Book book) throws DaoException;

    /**
     * Deletes book.
     *
     * @param bookId long value of book id.
     * @return boolean value. True if the book has been deleted, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean deleteBook(long bookId) throws DaoException;

    /**
     * Checks if title is available.
     *
     * @param title String object of book's title.
     * @return boolean value. True if the title is available, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean isTitleAvailable(String title) throws DaoException;

    /**
     * Updates book's available quantity.
     *
     * @param bookId long value of book's id.
     * @param newQuantity short value of book's new quantity;
     * @return boolean value. True if the quantity has been updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateAvailableQuantity(long bookId, short newQuantity) throws DaoException;
}