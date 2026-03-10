import java.util.List;

public class RecordUser {

    public static void main(String[] args) {

        List<User> users = List.of(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@yahoo.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );

        users.forEach(user ->
                System.out.println(
                        "Username: " + user.username() +
                                ", Email: " + user.email() +
                                ", Status: " + user.status()
                )
        );
    }
}

