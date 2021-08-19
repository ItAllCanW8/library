package by.epamtc.library.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserReport implements Serializable {
    private long id;
    private boolean isAvailable;
    private String subject;
    private String message;
    private String response;
    private String creationDate;
    private User user;

    private UserReport(){}

    public UserReport(boolean isAvailable, String subject, String message, String creationDate){
        this.isAvailable = isAvailable;
        this.subject = subject;
        this.message = message;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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
        if (isAvailable != that.isAvailable) return false;
        if (!subject.equals(that.subject)) return false;
        if (!message.equals(that.message)) return false;
        if (!Objects.equals(response, that.response)) return false;
        if (!creationDate.equals(that.creationDate)) return false;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isAvailable ? 1 : 0);
        result = 31 * result + subject.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(UserReport.class.getSimpleName()+"{");
        sb.append("id=").append(id);
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", response='").append(response).append('\'');
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
