package BaiTap_Ss11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BaiTap4 {

    // ================= DBContext =================
    static class DBContext {

        private static final String URL =
                "jdbc:mysql://192.168.1.10:3306/Hospital_DB";
        private static final String USER = "admin";
        private static final String PASSWORD = "med123";
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

        static {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Cannot load MySQL Driver", e);
            }
        }

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    // ================= DAO =================
    static class PatientDAO {

        // ❌ Cách không an toàn (demo SQL Injection)
        public void searchUnsafe(String name) {
            String sql = "SELECT * FROM Patients WHERE full_name = '" + name + "'";

            try (Connection conn = DBContext.getConnection();
                 java.sql.Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("=== KẾT QUẢ (UNSAFE) ===");

                while (rs.next()) {
                    System.out.println(rs.getString("full_name"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // ✅ Cách an toàn (PreparedStatement)
        public void searchSafe(String name) {
            String sql = "SELECT * FROM Patients WHERE full_name = ?";

            try (Connection conn = DBContext.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, name);

                ResultSet rs = ps.executeQuery();

                System.out.println("=== KẾT QUẢ (SAFE) ===");

                while (rs.next()) {
                    System.out.println(rs.getString("full_name"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientDAO dao = new PatientDAO();

        System.out.print("Nhập tên bệnh nhân: ");
        String input = scanner.nextLine();

        // Demo
        dao.searchUnsafe(input); // dễ bị hack
        dao.searchSafe(input);   // an toàn

        scanner.close();
    }
}