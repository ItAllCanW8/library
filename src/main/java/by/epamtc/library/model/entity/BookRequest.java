package by.epamtc.library.model.entity;

import java.util.Objects;

public class BookRequest {
    private long id;
    private BookRequestType type;
    private BookRequestState state;
    private String requestDate;
    private String processingDate;
    private String closingDate;
    private int penaltyAmount;
    private Book book;
    private User user;

    public BookRequest(){}

    public BookRequest(long id, BookRequestState state, String requestDate, Book book, User user) {
        this(state, requestDate, book, user);
        this.id = id;
    }

    public BookRequest(BookRequestState state, String requestDate, Book book, User user) {
        this.state = state;
        this.requestDate = requestDate;
        this.book = book;
        this.user = user;
    }

    public BookRequest(BookRequestType type, BookRequestState state, String requestDate){
        this.type = type;
        this.state = state;
        this.requestDate = requestDate;
    }

    public long getId() {
        return id;
    }

    public BookRequestState getState() {
        return state;
    }

    public void setState(BookRequestState state) {
        this.state = state;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public int getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(int penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookRequestType getType() {
        return type;
    }

    public void setType(BookRequestType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookRequest that = (BookRequest) o;

        if (id != that.id) return false;
        if (penaltyAmount != that.penaltyAmount) return false;
        if (type != that.type) return false;
        if (state != that.state) return false;
        if (!requestDate.equals(that.requestDate)) return false;
        if (!Objects.equals(processingDate, that.processingDate))
            return false;
        if (!Objects.equals(closingDate, that.closingDate)) return false;
        if (!book.equals(that.book)) return false;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + requestDate.hashCode();
        result = 31 * result + (processingDate != null ? processingDate.hashCode() : 0);
        result = 31 * result + (closingDate != null ? closingDate.hashCode() : 0);
        result = 31 * result + penaltyAmount;
        result = 31 * result + book.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(BookRequest.class.getSimpleName()+"{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", state=").append(state);
        sb.append(", requestDate=").append(requestDate);
        sb.append(", processingDate=").append(processingDate);
        sb.append(", closingDate=").append(closingDate);
        sb.append(", penaltyAmount=").append(penaltyAmount);
        sb.append(", book=").append(book);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
