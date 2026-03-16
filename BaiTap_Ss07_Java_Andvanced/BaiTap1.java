import java.util.*;

// Product
class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        System.out.println("Tạo sản phẩm: " + id + " - " + name + " - " + price);
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}

// Customer
class Customer {
    private String name;
    private String email;
    private String address;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        System.out.println("Tạo khách hàng: " + name + " - " + email);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}

// Order
class Order {
    private String orderId;
    private Customer customer;
    private Map<Product, Integer> products = new HashMap<>();
    private double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        System.out.println("Đơn hàng " + orderId + " được tạo");
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
        System.out.println("Đã thêm sản phẩm " + product.getId());
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}

// OrderCalculator
class OrderCalculator {

    public double calculateTotal(Order order) {
        double total = 0;

        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }

        System.out.println("Tổng tiền: " + total);
        return total;
    }
}

// OrderRepository
class OrderRepository {

    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng " + order.getOrderId());
    }
}

// EmailService
class EmailServicee {

    public void sendOrderConfirmation(Order order) {
        String email = order.getCustomer().getEmail();
        System.out.println("Đã gửi email đến " + email +
                ": Đơn hàng " + order.getOrderId() + " đã được tạo");
    }
}

// Main
public class BaiTap1 {

    public static void main(String[] args) {

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        Customer customer = new Customer(
                "Nguyễn Văn A",
                "a@example.com",
                "Hà Nội"
        );

        Order order = new Order("ORD001", customer);

        order.addProduct(p1, 1);
        order.addProduct(p2, 2);

        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotal(order);
        order.setTotal(total);

        OrderRepository repo = new OrderRepository();
        repo.save(order);

        EmailService emailService = new EmailService();
        emailService.sendOrderConfirmation(order);
    }
}