package by.epamtc.library.model.dao;

import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.entity.BookRequest;

public interface BookRequestDao {
    boolean bookRequestExists(BookRequest request) throws DaoException;
    boolean add(BookRequest request) throws DaoException;
}
