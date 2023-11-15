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
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (items.containsKey(item)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
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

    public void validateOrder() {
        checkOrderJustDrink();
        checkOrderMaxQuantity();
    }

    private void checkOrderJustDrink() {
        if (items.keySet()
                .stream()
                .allMatch(menu -> menu.getCategory().equals("Drink")))
        {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void checkOrderMaxQuantity() {
        int totalQuantity = items.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
