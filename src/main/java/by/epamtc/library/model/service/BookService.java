package by.epamtc.library.model.service;

import by.epamtc.library.exception.ServiceException;
import by.epamtc.library.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> loadPopularBooks() throws ServiceException;
    List<Book> loadBooks() throws ServiceException;
}
