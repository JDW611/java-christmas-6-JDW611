package christmas.service.strategies;

import christmas.models.DiscountInfo;
import christmas.models.Order;

public interface DiscountStrategy {
    DiscountInfo calculateDiscount(Order order);
}
