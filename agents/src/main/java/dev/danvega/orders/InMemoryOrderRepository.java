package dev.danvega.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Backing store for orders. A single instance is shared by every
 * request thread in the running application.
 */
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> store = new HashMap<>();

    @Override
    public void save(Order order) {
        store.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Order> findByCustomer(String email) {
        List<Order> found = new ArrayList<>();
        for (Order order : store.values()) {
            if (order.getCustomerEmail().equals(email)) {
                found.add(order);
            }
        }
        return found;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}
