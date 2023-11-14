package christmas.service;

import christmas.models.DiscountInfo;
import christmas.models.Order;
import christmas.service.strategies.ChristmasDayDiscountStrategy;
import christmas.service.strategies.DiscountStrategy;
import christmas.service.strategies.GiftEventStrategy;
import christmas.service.strategies.SpecialDiscountStrategy;
import christmas.service.strategies.WeekdayDiscountStrategy;
import christmas.service.strategies.WeekendDiscountStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventDiscountService {
    private final List<DiscountStrategy> strategies = Arrays.asList(
            new WeekendDiscountStrategy(),
            new WeekdayDiscountStrategy(),
            new ChristmasDayDiscountStrategy(),
            new GiftEventStrategy(),
            new SpecialDiscountStrategy()
    );

    public List<DiscountInfo> caculateDiscount(Order order, int orderTotalPrice) {
        List<DiscountInfo> discountInfos = new ArrayList<>();

        for (DiscountStrategy strategy : strategies) {
            discountInfos.add(strategy.calculateDiscount(order, orderTotalPrice));
        }

        return filterProgressDiscount(discountInfos);
    }

    public int caculateTotalDiscount(Order order, int orderTotalPrice) {
        List<DiscountInfo> discountInfos = caculateDiscount(order, orderTotalPrice);
        return discountInfos.stream()
                .filter(discountInfo -> discountInfo.getDiscountPrice() > 0)
                .mapToInt(DiscountInfo::getDiscountPrice)
                .sum();
    }

    public int caculateExpectedPayPrice(Order order, int orderTotalPrice) {
        int discountPrice = caculateTotalDiscountWithOutGiftPrice(order, orderTotalPrice);
        return orderTotalPrice - discountPrice;
    }

    private List<DiscountInfo> filterProgressDiscount(List<DiscountInfo> discountInfos) {
        return discountInfos.stream()
                .filter(discountInfo -> discountInfo.getDiscountPrice() > 0)
                .collect(Collectors.toList());
    }

    private int caculateTotalDiscountWithOutGiftPrice(Order order, int orderTotalPrice) {
        List<DiscountInfo> discountInfos = caculateDiscount(order, orderTotalPrice);
        return discountInfos.stream()
                .filter(discountInfo -> discountInfo.getDiscountPrice() > 0 && !discountInfo.getName().equals("증정 이벤트"))
                .mapToInt(DiscountInfo::getDiscountPrice)
                .sum();
    }
}
