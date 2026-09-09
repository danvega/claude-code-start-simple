package dev.danvega.orders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String id;
    private final String customerEmail;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.NEW;
    private Instant placedAt;

    public Order(String id, String customerEmail) {
        this.id = id;
        this.customerEmail = customerEmail;
    }

    public String getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Instant placedAt) {
        this.placedAt = placedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order other = (Order) o;
        return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Order[" + id + ", " + status + ", " + items.size() + " items]";
    }
}
