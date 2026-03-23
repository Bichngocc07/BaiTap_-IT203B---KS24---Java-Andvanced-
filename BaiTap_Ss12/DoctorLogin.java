package BaiTap_Ss12;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DoctorLogin {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean login(Connection conn, String code, String password) {
        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            String hashedPass = hashPassword(password);

            stmt.setString(1, code);
            stmt.setString(2, hashedPass);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // true nếu có kết quả

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital", "root", "password"
            );

            String doctorCode = "D001";
            String password = "123456";

            boolean success = login(conn, doctorCode, password);

            if (success) {
                System.out.println("Đăng nhập thành công!");
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
