package dev.danvega.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(String id);

    List<Order> findByCustomer(String email);

    List<Order> findAll();
}
