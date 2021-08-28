package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.Book;
import by.epamtc.library.model.entity.factory.EntityFactory;
import by.epamtc.library.model.service.validation.BookValidator;

import java.util.Map;
import java.util.Optional;

/**
 * EntityFactory implementation used to create a Book object.
 *
 * @author Artur Mironchik
 */
public class BookFactory implements EntityFactory<Book> {
    /**
     * The constant DEFAULT_COVER.
     */
    public static final String DEFAULT_COVER = "default_book_cover.png";
    /**
     * The constant DEFAULT_AUTHOR_PHOTO.
     */
    public static final String DEFAULT_AUTHOR_PHOTO = "default_author_photo.png";
    /**
     * The constant DEFAULT_PDF.
     */
    public static final String DEFAULT_PDF = "";

    private BookFactory() {
    }

    private static class Holder {
        /**
         * The Instance.
         */
        static final EntityFactory<Book> INSTANCE = new BookFactory();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static EntityFactory<Book> getInstance() {
        return BookFactory.Holder.INSTANCE;
    }

    @Override
    public Optional<Book> create(Map<String, String> fields) {
        Optional<Book> result = Optional.empty();
        if (BookValidator.isBookFormValid(fields)) {
            String title = fields.get(RequestParameter.BOOK_TITLE);
            String author = fields.get(RequestParameter.BOOK_AUTHOR);
            String isbn = fields.get(RequestParameter.BOOK_ISBN);
            String genre = fields.get(RequestParameter.BOOK_GENRE);
            short quantity = Short.parseShort(fields.get(RequestParameter.BOOK_QUANTITY));
            String description = fields.get(RequestParameter.BOOK_DESCRIPTION);

            result = Optional.of(new Book(title,author,isbn,quantity,genre,description,DEFAULT_PDF,
                    DEFAULT_COVER, DEFAULT_AUTHOR_PHOTO));
        }
        return result;
    }
}
