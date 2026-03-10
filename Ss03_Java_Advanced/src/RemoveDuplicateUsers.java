import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicateUsers {

    public static void main(String[] args) {

        List<User> users = List.of(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@yahoo.com", "INACTIVE"),
                new User("alice", "alice2@gmail.com", "ACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE"),
                new User("bob", "bob2@yahoo.com", "ACTIVE")
        );

        List<User> uniqueUsers = new ArrayList<>(
                users.stream()
                        .collect(Collectors.toMap(
                                User::username,
                                user -> user,
                                (existing, replacement) -> existing
                        ))
                        .values()
        );

        uniqueUsers.forEach(user ->
                System.out.println(
                        "Username: " + user.username() +
                                ", Email: " + user.email() +
                                ", Status: " + user.status()
                )
        );
    }
}

