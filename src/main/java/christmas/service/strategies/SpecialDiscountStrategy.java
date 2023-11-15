package christmas.service.strategies;

import christmas.models.DiscountInfo;
import christmas.models.Order;
import java.util.Arrays;
import java.util.List;

public class SpecialDiscountStrategy implements DiscountStrategy {

    private static final int SPECIAL_DISCOUNT = 1000;
    private static final List<Integer> SPECIAL_DAYS = Arrays.asList(3, 10, 17, 24, 25, 31);


    @Override
    public DiscountInfo calculateDiscount(Order order, int orderTotalPrice) {
        if (SPECIAL_DAYS.contains(order.getVisitDate().getDayOfMonth()) && orderTotalPrice >= 10000) {
            return new DiscountInfo("특별 할인", SPECIAL_DISCOUNT);
        }
        return new DiscountInfo("특별 할인", 0);
    }
}
