package by.epamtc.library.model.service.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.impl.DaoFactory;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.factory.EntityFactory;
import by.epamtc.library.model.entity.factory.impl.BookFactory;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.validation.BookValidator;
import by.epamtc.library.util.SortingHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * BookService implementation.
 *
 * @author Artur Mironchik
 */
public class BookServiceImpl implements BookService {
    private static final BookDao bookDao = DaoFactory.getInstance().getBookDao();
    private static final EntityFactory<Book> bookFactory = BookFactory.getInstance();

    /**
     * Instantiates a new Book service.
     */
    BookServiceImpl() {
    }

    @Override
    public boolean addBook(Map<String, String> fields) throws ServiceException {
        try {
            Optional<Book> bookOptional = bookFactory.create(fields);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                if(bookDao.isTitleAvailable(fields.get(RequestParameter.BOOK_TITLE)))
                    return (bookDao.add(book));
            }
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<Book> loadPopularBooks() throws ServiceException {
        try {
            return bookDao.loadPopularBooks();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> loadBooks() throws ServiceException {
        try {
            return bookDao.loadBooks();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> sort(String sortingField, String sortingOrder) throws ServiceException {
        try {
            SortingHelper.SortingColumn sortingColumn = SortingHelper.SortingColumn.fromString(sortingField);
            SortingHelper.SortingOrderType sortingOrderType = SortingHelper.SortingOrderType.fromString(sortingOrder);

            if(sortingColumn != null && sortingOrderType != null)
                return bookDao.sort(sortingColumn, sortingOrderType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return new ArrayList<>(0);
    }

    @Override
    public Optional<Book> findById(long bookId) throws ServiceException {
        try {
            return bookDao.findById(bookId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> findBooksByKeyword(String keyword) throws ServiceException {
        try {
            return bookDao.findByKeyword(keyword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> findBooksByGenre(String genre) throws ServiceException {
        try {
            return bookDao.findBooksByGenre(genre);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws ServiceException {
        try {
            return bookDao.findBooksByAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeCover(long bookId, String path) throws ServiceException {
        try {
            return (BookValidator.isPhotoNameValid(path) && bookDao.changeCover(bookId, path));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeAuthorPhoto(long bookId, String path) throws ServiceException {
        try {
            return (BookValidator.isPhotoNameValid(path) && bookDao.changeAuthorPhoto(bookId, path));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBook(long bookId, Map<String, String> newFields) throws ServiceException {
        try {
            if (BookValidator.isBookFormValid(newFields)) {
                Optional<Book> bookOptional = bookDao.findById(bookId);
                if (bookOptional.isPresent()) {
                    Book book = bookOptional.get();

                    if(book.getTitle().equals(newFields.get(RequestParameter.BOOK_TITLE)) ||
                            bookDao.isTitleAvailable(newFields.get(RequestParameter.BOOK_TITLE))){
                        updateBookInfo(book, newFields);
                        return (bookDao.updateBook(book));
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public boolean deleteBook(long bookId) throws ServiceException {
        try {
            return (bookDao.deleteBook(bookId));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateAvailableQuantity(long bookId, short newQuantity) throws ServiceException {
        try {
            return (bookDao.updateAvailableQuantity(bookId, newQuantity));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changePdf(long bookId, String pdfPath) throws ServiceException {
        try {
            findById(bookId).get().setPdf(pdfPath);
            return (bookDao.changePdf(bookId, pdfPath));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void updateBookInfo(Book book, Map<String, String> fields) {
        String newTitle = fields.get(RequestParameter.BOOK_TITLE);
        String newAuthor = fields.get(RequestParameter.BOOK_AUTHOR);
        String newIsbn = fields.get(RequestParameter.BOOK_ISBN);
        short newQuantity = Short.parseShort(fields.get(RequestParameter.BOOK_QUANTITY));
        String newGenre = fields.get(RequestParameter.BOOK_GENRE);
        String newDescription = fields.get(RequestParameter.BOOK_DESCRIPTION);

        book.setTitle(newTitle);
        book.setAuthorPseudo(newAuthor);
        book.setIsbn(newIsbn);
        book.setAvailableQuantity(newQuantity);
        book.setGenre(newGenre);
        book.setShortDescription(newDescription);
    }
}
