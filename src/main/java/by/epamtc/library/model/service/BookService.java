package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Interface provides actions on book.
 *
 * @author Artur Mironchik
 */
public interface BookService {
    /**
     * Add book boolean.
     *
     * @param fields the fields
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addBook(Map<String, String> fields) throws ServiceException;

    /**
     * Load popular books list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Book> loadPopularBooks() throws ServiceException;

    /**
     * Load books list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Book> loadBooks() throws ServiceException;

    /**
     * Sort list.
     *
     * @param sortingField the sorting field
     * @param sortingOrder the sorting order
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Book> sort(String sortingField, String sortingOrder) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param bookId the book id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Book> findById(long bookId) throws ServiceException;

    /**
     * Find books by keyword list.
     *
     * @param keyword the keyword
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Book> findBooksByKeyword(String keyword) throws ServiceException;

    /**
     * Find books by genre list.
     *
     * @param genre the genre
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Book> findBooksByGenre(String genre) throws ServiceException;

    /**
     * Find books by author list.
     *
     * @param author the author
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Book> findBooksByAuthor(String author) throws ServiceException;

    /**
     * Change cover boolean.
     *
     * @param bookId the book id
     * @param path   the path
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeCover(long bookId, String path) throws ServiceException;

    /**
     * Change author photo boolean.
     *
     * @param bookId the book id
     * @param path   the path
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeAuthorPhoto(long bookId, String path) throws ServiceException;

    /**
     * Change pdf boolean.
     *
     * @param bookId  the book id
     * @param pdfPath the pdf path
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changePdf(long bookId, String pdfPath) throws ServiceException;

    /**
     * Update book boolean.
     *
     * @param bookId    the book id
     * @param newFields the new fields
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateBook(long bookId, Map<String, String> newFields) throws ServiceException;

    /**
     * Delete book boolean.
     *
     * @param bookId the book id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteBook(long bookId) throws ServiceException;

    /**
     * Update available quantity boolean.
     *
     * @param bookId      the book id
     * @param newQuantity the new quantity
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateAvailableQuantity(long bookId, short newQuantity) throws ServiceException;
}
