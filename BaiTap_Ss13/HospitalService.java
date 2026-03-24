package BaiTap_Ss13;

import java.sql.*;
import javax.sql.DataSource;

public class HospitalService {

    private DataSource dataSource;

    public HospitalService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {

        Connection conn = null;
        PreparedStatement psCheck = null;
        PreparedStatement psTruTien = null;
        PreparedStatement psGiuong = null;
        PreparedStatement psBenhNhan = null;

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            String checkSQL = "SELECT balance FROM patient_wallet WHERE patient_id = ?";
            psCheck = conn.prepareStatement(checkSQL);
            psCheck.setInt(1, maBenhNhan);

            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Không tìm thấy bệnh nhân!");
            }

            double soDu = rs.getDouble("balance");

            if (soDu < tienVienPhi) {
                throw new SQLException("Không đủ tiền!");
            }

            // =========================
            // 1. TRỪ TIỀN
            // =========================
            String sql1 = "UPDATE patient_wallet SET balance = balance - ? WHERE patient_id = ?";
            psTruTien = conn.prepareStatement(sql1);
            psTruTien.setDouble(1, tienVienPhi);
            psTruTien.setInt(2, maBenhNhan);

            int row1 = psTruTien.executeUpdate();

            if (row1 == 0) {
                throw new SQLException("Trừ tiền thất bại!");
            }

            // =========================
            // 2. GIẢI PHÓNG GIƯỜNG
            // =========================
            String sql2 = "UPDATE beds SET status = 'EMPTY' WHERE patient_id = ?";
            psGiuong = conn.prepareStatement(sql2);
            psGiuong.setInt(1, maBenhNhan);

            int row2 = psGiuong.executeUpdate();

            // =========================
            // 🧨 BẪY 2: ROW = 0 (KHÔNG THROW EXCEPTION)
            // =========================
            if (row2 == 0) {
                throw new SQLException("Không có giường để giải phóng!");
            }

            // =========================
            // 3. CẬP NHẬT BỆNH NHÂN
            // =========================
            String sql3 = "UPDATE patients SET status = 'DISCHARGED' WHERE patient_id = ?";
            psBenhNhan = conn.prepareStatement(sql3);
            psBenhNhan.setInt(1, maBenhNhan);

            int row3 = psBenhNhan.executeUpdate();

            if (row3 == 0) {
                throw new SQLException("Không cập nhật được trạng thái bệnh nhân!");
            }

            // =========================
            // ✅ COMMIT
            // =========================
            conn.commit();
            System.out.println("Xuất viện + thanh toán thành công!");

        } catch (Exception e) {

            // ❗ ROLLBACK toàn bộ nếu có lỗi
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã rollback toàn bộ giao dịch!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            System.out.println("Lỗi: " + e.getMessage());

        } finally {

            // ❗ Đóng tài nguyên
            try {
                if (psBenhNhan != null) psBenhNhan.close();
                if (psGiuong != null) psGiuong.close();
                if (psTruTien != null) psTruTien.close();
                if (psCheck != null) psCheck.close();

                if (conn != null) {
                    conn.setAutoCommit(true); // reset
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}