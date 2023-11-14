package christmas.models;

import christmas.enums.Menu;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final Map<Menu, Integer> items;
    private LocalDate visitDate;

    public Order() {
        this.items = new HashMap<>();
    }

    public void addItem(Menu item, int quantity) {
        items.put(item, quantity);
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(int customerVisitDate) {
        this.visitDate = LocalDate.of(2023, 12, customerVisitDate);
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
