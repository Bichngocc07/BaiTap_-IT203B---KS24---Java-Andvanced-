import java.time.LocalDate;

public class user_btth {

    private String id;
    private String email;
    private String password;
    private boolean verified;
    private LocalDate createdAt;

    public user_btth(String id, String email, String password, boolean verified, LocalDate createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{id='%s', email='%s', verified=%s, createdAt=%s}"
                .formatted(id, email, verified, createdAt);
    }
}