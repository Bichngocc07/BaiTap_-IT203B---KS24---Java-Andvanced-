package BaiTap_Ss11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaiTap2 {

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
    static class PharmacyDAO {

        public void printAllMedicine() {
            String sql = "SELECT medicine_name, stock FROM Pharmacy";

            try (Connection conn = DBContext.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("=== DANH MỤC THUỐC ===");

                while (rs.next()) {  // ✅ FIX QUAN TRỌNG
                    String name = rs.getString("medicine_name");
                    int stock = rs.getInt("stock");

                    System.out.println("Thuốc: " + name + " | Tồn kho: " + stock);
                }

            } catch (SQLException e) {
                System.err.println("Lỗi truy vấn Pharmacy:");
                e.printStackTrace();
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        PharmacyDAO dao = new PharmacyDAO();
        dao.printAllMedicine();
    }
}