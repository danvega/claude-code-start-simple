package dev.danvega.orders;

public class Main {

    public static void main(String[] args) {
        InventoryService inventory = new InventoryService();
        inventory.stock("SPRING-BOOT-4", 10);
        inventory.stock("JAVA-25", 5);

        OrderService service = new OrderService(
                new InMemoryOrderRepository(),
                inventory,
                new PricingService(),
                new OrderValidator());

        Order order = new Order("A-1001", "dan@example.com");
        order.addItem(new OrderItem("SPRING-BOOT-4", 2, 49.99));
        order.addItem(new OrderItem("JAVA-25", 1, 29.99));

        service.place(order);

        DiscountPolicy tenOff = new DiscountPolicy("SAVE10", 10, 50.00);
        System.out.println(order);
        System.out.println("Total: " + service.totalFor("A-1001", tenOff));
    }
}
