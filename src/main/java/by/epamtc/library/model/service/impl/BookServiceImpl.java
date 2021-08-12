package by.epamtc.library.model.service.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.dao.BookDao;
import by.epamtc.library.model.dao.impl.BookDaoImpl;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.entity.factory.impl.BookFactory;
import by.epamtc.library.model.service.BookService;
import by.epamtc.library.model.service.validation.BookValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private static final BookDao bookDao = BookDaoImpl.getInstance();
    private static final LibraryFactory<Book> bookFactory = BookFactory.getInstance();

    private BookServiceImpl() {
    }
    private static class Holder {
        static final BookService INSTANCE = new BookServiceImpl();
    }

    public static BookService getInstance() {
        return BookServiceImpl.Holder.INSTANCE;
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
    public Optional<Book> findBookById(long bookId) throws ServiceException {
        try {
            Optional<Book> bookOptional = bookDao.findBookById(bookId);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                bookOptional = Optional.of(book);
            }
            return bookOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> findBookCoverById(long bookId) throws ServiceException {
        try {
            Optional<String> bookCoverOptional = bookDao.findBookCoverById(bookId);
            if (bookCoverOptional.isPresent()) {
                String bookCover = bookCoverOptional.get();
                bookCoverOptional = Optional.of(bookCover);
            }
            return bookCoverOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> findBookPdfById(long bookId) throws ServiceException {
        try {
            Optional<String> bookPdfOptional = bookDao.findBookPdfById(bookId);
            if (bookPdfOptional.isPresent()) {
                String bookPdf = bookPdfOptional.get();
                bookPdfOptional = Optional.of(bookPdf);
            }
            return bookPdfOptional;
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
    public boolean isTitleAvailable(String title) throws ServiceException {
        try {
            return (bookDao.isTitleAvailable(title));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateBook(long bookId, Map<String, String> newFields) throws ServiceException {
        try {
            if (BookValidator.isBookFormValid(newFields)) {
                Optional<Book> bookOptional = bookDao.findBookById(bookId);
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
    public boolean changePdf(long bookId, String pdfPath) throws ServiceException {
        try {
            findBookById(bookId).get().setPdf(pdfPath);
            return (bookDao.changePdf(bookId, pdfPath));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void updateBookInfo(Book book, Map<String, String> fields) {
        String newTitle = fields.get(RequestParameter.BOOK_TITLE);
        String newAuthor = fields.get(RequestParameter.BOOK_AUTHOR);
        String newIsbn = fields.get(RequestParameter.BOOK_ISBN);
        String newQuantity = fields.get(RequestParameter.BOOK_QUANTITY);
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
