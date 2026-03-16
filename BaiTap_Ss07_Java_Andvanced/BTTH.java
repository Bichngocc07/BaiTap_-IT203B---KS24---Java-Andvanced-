package BaiTap_Ss07_Java_Andvanced;// TechShopOrderSystem.java

// ===== PAYMENT STRATEGY =====
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paying with Credit Card: " + amount);
    }
}

class PaypalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paying with PayPal: " + amount);
    }
}

class MomoPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paying with MoMo: " + amount);
    }
}

// ===== ORDER REPOSITORY (DATABASE) =====
interface OrderRepository {
    void saveOrder(String orderId);
}

class SqlOrderRepository implements OrderRepository {
    public void saveOrder(String orderId) {
        System.out.println("Order " + orderId + " saved to SQL Database");
    }
}

class MongoOrderRepository implements OrderRepository {
    public void saveOrder(String orderId) {
        System.out.println("Order " + orderId + " saved to MongoDB");
    }
}

// ===== NOTIFICATION SERVICE =====
interface NotificationService {
    void send(String message);
}

class EmailNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SmsNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// ===== ORDER PROCESSOR (SRP + DIP) =====
class OrderProcessor {

    private PaymentStrategy paymentStrategy;
    private OrderRepository orderRepository;
    private NotificationService notificationService;

    // Dependency Injection qua constructor
    public OrderProcessor(
            PaymentStrategy paymentStrategy,
            OrderRepository orderRepository,
            NotificationService notificationService) {

        this.paymentStrategy = paymentStrategy;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void processOrder(String orderId, double amount) {

        System.out.println("Processing order: " + orderId);

        // 1. Lưu đơn hàng
        orderRepository.saveOrder(orderId);

        // 2. Thanh toán
        paymentStrategy.pay(amount);

        // 3. Thông báo
        notificationService.send("Order " + orderId + " processed successfully");

        System.out.println("Order completed\n");
    }
}

// ===== MAIN CLASS =====
public class BTTH {

    public static void main(String[] args) {

        // Chọn phương thức thanh toán
        PaymentStrategy payment = new CreditCardPayment();

        // Chọn database
        OrderRepository repository = new SqlOrderRepository();

        // Chọn cách thông báo
        NotificationService notification = new EmailNotification();

        // Inject dependency
        OrderProcessor processor =
                new OrderProcessor(payment, repository, notification);

        processor.processOrder("ORDER001", 500);
    }
}