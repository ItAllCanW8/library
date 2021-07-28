package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> loadPopularBooks() throws ServiceException;
    List<Book> loadBooks() throws ServiceException;
    Optional<Book> findBookById(long bookId) throws ServiceException;
}
