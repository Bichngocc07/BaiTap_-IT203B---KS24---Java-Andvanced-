public class BaiTapAuthenticatable {

    interface Authenticatable {

        // Phương thức trừu tượng
        String getPassword();

        // Default method: kiểm tra mật khẩu có rỗng hay không
        default boolean isAuthenticated() {
            return getPassword() != null && !getPassword().isEmpty();
        }

        // Static method: mô phỏng mã hóa mật khẩu
        static String encrypt(String rawPassword) {
            return "ENC_" + rawPassword;
        }
    }

    // Class User triển khai interface
    static class User implements Authenticatable {

        private String password;

        public User(String password) {
            this.password = password;
        }

        @Override
        public String getPassword() {
            return password;
        }
    }

    public static void main(String[] args) {

        User user1 = new User(Authenticatable.encrypt("123456"));
        User user2 = new User("");

        System.out.println("User1 authenticated: " + user1.isAuthenticated());
        System.out.println("User2 authenticated: " + user2.isAuthenticated());

        // Gọi static method từ interface
        String encrypted = Authenticatable.encrypt("mypassword");
        System.out.println("Encrypted password: " + encrypted);
    }
}