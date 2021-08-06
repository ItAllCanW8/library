package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.model.service.validation.BookValidator;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookFactory implements LibraryFactory<Book> {
    private static final Lock lock = new ReentrantLock();
    private static LibraryFactory<Book> instance;
    public static final String DEFAULT_COVER = "default_book_cover.png";
    public static final String DEFAULT_AUTHOR_PHOTO = "default_author_photo.png";
    public static final String DEFAULT_PDF = "default_book_pdf.pdf";

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
        if (BookValidator.isBookFormValid(fields)) {
            String title = fields.get(RequestParameter.BOOK_TITLE);
            String author = fields.get(RequestParameter.BOOK_AUTHOR);
            String isbn = fields.get(RequestParameter.BOOK_ISBN);
            String genre = fields.get(RequestParameter.BOOK_GENRE);
            String quantity = fields.get(RequestParameter.BOOK_QUANTITY);
            String description = fields.get(RequestParameter.BOOK_DESCRIPTION);

            result = Optional.of(new Book(title,author,isbn,quantity,genre,description,DEFAULT_PDF,
                    DEFAULT_COVER, DEFAULT_AUTHOR_PHOTO));
        }
        return result;
    }
}
