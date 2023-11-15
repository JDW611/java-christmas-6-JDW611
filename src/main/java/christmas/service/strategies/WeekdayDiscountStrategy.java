package christmas.service.strategies;

import christmas.models.DiscountInfo;
import christmas.enums.Menu;
import christmas.models.Order;
import java.time.DayOfWeek;
import java.util.Map;

public class WeekdayDiscountStrategy implements DiscountStrategy {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public DiscountInfo calculateDiscount(Order order, int orderTotalPrice) {
        Map<Menu, Integer> menus = order.getItems();
        DayOfWeek orderDayOfWeek = order.getVisitDate().getDayOfWeek();

        if (orderDayOfWeek == DayOfWeek.FRIDAY || orderDayOfWeek == DayOfWeek.SATURDAY || orderTotalPrice < 10000) {
            return new DiscountInfo("평일 할인", 0);
        }
        int weekDiscount = menus.entrySet().stream()
                .filter(menu -> menu.getKey().getCategory().equals("Dessert"))
                .mapToInt(menu -> menu.getValue() * DISCOUNT_PER_DESSERT)
                .sum();
        return new DiscountInfo("평일 할인", weekDiscount);
    }
}
