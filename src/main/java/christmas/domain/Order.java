package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private final Map<Menu, Integer> items;

    public Order() {
        this.items = new HashMap<>();
    }

    public void addItem(Menu item, int quantity) {
        items.put(item, quantity);
    }

    public Map<Menu, Integer> getItems() {
        return items;
    }

    public int calculateTotalPrice(Order orderDetails) {
        return orderDetails.getItems()
                .entrySet()
                .stream()
                .mapToInt(menu -> menu.getKey().getPrice() * menu.getValue())
                .sum();
    }
}
