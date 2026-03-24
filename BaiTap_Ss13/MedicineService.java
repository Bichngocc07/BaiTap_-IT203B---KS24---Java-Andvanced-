package BaiTap_Ss13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class MedicineService {

    private DataSource dataSource;

    public MedicineService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void capPhatThuoc(int patientId, int medicineId) {
        Connection conn = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psInsert = null;

        try {
            conn = dataSource.getConnection();

           
            conn.setAutoCommit(false);

            String updateSQL = "UPDATE medicine_inventory " +
                    "SET quantity = quantity - 1 " +
                    "WHERE medicine_id = ? AND quantity > 0";

            psUpdate = conn.prepareStatement(updateSQL);
            psUpdate.setInt(1, medicineId);

            int rowsAffected = psUpdate.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Không đủ thuốc trong kho!");
            }

            String insertSQL = "INSERT INTO prescription_history " +
                    "(patient_id, medicine_id, date) " +
                    "VALUES (?, ?, CURRENT_TIMESTAMP)";

            psInsert = conn.prepareStatement(insertSQL);
            psInsert.setInt(1, patientId);
            psInsert.setInt(2, medicineId);

            psInsert.executeUpdate();

            conn.commit();
            System.out.println("Cấp phát thuốc thành công!");

        } catch (Exception e) {

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã rollback do lỗi!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            e.printStackTrace();

        } finally {
            try {
                if (psInsert != null) psInsert.close();
                if (psUpdate != null) psUpdate.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
