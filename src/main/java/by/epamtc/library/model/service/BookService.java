package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {
    boolean addBook(Map<String, String> fields) throws ServiceException;
    List<Book> loadPopularBooks() throws ServiceException;
    List<Book> loadBooks() throws ServiceException;
    Optional<Book> findBookById(long bookId) throws ServiceException;
}
