package by.epamtc.library.model.entity;

import java.io.Serializable;

/**
 * Entity class represents a user.
 *
 * @author Artur Mironchik
 */
public class User implements Serializable {
    private long id;
    private UserRole role;
    private UserDetails userDetails;
    private UserStatus status;
    private String username;
    private String email;

    /**
     * Constructs an User object.
     */
    public User() {}

    /**
     * Constructs an User object with given id.
     *
     * @param id long value of user's id.
     */
    public User(long id) {
        this.id = id;
    }

    /**
     * Constructs an User object with given id,username,email,role.
     *
     * @param id long value of user's id.
     * @param username String object of username.
     * @param email String object of user's email.
     * @param role UserRole object of user's role.
     */
    public User(long id, String username, String email, UserRole role){
        this(id);
        this.username = username;
        this.email = email;
        this.role = role;
    }

    /**
     * Constructs an User object with given id,role,userDetails,status,username,email.
     *
     * @param id long value of user's id.
     * @param role UserRole object of user's role.
     * @param userDetails UserDetails object of user's details.
     * @param status UserStatus object of user's status.
     * @param username String object of username.
     * @param email String object of user's email.
     */
    public User(long id, UserRole role, UserDetails userDetails, UserStatus status,
                String username, String email) {
        this(role,userDetails,status,username,email);
        this.id = id;
    }

    /**
     * Constructs an User object with given role,userDetails,status,username,email.
     *
     * @param role UserRole object of user's role.
     * @param userDetails UserDetails object of user's details.
     * @param status UserStatus object of user's status.
     * @param username String object of username.
     * @param email String object of user's email.
     */
    public User(UserRole role, UserDetails userDetails, UserStatus status, String username, String email) {
        this.role = role;
        this.userDetails = userDetails;
        this.status = status;
        this.username = username;
        this.email = email;
    }

    /**
     * Constructs an User object with given userId,username,userPhoto.
     *
     * @param userId long value of user's id.
     * @param username String object of username.
     * @param userPhoto String object of user photo.
     */
    public User(long userId, String username, String userPhoto) {
        this(userId);
        this.username = username;
        this.userDetails = new UserDetails(userPhoto);
    }

    /**
     * Getter method of user's id.
     *
     * @return long value of user's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter method of user's role.
     *
     * @return UserRole object of user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Setter method of user's role.
     *
     * @param role UserRole object of user's role.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Getter method of user's id.
     *
     * @return UserDetails object of user's details.
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * Setter method of user's details.
     *
     * @param userDetails UserDetails object of user's details.
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * Getter method of user's status.
     *
     * @return UserStatus object of user's status.
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Setter method of user's status.
     *
     * @param status UserStatus object of user's status.
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Getter method of user's username.
     *
     * @return String object of user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method of user's username.
     *
     * @param username String object of user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method of user's email.
     *
     * @return String value of user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method of user's email.
     *
     * @param email String object of user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (role != user.role) return false;
        if (!userDetails.equals(user.userDetails)) return false;
        if (!status.equals(user.status)) return false;
        if (!username.equals(user.username)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + role.hashCode();
        result = 31 * result + userDetails.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(User.class.getSimpleName() + "{");
        sb.append("id=").append(id);
        sb.append(", role=").append(role);
        sb.append(", userDetails=").append(userDetails);
        sb.append(", status=").append(status);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}