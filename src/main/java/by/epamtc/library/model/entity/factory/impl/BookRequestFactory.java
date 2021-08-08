package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.entity.factory.LibraryFactory;
import by.epamtc.library.util.DateTimeHelper;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class BookRequestFactory implements LibraryFactory<BookRequest> {
    private static final BookRequestState DEFAULT_STATE = BookRequestState.LEFT;

    private static class Holder {
        static final LibraryFactory<BookRequest> INSTANCE = new BookRequestFactory();
    }

    public static LibraryFactory<BookRequest> getInstance() {
        return BookRequestFactory.Holder.INSTANCE;
    }

    @Override
    public Optional<BookRequest> create(Map<String, String> fields) {
        BookRequestType requestType = BookRequestType.fromString(fields.get(RequestParameter.BOOK_REQUEST_TYPE));

        if(requestType != null)
            return (Optional.of(new BookRequest(requestType,DEFAULT_STATE,
                    LocalDateTime.now().format(DateTimeHelper.formatter))));

        return Optional.empty();
    }
}
