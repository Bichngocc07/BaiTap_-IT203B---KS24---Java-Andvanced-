public interface IUserAccount {

    // Phương thức trừu tượng
    String getRole();

    // Default method kiểm tra quyền truy cập
    default boolean checkAccess() {
        if ("ADMIN".equals(getRole())) {
            return true;
        }
        return false;
    }

    // Static method kiểm tra username hợp lệ
    static boolean isStandardLength(String username) {
        return username != null && username.length() > 5;
    }
}