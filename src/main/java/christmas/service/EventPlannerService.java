package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class EventPlannerService {
    public Order convertToMenuItems(String customerOrder) {
        Map<String, Integer> orderDetails = convertToOrderDetails(customerOrder);
        Order order = new Order();

        orderDetails.forEach((menuName, quantity) -> {
            Menu menu = Menu.checkMenu(menuName);
            order.addItem(menu, quantity);
        });

        return order;
    }

    public int calculateOrderTotalPrice(Order orderDetails) {
        return orderDetails.calculateTotalPrice(orderDetails);
    }

    private Map<String, Integer> convertToOrderDetails(String order) {
        return Arrays.stream(order.split(","))
                .map(item -> item.split("-"))
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> Integer.parseInt(parts[1].trim())
                ));
    }
}
