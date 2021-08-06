package by.epamtc.library.model.entity;

public class User {
    private long id;
    private UserRole role;
    private UserDetails userDetails;
    private UserStatus status;
    private String username;
    private String email;

    public User() {}

    public User(long id) {
        this.id = id;
    }

    public User(long id, UserRole role, UserDetails userDetails, UserStatus status,
                String username, String email) {
        this(role,userDetails,status,username,email);
        this.id = id;
    }

    public User(UserRole role, UserDetails userDetails, UserStatus status, String username, String email) {
        this.role = role;
        this.userDetails = userDetails;
        this.status = status;
        this.username = username;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

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
