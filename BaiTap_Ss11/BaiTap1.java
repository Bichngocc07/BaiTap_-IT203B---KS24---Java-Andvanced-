package BaiTap_Ss11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaiTap1 {

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

    static class PatientDAO {

        public void getAllPatients() {
            String sql = "SELECT * FROM Patients";

            try (Connection conn = DBContext.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " - " +
                                    rs.getString("name")
                    );
                }

            } catch (SQLException e) {
                System.err.println("Error querying patients:");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PatientDAO dao = new PatientDAO();
        dao.getAllPatients();
    }
}
