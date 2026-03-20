package BaiTap_Ss11;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BTTH {

    // ================= DB CONNECTION =================
    static class DatabaseConnection {

        private static final String URL =
                "jdbc:mysql://192.168.1.10:3306/MedicalAppointmentDB";
        private static final String USER = "admin";
        private static final String PASSWORD = "med123";
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

        static {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Load driver failed", e);
            }
        }

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    // ================= MODEL =================
    static class Appointment {
        int id;
        String patientName;
        Date appointmentDate;
        String doctorName;
        String status;

        public Appointment(int id, String patientName, Date appointmentDate,
                           String doctorName, String status) {
            this.id = id;
            this.patientName = patientName;
            this.appointmentDate = appointmentDate;
            this.doctorName = doctorName;
            this.status = status;
        }

        public Appointment(String patientName, Date appointmentDate,
                           String doctorName, String status) {
            this.patientName = patientName;
            this.appointmentDate = appointmentDate;
            this.doctorName = doctorName;
            this.status = status;
        }
    }

    // ================= REPOSITORY =================
    static class AppointmentRepository {

        // CREATE
        public void addAppointment(Appointment appt) {
            String sql = "INSERT INTO appointments(patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, appt.patientName);
                ps.setDate(2, appt.appointmentDate);
                ps.setString(3, appt.doctorName);
                ps.setString(4, appt.status);

                ps.executeUpdate();
                System.out.println("✅ Thêm lịch khám thành công");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // READ ALL
        public List<Appointment> getAllAppointments() {
            List<Appointment> list = new ArrayList<>();
            String sql = "SELECT * FROM appointments";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(new Appointment(
                            rs.getInt("id"),
                            rs.getString("patient_name"),
                            rs.getDate("appointment_date"),
                            rs.getString("doctor_name"),
                            rs.getString("status")
                    ));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return list;
        }

        // READ BY ID
        public Appointment getById(int id) {
            String sql = "SELECT * FROM appointments WHERE id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return new Appointment(
                            rs.getInt("id"),
                            rs.getString("patient_name"),
                            rs.getDate("appointment_date"),
                            rs.getString("doctor_name"),
                            rs.getString("status")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        // UPDATE
        public void updateAppointment(Appointment appt) {
            String sql = "UPDATE appointments SET patient_name=?, appointment_date=?, doctor_name=?, status=? WHERE id=?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, appt.patientName);
                ps.setDate(2, appt.appointmentDate);
                ps.setString(3, appt.doctorName);
                ps.setString(4, appt.status);
                ps.setInt(5, appt.id);

                int rows = ps.executeUpdate();

                if (rows == 0) {
                    System.out.println("❌ Không tìm thấy lịch khám");
                } else {
                    System.out.println("✅ Cập nhật thành công");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // DELETE
        public void deleteAppointment(int id) {
            String sql = "DELETE FROM appointments WHERE id=?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);

                int rows = ps.executeUpdate();

                if (rows == 0) {
                    System.out.println("❌ Không tìm thấy để xóa");
                } else {
                    System.out.println("✅ Xóa thành công");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AppointmentRepository repo = new AppointmentRepository();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Thêm lịch");
            System.out.println("2. Xem tất cả");
            System.out.println("3. Tìm theo ID");
            System.out.println("4. Cập nhật");
            System.out.println("5. Xóa");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Tên bệnh nhân: ");
                    String name = sc.nextLine();

                    System.out.print("Ngày (yyyy-mm-dd): ");
                    Date date = Date.valueOf(sc.nextLine());

                    System.out.print("Bác sĩ: ");
                    String doctor = sc.nextLine();

                    System.out.print("Trạng thái: ");
                    String status = sc.nextLine();

                    repo.addAppointment(new Appointment(name, date, doctor, status));
                    break;

                case 2:
                    List<Appointment> list = repo.getAllAppointments();
                    for (Appointment a : list) {
                        System.out.println(a.id + " | " + a.patientName + " | " +
                                a.appointmentDate + " | " + a.doctorName + " | " + a.status);
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID: ");
                    int id = sc.nextInt();

                    Appointment a = repo.getById(id);
                    if (a == null) {
                        System.out.println("❌ Không tìm thấy");
                    } else {
                        System.out.println(a.patientName + " | " + a.doctorName);
                    }
                    break;

                case 4:
                    System.out.print("ID cần sửa: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Tên mới: ");
                    String newName = sc.nextLine();

                    System.out.print("Ngày mới: ");
                    Date newDate = Date.valueOf(sc.nextLine());

                    System.out.print("Bác sĩ mới: ");
                    String newDoctor = sc.nextLine();

                    System.out.print("Status: ");
                    String newStatus = sc.nextLine();

                    repo.updateAppointment(
                            new Appointment(uid, newName, newDate, newDoctor, newStatus));
                    break;

                case 5:
                    System.out.print("ID cần xóa: ");
                    int did = sc.nextInt();
                    repo.deleteAppointment(did);
                    break;

                case 0:
                    System.out.println("Thoát...");
                    return;
            }
        }
    }
}