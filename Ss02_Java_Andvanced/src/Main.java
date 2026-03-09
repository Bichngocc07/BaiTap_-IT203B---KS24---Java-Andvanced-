import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserManagement management = new UserManagement();

        // Tạo user bằng Supplier
        User user = management.userSupplier.get();

        // Kiểm tra username
        String username = "student01";
        boolean valid = IUserAccount.isStandardLength(username);
        System.out.println("Username hợp lệ: " + valid);

        // Lấy email bằng Function
        User u1 = new User("dai123", "dai@gmail.com", "ADMIN", "ACTIVE");
        String email = management.getEmail.apply(u1);
        System.out.println("Email: " + email);

        // Tạo danh sách user
        List<User> users = new ArrayList<>();

        users.add(u1);
        users.add(new User("ngoc99", "ngoc@gmail.com", "USER", "ACTIVE"));
        users.add(new User("linh01", "linh@gmail.com", "USER", "INACTIVE"));
        users.add(new User("dai01", "dai@gmail.com", "ADMIN", "ACTIVE"));

        // In danh sách
        users.forEach(System.out::println);
    }
}