package christmas.service.strategies;

import christmas.models.DiscountInfo;
import christmas.models.Order;

public class GiftEventStrategy implements DiscountStrategy {
    private static final int CHAMPAGNE_GIFT_LIMIT = 120000;

    @Override
    public DiscountInfo calculateDiscount(Order order, int orderTotalPrice) {
        if (orderTotalPrice >= CHAMPAGNE_GIFT_LIMIT) {
            return new DiscountInfo("증정 이벤트", 25000);
        }
        return new DiscountInfo("증정 이벤트", 0);
    }
}
