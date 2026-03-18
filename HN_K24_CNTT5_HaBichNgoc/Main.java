package HN_K24_CNTT5_HaBichNgoc;

import HN_K24_CNTT5_HaBichNgoc.ProductDatabase;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();
        while (true) {
            System.out.println("\n1. ---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm ");
            System.out.println("5. Thoát");
            System.out.println("Lựa chọn của bạn là:");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Type (1: Physical, 2: Digital): ");
                    int type = sc.nextInt();
                    sc.nextLine();
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Weight/Size: ");
                    double extra = sc.nextDouble();
                    DigitalProduct p = ProductFactory.createProduct(type, id, name, price, extra);
                    db.addProduct(p);
                    break;
                case 2:
                    for (Produc prod : db.getAll()) {
                        prod.displayInfo();
                    }
                    break;

            }