package dev.danvega.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {

    private final PricingService pricing = new PricingService();

    @Test
    void subtotalAddsEveryLine() {
        Order order = new Order("A-1", "dan@example.com");
        order.addItem(new OrderItem("SKU-1", 2, 10.00));
        order.addItem(new OrderItem("SKU-2", 1, 5.00));

        assertEquals(25.00, pricing.subtotal(order), 0.001);
    }

    @Test
    void taxUsesTheConfiguredRate() {
        assertEquals(8.25, pricing.tax(100.00), 0.001);
    }

    @Test
    void discountIsSkippedBelowTheMinimum() {
        Order order = new Order("A-2", "dan@example.com");
        order.addItem(new OrderItem("SKU-1", 1, 10.00));

        DiscountPolicy policy = new DiscountPolicy("SAVE10", 10, 50.00);

        assertEquals(0.0, pricing.discountFor(order, policy), 0.001);
    }
}
