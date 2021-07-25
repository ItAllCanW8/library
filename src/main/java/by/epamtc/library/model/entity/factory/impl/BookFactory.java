package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.factory.LibraryFactory;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookFactory implements LibraryFactory<Book> {
    private static final Lock lock = new ReentrantLock();
    private static LibraryFactory<Book> instance;

    private BookFactory() {
    }

    public static LibraryFactory<Book> getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new BookFactory();
            }
            lock.unlock();
        }
        return instance;
    }
    @Override
    public Optional<Book> create(Map<String, String> fields) {
        Optional<Book> result = Optional.empty();
//        if (isVacancyFormValid(fields)) {
//        String title = fields.get();
//
//            String position = fields.get(RequestParameter.POSITION);
//            String description = fields.get(RequestParameter.DESCRIPTION);
//            String country = fields.get(RequestParameter.COUNTRY);
//            String city = fields.get(RequestParameter.CITY);
//            LocalDate creationDate = LocalDate.now();
//            result = Optional.of(new Vacancy(DEFAULT_AVAILABILITY_VALUE, position, description, country, city, creationDate));
//        }
        return result;
    }
}
