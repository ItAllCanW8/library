package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {
    boolean addBook(Map<String, String> fields) throws ServiceException;
    List<Book> loadPopularBooks() throws ServiceException;
    List<Book> loadBooks() throws ServiceException;
    Optional<Book> findBookById(long bookId) throws ServiceException;
    Optional<String> findBookCoverById(long bookId) throws ServiceException;
    Optional<String> findBookPdfById(long bookId) throws ServiceException;
    boolean changeCover(long bookId, String path) throws ServiceException;
    boolean changeAuthorPhoto(long bookId, String path) throws ServiceException;
    boolean changePdf(long bookId, String pdfPath) throws ServiceException;
    boolean isTitleAvailable(String title) throws ServiceException;
    boolean updateBook(long bookId, Map<String, String> newFields) throws ServiceException;
    boolean deleteBook(long bookId) throws ServiceException;

}
