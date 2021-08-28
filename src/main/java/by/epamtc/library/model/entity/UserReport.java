package by.epamtc.library.model.entity;

import java.io.Serializable;

/**
 * Entity class that represents a book.
 *
 * @author Artur Mironchik
 */
public class UserReport implements Serializable {
    private long id;
    private boolean isProcessed;
    private String subject;
    private String message;
    private String response;
    private String creationDate;
    private User user;

    private UserReport(){}

    public UserReport(boolean isProcessed, String subject, String message, String creationDate){
        this.isProcessed = isProcessed;
        this.subject = subject;
        this.message = message;
        this.creationDate = creationDate;
    }

    public UserReport(long id, boolean isProcessed, String subject, String creationDate, User user) {
        this.id = id;
        this.isProcessed = isProcessed;
        this.subject = subject;
        this.creationDate = creationDate;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserReport that = (UserReport) o;

        if (id != that.id) return false;
        if (isProcessed != that.isProcessed) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (response != null ? !response.equals(that.response) : that.response != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isProcessed ? 1 : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserReport{" +
                "id=" + id +
                ", isProcessed=" + isProcessed +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", response='" + response + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", user=" + user +
                '}';
    }
}
