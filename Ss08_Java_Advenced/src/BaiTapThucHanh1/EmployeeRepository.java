package BaiTapThucHanh1;

public class EmployeeRepository {

    private DatabaseConnection connection;

    public EmployeeRepository(){
        // KHÔNG new → dùng Singleton
        connection = DatabaseConnection.getInstance();
    }

    public void save(Employee emp){
        System.out.println("Lưu nhân viên: " + emp.getName());
        connection.query("INSERT INTO employees VALUES (...)");
    }

    public void findAll(){
        System.out.println("Lấy danh sách nhân viên");
        connection.query("SELECT * FROM employees");
    }

    // để test Singleton
    public DatabaseConnection getConnection(){
        return connection;
    }
}