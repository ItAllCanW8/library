package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.BookRequestState;
import by.epamtc.library.model.entity.BookRequestType;
import by.epamtc.library.model.entity.factory.LibraryFactory;

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

//        return (isSummaryValid(summary) ? Optional.of(new ApplicantRequest(summary, DEFAULT_APPLICANT_STATE)) :
//                Optional.empty());
        return Optional.empty();
    }
}
