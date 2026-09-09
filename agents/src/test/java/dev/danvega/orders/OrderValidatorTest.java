package dev.danvega.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderValidatorTest {

    private final OrderValidator validator = new OrderValidator();

    @Test
    void rejectsAnEmptyOrder() {
        Order order = new Order("A-1", "dan@example.com");
        assertThrows(IllegalArgumentException.class, () -> validator.validate(order));
    }

    @Test
    void rejectsAnInvalidEmail() {
        Order order = new Order("A-2", "not-an-email");
        order.addItem(new OrderItem("SKU-1", 1, 10.00));
        assertThrows(IllegalArgumentException.class, () -> validator.validate(order));
    }

    @Test
    void acceptsAValidOrder() {
        Order order = new Order("A-3", "dan@example.com");
        order.addItem(new OrderItem("SKU-1", 1, 10.00));
        assertDoesNotThrow(() -> validator.validate(order));
    }
}
