import java.util.ArrayList;
import java.util.Scanner;

public class ProductManagementsystem {

    ArrayList<Product> products = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ProductManagementsystem system = new ProductManagementsystem();
        system.menu();
    }

    public void menu(){

        int choice;

        do {

            System.out.println("=========PRODUCT MANAGEMENT SYSTEM=========");
            System.out.println("1.Thêm sản phẩm mới");
            System.out.println("2.Hiển thị danh sách sản phẩm");
            System.out.println("3.Cập nhật số lượng theo ID");
            System.out.println("4.Xóa sản phẩm đã hết hàng");
            System.out.println("5.Thoát chương trình");
            System.out.println("==========================================");

            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice){

                case 1:
                    addProduct();
                    break;

                case 2:
                    displayProducts();
                    break;

                case 5:
                    System.out.println("Thoát chương trình");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 5);
    }

    public void addProduct(){

        System.out.print("Nhập ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();

        System.out.print("Nhập giá: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập danh mục: ");
        String category = sc.nextLine();

        Product p = new Product(id, name, price, quantity, category);

        products.add(p);

        System.out.println("Thêm sản phẩm thành công!");
    }

    public void displayProducts(){

        if(products.isEmpty()){
            System.out.println("Danh sách trống!");
            return;
        }

        for(Product p : products){
            System.out.println(p);
        }
    }
}
