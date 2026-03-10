import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserService service = new UserService();

        List<User> users = List.of(
                new User("U1", "alice@gmail.com", "123", true, LocalDate.now().minusMonths(30)),
                new User("U2", "bob@gmail.com", "123", false, LocalDate.now().minusMonths(10)),
                new User("U3", "charlie@gmail.com", "123", true, LocalDate.now().minusMonths(15)),
                new User("U4", "david@gmail.com", "123", true, LocalDate.now().minusMonths(5)),
                new User("U5", "emma@gmail.com", "123", false, LocalDate.now().minusMonths(40))
        );

        List<User> verifiedUsers = service.getVerifiedUsers(users);

        List<PublicUser> publicUsers = verifiedUsers.stream()
                .map(user -> {
                    long months = service.calculateMonths(user.getCreatedAt());
                    Tier tier = service.classifyTier(months);
                    return new PublicUser(user.getId(), user.getEmail(), tier);
                })
                .toList();

        System.out.println("""
                ===== USER REPORT =====
                """);

        publicUsers.forEach(System.out::println);
    }
}