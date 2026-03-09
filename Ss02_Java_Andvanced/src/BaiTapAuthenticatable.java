public class BaiTapAuthenticatable {

    interface Authenticatable {

        String getPassword();

        default boolean isAuthenticated() {
            return getPassword() != null && !getPassword().isEmpty();
        }

        static String encrypt(String rawPassword) {
            return "ENC_" + rawPassword;
        }
    }

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

        String encrypted = Authenticatable.encrypt("mypassword");
        System.out.println("Encrypted password: " + encrypted);
    }

}
