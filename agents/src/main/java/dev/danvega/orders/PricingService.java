package dev.danvega.orders;

public class PricingService {

    private static final double TAX_RATE = 0.0825;

    public double subtotal(Order order) {
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getLineTotal();
        }
        return total;
    }

    public double discountFor(Order order, DiscountPolicy policy) {
        double subtotal = subtotal(order);
        if (!policy.appliesTo(subtotal)) {
            return 0;
        }
        return subtotal * (policy.getPercentOff() / 100);
    }

    public double tax(double taxableAmount) {
        return taxableAmount * TAX_RATE;
    }

    public double total(Order order, DiscountPolicy policy) {
        double subtotal = subtotal(order);
        double discount = discountFor(order, policy);
        double taxable = subtotal - discount;
        return taxable + tax(taxable);
    }
}
