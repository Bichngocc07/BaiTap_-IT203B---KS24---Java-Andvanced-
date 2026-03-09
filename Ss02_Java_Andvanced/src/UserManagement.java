import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.Function;

public class UserManagement {

    // Supplier tạo User (Constructor Reference)
    Supplier<User> userSupplier = User::new;

    // Predicate kiểm tra status ACTIVE
    Predicate<User> isActive = user -> "ACTIVE".equals(user.getStatus());

    // Function trích xuất email
    Function<User, String> getEmail = User::getEmail;

}