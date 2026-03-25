package HN_K24_CNTT5_HaBichNgoc_Ss14;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pass = "01072006";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, pass);

            conn.setAutoCommit(false);

            String fromId = "ACC01";
            String toId = "ACC02";
            double amount = 1000;

            String sqlCheck = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            PreparedStatement ps = conn.prepareStatement(sqlCheck);
            ps.setString(1, fromId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("Balance");

                if (balance < amount) {
                    throw new Exception("Không đủ tiền!");
                }
            } else {
                throw new Exception("Không tồn tại tài khoản!");
            }

            CallableStatement cs1 = conn.prepareCall("{call sp_UpdateBalance(?, ?)}");
            cs1.setString(1, fromId);
            cs1.setDouble(2, -amount);
            cs1.execute();

            CallableStatement cs2 = conn.prepareCall("{call sp_UpdateBalance(?, ?)}");
            cs2.setString(1, toId);
            cs2.setDouble(2, amount);
            cs2.execute();

            conn.commit();
            System.out.println("Chuyển tiền thành công!");

            String sql = "SELECT * FROM Accounts";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                System.out.println(
                        rs2.getString("AccountId") + " - " +
                                rs2.getString("FullName") + " - " +
                                rs2.getDouble("Balance")
                );
            }

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Rollback!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Lỗi: " + e.getMessage());

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
