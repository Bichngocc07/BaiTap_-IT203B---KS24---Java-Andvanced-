package BaiTap_Ss12;

import java.sql.*;

public class UpdateVitals {

    public static boolean updateVitals(Connection conn, int patientId, double temperature, int heartRate) {
        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind đúng kiểu dữ liệu
            stmt.setDouble(1, temperature);
            stmt.setInt(2, heartRate);
            stmt.setInt(3, patientId);

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Demo test
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital", "root", "password"
            );

            boolean result = updateVitals(conn, 1, 37.5, 80);

            if (result) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}