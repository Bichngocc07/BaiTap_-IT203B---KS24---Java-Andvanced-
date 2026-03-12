//SS05_THỰC HÀNH ĐẦU GIỜ
import java.util.ArrayList;
import java.util.Scanner;
public class ProductManagementsystem {
    ArrayList<Product>products = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int choice = 0;
        do {
            System.out.println("=========PRODUCTMANAGEMENTSYSTEM=========");
            System.out.println("1.Thêm sản phẩm mới");
            System.out.println("2.Hiển thị danh sách sản phẩm");
            System.out.println("3.Cập nhật số lượng theo ID");
            System.out.println("4.Xóa sản phẩm đã hết hàng");
            System.out.println("5.Thoát chương trình");
            System.out.println("==========================================");
            choice = Integer.getInteger(sc.nextLinre());
            System.out.println("Lựa chọn của bạn :");

        } while (choice !=0);
    }
    public void addProduct(){
        System.out.println("Nhập tên sản phẩm :");
        int id = Integer.getInteger()
    }
}

