// Interface tổng quát
interface PaymentMethod {
    void pay(double amount);
}

// ISP: các interface nhỏ
interface CODPayable extends PaymentMethod {}
interface CardPayable extends PaymentMethod {}
interface EWalletPayable extends PaymentMethod {}


// COD Payment
class CODPayment implements CODPayable {

    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán COD: " + amount + " - Thành công");
    }
}


// Credit Card Payment
class CreditCardPayment implements CardPayable {

    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + amount + " - Thành công");
    }
}


// Momo Payment
class MomoPayment implements EWalletPayable {

    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + amount + " - Thành công");
    }
}


// PaymentProcessor
class PaymentProcessor {

    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void process(double amount) {
        paymentMethod.pay(amount);
    }
}


// Main
public class BaiTap3 {

    public static void main(String[] args) {

        // COD
        PaymentProcessor codProcessor =
                new PaymentProcessor(new CODPayment());
        codProcessor.process(500000);

        // Credit Card
        PaymentProcessor cardProcessor =
                new PaymentProcessor(new CreditCardPayment());
        cardProcessor.process(1000000);

        // Momo
        PaymentProcessor momoProcessor =
                new PaymentProcessor(new MomoPayment());
        momoProcessor.process(750000);


        // Chứng minh LSP
        PaymentMethod payment = new CreditCardPayment();
        PaymentProcessor processor1 = new PaymentProcessor(payment);
        processor1.process(200000);

        payment = new MomoPayment(); // thay thế implementation
        PaymentProcessor processor2 = new PaymentProcessor(payment);
        processor2.process(200000);
    }
}