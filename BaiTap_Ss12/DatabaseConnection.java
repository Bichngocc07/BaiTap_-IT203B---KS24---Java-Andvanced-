package BaiTap_Ss12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "root";
    private static final String PASSWORD = "01072006";

    public static Connection openConnection()  {
        Connection con;
        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (ClassNotFoundException|SQLException e){
            throw new RuntimeException(e);
        }
        return con;
    }
}