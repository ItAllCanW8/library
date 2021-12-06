package by.epamtc.library.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingNote implements Serializable {
    private long id;
    private String event;
    private LocalDateTime dateTime;
    private long userIdFk;

    public LoggingNote(){}

    public LoggingNote(String event, String dateTime, long userIdFk){
        this.event = event;
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.userIdFk = userIdFk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(long userIdFk) {
        this.userIdFk = userIdFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoggingNote that = (LoggingNote) o;

        if (userIdFk != that.userIdFk) return false;
        if (!event.equals(that.event)) return false;
        return dateTime.equals(that.dateTime);
    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + (int) (userIdFk ^ (userIdFk >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "LoggingNote{" +
                "id=" + id +
                ", event='" + event + '\'' +
                ", dateTime=" + dateTime +
                ", user_id_fk=" + userIdFk +
                '}';
    }
}
