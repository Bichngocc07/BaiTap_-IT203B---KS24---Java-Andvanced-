package BaiTapThucHanh1;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    // constructor private
    private DatabaseConnection(){
        System.out.println("Khởi tạo kết nối CSDL");
    }

    // global access point
    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void query(String sql){
        System.out.println("Thực thi: " + sql);
    }
}