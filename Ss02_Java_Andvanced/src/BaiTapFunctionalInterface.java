import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Userr {
    private String username;
    private String role;

    public Userr(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', role='" + role + "'}";
    }
}

public class BaiTapFunctionalInterface {

    public static void main(String[] args) {

        Userr user = new Userr("admin01", "ADMIN");

       
        Predicate<Userr> isAdmin = u -> u.getRole().equals("ADMIN");
        System.out.println("Is Admin: " + isAdmin.test(user));

       
        Function<Userr, String> getUsername = u -> u.getUsername();
        String username = getUsername.apply(user);
        System.out.println("Username: " + username);

        Consumer<Userr> printUser = u -> System.out.println("User info: " + u);
        printUser.accept(user);

        Supplier<Userr> createDefaultUser = () -> new Userr("guest", "USER");
        Userr defaultUser = createDefaultUser.get();
        System.out.println("Default User: " + defaultUser);
    }

}
