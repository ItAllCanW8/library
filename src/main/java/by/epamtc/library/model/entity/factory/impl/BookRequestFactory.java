package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.entity.factory.EntityFactory;
import by.epamtc.library.util.DateTimeHelper;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * EntityFactory implementation used to create a BookRequest object.
 *
 * @author Artur Mironchik
 */
public class BookRequestFactory implements EntityFactory<BookRequest> {
    private static final BookRequestState DEFAULT_STATE = BookRequestState.LEFT;

    private static class Holder {
        /**
         * The Instance.
         */
        static final EntityFactory<BookRequest> INSTANCE = new BookRequestFactory();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static EntityFactory<BookRequest> getInstance() {
        return BookRequestFactory.Holder.INSTANCE;
    }

    @Override
    public Optional<BookRequest> create(Map<String, String> fields) {
        BookRequestType requestType = BookRequestType.fromString(fields.get(RequestParameter.REQUEST_TYPE));
        BookRequestState state;

        Optional<BookRequest> requestOptional = Optional.empty();

        if(requestType != null) {
            state = requestType.equals(BookRequestType.TO_READING_ROOM) ? BookRequestState.APPROVED : DEFAULT_STATE;

            requestOptional = Optional.of(new BookRequest(requestType, state, LocalDateTime.now()
                    .format(DateTimeHelper.formatter)));
        }

        return requestOptional;
    }
}