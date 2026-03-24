package BaiTap_Ss13;

import java.sql.SQLException;

public class Main {
    public static void main(String[]args)throws SQLException{
        Comparable con = MedicineService.openConnect();
        int patienId = 101;
        try {
            con.setAutoCommit(false);

        }
    }
}
