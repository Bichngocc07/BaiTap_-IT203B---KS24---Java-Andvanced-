package BaiTap_Ss11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BaiTap3 {

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

    static class BedDAO {

        public void updateBedStatus(String bedId) {
            String sql = "UPDATE Beds SET bed_status = ? WHERE bed_id = ?";

            try (Connection conn = DBContext.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, "Occupied");
                ps.setString(2, bedId);

                int rowsAffected = ps.executeUpdate(); 

                if (rowsAffected == 0) {
                    System.out.println("Mã giường không tồn tại: " + bedId);
                } else {
                    System.out.println("Cập nhật giường thành công: " + bedId);
                }

            } catch (SQLException e) {
                System.err.println("Lỗi cập nhật giường:");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BedDAO dao = new BedDAO();

        System.out.print("Nhập mã giường: ");
        String bedId = scanner.nextLine();

        dao.updateBedStatus(bedId);

        scanner.close();
    }
}
