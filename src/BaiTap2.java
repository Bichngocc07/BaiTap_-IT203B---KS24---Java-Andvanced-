import java.util.Scanner;

public class BaiTap2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhập tổng số người dùng: ");
            int tongNguoi = scanner.nextInt();

            System.out.print("Nhập số nhóm muốn chia: ");
            int soNhom = scanner.nextInt();

            int ketQua = tongNguoi / soNhom;

            System.out.println("Mỗi nhóm có: " + ketQua + " người");

        } catch (ArithmeticException e) {

            System.out.println("Không thể chia cho 0!");

        }

        System.out.println("Chương trình vẫn tiếp tục chạy...");

        scanner.close();
    }
}