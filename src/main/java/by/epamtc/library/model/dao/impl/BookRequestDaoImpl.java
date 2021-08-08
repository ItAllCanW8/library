package by.epamtc.library.model.dao.impl;

import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.BookRequestDao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookRequestDaoImpl implements BookRequestDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private BookRequestDaoImpl() {
    }

    private static class Holder {
        static final BookRequestDaoImpl INSTANCE = new BookRequestDaoImpl();
    }

    public static BookRequestDaoImpl getInstance() {
        return BookRequestDaoImpl.Holder.INSTANCE;
    }
}
