import java.util.Scanner;

public class BaiTap1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhập năm sinh của bạn: ");

            String input = scanner.nextLine();

            int namSinh = Integer.parseInt(input);

            int tuoi = 2024 - namSinh;

            System.out.println("Tuổi của bạn là: " + tuoi);

        } catch (NumberFormatException e) {

            System.out.println("Lỗi: Vui lòng nhập năm sinh bằng số!");

        } finally {

            scanner.close();

            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}