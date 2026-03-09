public class BaiTapLambda {

    @FunctionalInterface
    interface PasswordValidator {
        boolean isValid(String password);
    }

    public static void main(String[] args) {

        // Lambda expression kiểm tra mật khẩu >= 8 ký tự
        PasswordValidator validator = password -> password.length() >= 8;

        // Kiểm tra 2 mật khẩu
        System.out.println("12345678 -> " + validator.isValid("12345678"));
        System.out.println("1234 -> " + validator.isValid("1234"));
    }
}