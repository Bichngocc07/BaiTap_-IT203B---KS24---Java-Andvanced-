import java.util.*;

// Order entity
class Order {
    private String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}


// Repository abstraction
interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}


// Notification abstraction
interface NotificationService {
    void send(String message, String recipient);
}


// FileOrderRepository
class FileOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào file: " + order.getOrderId());
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}


// DatabaseOrderRepository
class DatabaseOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào database: " + order.getOrderId());
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}


// EmailService
class EmailService implements NotificationService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi email: " + message);
    }
}


// SMSNotification
class SMSNotification implements NotificationService {

    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}


// OrderService (module cấp cao)
class OrderService {

    private OrderRepository repository;
    private NotificationService notificationService;

    // Constructor Injection
    public OrderService(OrderRepository repository,
                        NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void createOrder(String orderId, String recipient) {

        Order order = new Order(orderId);

        repository.save(order);

        notificationService.send(
                "Đơn hàng " + orderId + " đã được tạo",
                recipient
        );
    }
}


// Main
public class BaiTap1 {

    public static void main(String[] args) {

        // Cấu hình 1: File + Email
        OrderRepository repo1 = new FileOrderRepository();
        NotificationService notify1 = new EmailService();

        OrderService orderService1 = new OrderService(repo1, notify1);

        System.out.println("Tạo đơn hàng ORD001");
        orderService1.createOrder("ORD001", "a@example.com");


        System.out.println("\n--------------------\n");


        // Cấu hình 2: Database + SMS
        OrderRepository repo2 = new DatabaseOrderRepository();
        NotificationService notify2 = new SMSNotification();

        OrderService orderService2 = new OrderService(repo2, notify2);

        System.out.println("Tạo đơn hàng ORD002");
        orderService2.createOrder("ORD002", "0900000000");
    }
}