package christmas.service;

import static christmas.models.Promotion.isChampagneEligible;

import christmas.enums.Badge;
import christmas.enums.Menu;
import christmas.models.Order;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class EventService {
    public Order convertToMenuItems(String customerOrder, int visitDate) {
        Map<String, Integer> orderDetails = convertToOrderDetails(customerOrder);
        Order order = new Order();

        orderDetails.forEach((menuName, quantity) -> {
            Menu menu = Menu.checkExistenceMenu(menuName);
            order.addItem(menu, quantity);
        });
        order.setVisitDate(visitDate);
        order.validateOrder();

        return order;
    }

    public int calculateOrderTotalPrice(Order orderDetails) {
        return orderDetails.calculateTotalPrice(orderDetails);
    }

    public String giveGift(int orderTotalPrice) {
        return isChampagneEligible(orderTotalPrice);
    }

    private Map<String, Integer> convertToOrderDetails(String order) {
        return Arrays.stream(order.split(","))
                .map(item -> item.split("-"))
                .collect(Collectors.toMap(
                        parts -> parts[0].trim(),
                        parts -> Integer.parseInt(parts[1].trim())
                ));
    }

    public String checkBadgesType(int totalDiscountPrice) {
        if (totalDiscountPrice >= 20000) {
            return Badge.SANTA.getBadgeName();
        }

        if (totalDiscountPrice >= 10000) {
            return Badge.TREE.getBadgeName();
        }

        if (totalDiscountPrice >= 5000) {
            return Badge.STAR.getBadgeName();
        }
        return Badge.NONE.getBadgeName();
    }
}
