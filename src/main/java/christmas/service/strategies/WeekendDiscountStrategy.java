package christmas.service.strategies;

import christmas.models.DiscountInfo;
import christmas.enums.Menu;
import christmas.models.Order;
import java.time.DayOfWeek;
import java.util.Map;

public class WeekendDiscountStrategy implements DiscountStrategy{
    private static final int DISCOUNT_PER_MAIN = 2023;
    @Override
    public DiscountInfo calculateDiscount(Order order, int orderTotalPrice) {
        Map<Menu, Integer> menus = order.getItems();
        DayOfWeek orderDayOfWeek = order.getVisitDate().getDayOfWeek();

        if ((orderDayOfWeek == DayOfWeek.FRIDAY || orderDayOfWeek == DayOfWeek.SATURDAY) && orderTotalPrice >= 10000) {
            int weekendDiscount = menus.entrySet().stream()
                    .filter(menu -> menu.getKey().getCategory().equals("Main"))
                    .mapToInt(menu -> menu.getValue() * DISCOUNT_PER_MAIN)
                    .sum();

            return new DiscountInfo("주말 할인", weekendDiscount);
        }
        return new DiscountInfo("주말 할인", 0);
    }
}
