package by.epamtc.library.model.entity.factory;

import java.util.Map;
import java.util.Optional;

public interface LibraryFactory<T> {
    Optional<T> create(Map<String, String> fields);
}
