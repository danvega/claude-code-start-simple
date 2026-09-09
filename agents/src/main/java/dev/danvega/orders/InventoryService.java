package dev.danvega.orders;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private final Map<String, Integer> stockBySku = new HashMap<>();

    public void stock(String sku, int quantity) {
        stockBySku.put(sku, quantity);
    }

    public int available(String sku) {
        return stockBySku.getOrDefault(sku, 0);
    }

    public boolean canFulfill(Order order) {
        for (OrderItem item : order.getItems()) {
            if (available(item.getSku()) < item.getQuantity()) {
                return false;
            }
        }
        return true;
    }

    public void reserve(Order order) {
        for (OrderItem item : order.getItems()) {
            int remaining = available(item.getSku()) - item.getQuantity();
            stockBySku.put(item.getSku(), remaining);
        }
    }
}
