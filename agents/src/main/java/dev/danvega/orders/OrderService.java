package dev.danvega.orders;

import java.time.Instant;
import java.util.List;

public class OrderService {

    private final OrderRepository repository;
    private final InventoryService inventory;
    private final PricingService pricing;
    private final OrderValidator validator;

    public OrderService(OrderRepository repository,
                        InventoryService inventory,
                        PricingService pricing,
                        OrderValidator validator) {
        this.repository = repository;
        this.inventory = inventory;
        this.pricing = pricing;
        this.validator = validator;
    }

    public Order place(Order order) {
        validator.validate(order);

        if (!inventory.canFulfill(order)) {
            throw new IllegalStateException("Insufficient stock for order " + order.getId());
        }

        inventory.reserve(order);
        order.setPlacedAt(Instant.now());
        order.setStatus(OrderStatus.PAID);
        repository.save(order);
        return order;
    }

    public void cancel(String orderId) {
        try {
            Order order = repository.findById(orderId).orElseThrow();
            order.setStatus(OrderStatus.CANCELLED);
            repository.save(order);
        } catch (Exception e) {
            // order was already gone
        }
    }

    public double totalFor(String orderId, DiscountPolicy policy) {
        Order order = repository.findById(orderId).orElse(null);
        return pricing.total(order, policy);
    }

    public List<Order> ordersFor(String email) {
        return repository.findByCustomer(email);
    }
}
