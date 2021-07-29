package by.epamtc.library.model.entity;

import java.time.LocalDate;

public class BookRequest {
    private long id;
    private BookRequestState state;
    private LocalDate requestDate;
    private LocalDate processingDate;
    private LocalDate closingDate;
    private int penaltyAmount;
    private Book book;
    private User user;

    public BookRequest(){}

    public BookRequest(long id, BookRequestState state, LocalDate requestDate, Book book, User user) {
        this.id = id;
        this.state = state;
        this.requestDate = requestDate;
        this.book = book;
        this.user = user;
    }
}
