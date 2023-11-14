package christmas.controller;

import christmas.models.DiscountInfo;
import christmas.models.Order;
import christmas.service.EventDiscountService;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class EventController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final EventService eventService = new EventService();
    private final EventDiscountService eventDiscountService = new EventDiscountService();
    public void run() {
        outputView.printWelcomeMessage();
        int visitDate = inputView.readDate();
        Order orderDetails = eventService.convertToMenuItems(inputView.readMenu(), visitDate);
        outputView.printEventPreviewForDate(visitDate);

        outputView.printOrderMenuDetail(orderDetails);

        int orderTotalPrice = eventService.calculateOrderTotalPrice(orderDetails);
        outputView.printOrderTotalPrice(orderTotalPrice);

        String promotionResult = eventService.giveGift(orderTotalPrice);
        outputView.printPromotionMenu(promotionResult);

        List<DiscountInfo> discountInfos = eventDiscountService.caculateDiscount(orderDetails, orderTotalPrice);
        outputView.printDiscountList(discountInfos);

        int totalDiscountPrice = eventDiscountService.caculateTotalDiscount(orderDetails, orderTotalPrice);
        outputView.printTotalDiscountPrice(totalDiscountPrice);


        int expectedPayPrice = eventDiscountService.caculateExpectedPayPrice(orderDetails, orderTotalPrice);
        outputView.printExpectedPayPrice(expectedPayPrice);

        String eventBadge = eventService.checkBadgesType(totalDiscountPrice);
        outputView.printEventBadge(eventBadge);
    }

}
