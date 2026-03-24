package BaiTap_Ss13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class PaymentService {

    private DataSource dataSource;

    public PaymentService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void thanhToanVienPhi(int patientId, int invoiceId, double amount) {

        Connection conn = null;
        PreparedStatement psWallet = null;
        PreparedStatement psInvoice = null;

        try {
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            String sqlWallet = "UPDATE patient_wallet " +
                    "SET balance = balance - ? " +
                    "WHERE patient_id = ? AND balance >= ?";

            psWallet = conn.prepareStatement(sqlWallet);
            psWallet.setDouble(1, amount);
            psWallet.setInt(2, patientId);
            psWallet.setDouble(3, amount);

            int rows = psWallet.executeUpdate();

            if (rows == 0) {
                throw new SQLException("Không đủ tiền trong ví!");
            }

            String sqlInvoice = "UPDATE invoices " +
                    "SET status = 'PAID' " +
                    "WHERE invoice_id = ?";

            psInvoice = conn.prepareStatement(sqlInvoice);
            psInvoice.setInt(1, invoiceId);

            int updated = psInvoice.executeUpdate();

            if (updated == 0) {
                throw new SQLException("Không tìm thấy hóa đơn!");
            }

            conn.commit();
            System.out.println("Thanh toán thành công!");

        } catch (Exception e) {

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã rollback giao dịch!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            System.out.println("Thanh toán thất bại: " + e.getMessage());

        } finally {

            try {
                if (psInvoice != null) psInvoice.close();
                if (psWallet != null) psWallet.close();

                if (conn != null) {
                    conn.setAutoCommit(true); 
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
