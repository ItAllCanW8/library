package by.epamtc.library.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entity class that represents user details.
 *
 * @author Artur Mironchik
 */
public class UserDetails implements Serializable {
    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String photoPath;

    public UserDetails(){ }

    /**
     * Instantiates a new User details.
     *
     * @param id          the id
     * @param name        the name
     * @param surname     the surname
     * @param dateOfBirth the date of birth
     * @param phoneNumber the phone number
     * @param photoPath   the photo path
     */
    public UserDetails(long id, String name, String surname, LocalDate dateOfBirth,
                       String phoneNumber, String photoPath) {
        this(name, surname, dateOfBirth, phoneNumber, photoPath);
        this.id = id;
    }

    /**
     * Instantiates a new User details.
     *
     * @param name        the name
     * @param surname     the surname
     * @param dateOfBirth the date of birth
     * @param phoneNumber the phone number
     * @param photoPath   the photo path
     */
    public UserDetails(String name, String surname, LocalDate dateOfBirth,
                       String phoneNumber, String photoPath) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.photoPath = photoPath;
    }

    /**
     * Instantiates a new User details.
     *
     * @param userPhoto the user photo
     */
    public UserDetails(String userPhoto) {
        this.photoPath = userPhoto;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets photo path.
     *
     * @return the photo path
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * Sets photo path.
     *
     * @param photoPath the photo path
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
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
