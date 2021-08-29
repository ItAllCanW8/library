package by.epamtc.library.model.entity;

import java.io.Serializable;

/**
 * Entity class that represents a book request.
 *
 * @author Artur Mironchik
 */
public class BookRequest implements Serializable {
    private long id;
    private BookRequestType type;
    private BookRequestState state;
    private String requestDate;
    private String closingDate;
    private String expectedReturnDate;
    private Book book;
    private User user;

    /**
     * Instantiates a new Book request.
     *
     */
    public BookRequest(){}

    /**
     * Instantiates a new Book request.
     *
     * @param type        the type
     * @param state       the state
     * @param requestDate the request date
     */
    public BookRequest(BookRequestType type, BookRequestState state, String requestDate){
        this.type = type;
        this.state = state;
        this.requestDate = requestDate;
    }

    /**
     * Instantiates a new Book request.
     *
     * @param book the book
     */
    public BookRequest(Book book) {
        this.book = book;
    }

    /**
     * Instantiates a new Book request.
     *
     * @param id                 the id
     * @param requestType        the request type
     * @param requestState       the request state
     * @param requestDate        the request date
     * @param expectedReturnDate the expected return date
     * @param closingDate        the closing date
     * @param book               the book
     */
    public BookRequest(long id, BookRequestType requestType, BookRequestState requestState, String requestDate,
                       String expectedReturnDate, String closingDate, Book book) {
        this(requestType, requestState, requestDate);
        this.id = id;
        this.expectedReturnDate = expectedReturnDate;
        this.closingDate = closingDate;
        this.book = book;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public BookRequestState getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(BookRequestState state) {
        this.state = state;
    }

    /**
     * Gets request date.
     *
     * @return the request date
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * Sets request date.
     *
     * @param requestDate the request date
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * Gets closing date.
     *
     * @return the closing date
     */
    public String getClosingDate() {
        return closingDate;
    }

    /**
     * Sets closing date.
     *
     * @param closingDate the closing date
     */
    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    /**
     * Gets book.
     *
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets book.
     *
     * @param book the book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public BookRequestType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(BookRequestType type) {
        this.type = type;
    }

    /**
     * Gets expected return date.
     *
     * @return the expected return date
     */
    public String getExpectedReturnDate() {
        return expectedReturnDate;
    }

    /**
     * Sets expected return date.
     *
     * @param expectedReturnDate the expected return date
     */
    public void setExpectedReturnDate(String expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookRequest that = (BookRequest) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (state != that.state) return false;
        if (requestDate != null ? !requestDate.equals(that.requestDate) : that.requestDate != null) return false;
        if (closingDate != null ? !closingDate.equals(that.closingDate) : that.closingDate != null) return false;
        if (expectedReturnDate != null ? !expectedReturnDate.equals(that.expectedReturnDate) : that.expectedReturnDate != null)
            return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (closingDate != null ? closingDate.hashCode() : 0);
        result = 31 * result + (expectedReturnDate != null ? expectedReturnDate.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "id=" + id +
                ", type=" + type +
                ", state=" + state +
                ", requestDate='" + requestDate + '\'' +
                ", closingDate='" + closingDate + '\'' +
                ", expectedReturnDate='" + expectedReturnDate + '\'' +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
