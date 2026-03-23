package BaiTap_Ss12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection con = DatabaseConnection.openConnection()) {

            System.out.print("Nhap so luong bac si: ");
            int n = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < n; i++) {

                String sql = "INSERT INTO doctors VALUES(?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);

                System.out.println("Nhap ma bac si:");
                ps.setString(1, sc.nextLine());

                System.out.println("Nhap ten bac si:");
                ps.setString(2, sc.nextLine());

                System.out.println("Nhap gioi tinh:");
                ps.setString(3, sc.nextLine());

                System.out.println("Nhap tuoi:");
                ps.setInt(4, Integer.parseInt(sc.nextLine()));

                System.out.println("Nhap khoa:");
                ps.setString(5, sc.nextLine());

                int row = ps.executeUpdate();
                System.out.println("Da them " + row + " ban ghi");

                ps.close();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}