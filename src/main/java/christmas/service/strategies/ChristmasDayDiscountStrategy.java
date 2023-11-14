package christmas.service.strategies;

import christmas.models.DiscountInfo;
import christmas.models.Order;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDayDiscountStrategy implements DiscountStrategy{
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 25);
    private static final int START_DISCOUNT = 1000;
    private static final int DAILY_INCREMENT = 100;

    @Override
    public DiscountInfo calculateDiscount(Order order) {
        LocalDate customerVisitDate = order.getVisitDate();
        if (customerVisitDate.isBefore(START_DATE) || customerVisitDate.isAfter(END_DATE)) {
            return new DiscountInfo("크리스마스 디데이 할인", 0);
        }
        long daysBetween = ChronoUnit.DAYS.between(START_DATE, customerVisitDate);

        int discountPrice = START_DISCOUNT + ((int)daysBetween * DAILY_INCREMENT);
        return new DiscountInfo("크리스마스 디데이 할인", discountPrice);
    }
}
