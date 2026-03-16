package BaiTap_Ss07_Java_Andvanced;

// DiscountStrategy interface
interface DiscountStrategy {
    double applyDiscount(double totalAmount);
}

// PercentageDiscount
class PercentageDiscount implements DiscountStrategy {

    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * percent / 100);
    }
}

// FixedDiscount
class FixedDiscount implements DiscountStrategy {

    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - amount;
    }
}

// NoDiscount
class NoDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount;
    }
}

// HolidayDiscount (thêm mới mà không sửa code cũ)
class HolidayDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * 0.15);
    }
}

// OrderCalculator
class OrderCalculator {

    private DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculate(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}

// Main
public class BaiTap2 {

    public static void main(String[] args) {

        double total = 1000000;

        // PercentageDiscount 10%
        OrderCalculator calc1 =
                new OrderCalculator(new PercentageDiscount(10));
        System.out.println("Số tiền sau giảm: " + calc1.calculate(total));

        // FixedDiscount 50000
        OrderCalculator calc2 =
                new OrderCalculator(new FixedDiscount(50000));
        System.out.println("Số tiền sau giảm: " + calc2.calculate(total));

        // NoDiscount
        OrderCalculator calc3 =
                new OrderCalculator(new NoDiscount());
        System.out.println("Số tiền sau giảm: " + calc3.calculate(total));

        // HolidayDiscount 15%
        OrderCalculator calc4 =
                new OrderCalculator(new HolidayDiscount());
        System.out.println("Số tiền sau giảm: " + calc4.calculate(total));
    }
}