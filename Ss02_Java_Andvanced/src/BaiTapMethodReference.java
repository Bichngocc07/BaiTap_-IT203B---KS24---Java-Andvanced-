import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class BaiTapMethodReference {

    static class User {
        private String username;

        public User() {
            this.username = "defaultUser";
        }

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }

    public static void main(String[] args) {

        List<User> users = Arrays.asList(
                new User("Alice"),
                new User("Bob"),
                new User("Charlie")
        );

       
        Function<User, String> getUsername = User::getUsername;

       
        Consumer<String> print = System.out::println;

       
        Supplier<User> createUser = User::new;

        users.stream()
                .map(getUsername)
                .forEach(print);

        User newUser = createUser.get();
        System.out.println("New User: " + newUser.getUsername());
    }

}
