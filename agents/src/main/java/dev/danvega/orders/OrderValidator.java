package dev.danvega.orders;

public class OrderValidator {

    private static final int MAX_ITEMS_PER_ORDER = 20;

    public void validate(Order order) {
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        if (order.getItems().size() > MAX_ITEMS_PER_ORDER) {
            throw new IllegalArgumentException(
                    "Order cannot contain more than " + MAX_ITEMS_PER_ORDER + " items");
        }

        for (OrderItem item : order.getItems()) {
            if (item.getQuantity() < 0) {
                throw new IllegalArgumentException("Quantity must be positive for " + item.getSku());
            }
        }

        if (!order.getCustomerEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
