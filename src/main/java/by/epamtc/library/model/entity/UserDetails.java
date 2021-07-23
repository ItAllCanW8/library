package by.epamtc.library.model.entity;

import java.time.LocalDate;

public class UserDetails {
    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String photoPath;

    private UserDetails(){}

    public UserDetails(long id){this.id = id;}

    public UserDetails(long id, String name, String surname, LocalDate dateOfBirth,
                       String phoneNumber, String photoPath) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.photoPath = photoPath;
    }

    public UserDetails(String name, String surname, LocalDate dateOfBirth,
                       String phoneNumber, String photoPath) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.photoPath = photoPath;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!surname.equals(that.surname)) return false;
        if (!dateOfBirth.equals(that.dateOfBirth)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        return photoPath.equals(that.photoPath);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + photoPath.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
