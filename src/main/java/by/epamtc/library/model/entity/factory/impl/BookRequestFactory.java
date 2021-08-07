package by.epamtc.library.model.entity.factory.impl;

import by.epamtc.library.model.entity.BookRequest;
import by.epamtc.library.model.entity.factory.LibraryFactory;

import java.util.Map;
import java.util.Optional;

public class BookRequestFactory implements LibraryFactory<BookRequest> {
    @Override
    public Optional<BookRequest> create(Map<String, String> fields) {
        return Optional.empty();
    }
}
