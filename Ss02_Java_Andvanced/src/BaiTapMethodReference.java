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

        // Danh sách users
        List<User> users = Arrays.asList(
                new User("Alice"),
                new User("Bob"),
                new User("Charlie")
        );

        // 1. Lambda: (user) -> user.getUsername()
        // Method Reference:
        Function<User, String> getUsername = User::getUsername;

        // 2. Lambda: (s) -> System.out.println(s)
        // Method Reference:
        Consumer<String> print = System.out::println;

        // 3. Lambda: () -> new User()
        // Method Reference:
        Supplier<User> createUser = User::new;

        // Áp dụng với danh sách users
        users.stream()
                .map(getUsername)
                .forEach(print);

        // Tạo user mới bằng constructor reference
        User newUser = createUser.get();
        System.out.println("New User: " + newUser.getUsername());
    }
}