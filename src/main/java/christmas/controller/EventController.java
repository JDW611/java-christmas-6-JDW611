package christmas.controller;

import christmas.models.DiscountInfo;
import christmas.models.Order;
import christmas.service.EventDiscountService;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import org.mockito.internal.matchers.Or;

public class EventController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final EventService eventService = new EventService();
    private final EventDiscountService eventDiscountService = new EventDiscountService();

    public void run() {
        outputView.printWelcomeMessage();
        Order orderDetails = askToCustomer();

        outputView.printOrderMenuDetail(orderDetails);

        previewEvent(orderDetails);
    }

    private Order askToCustomer() {
        int visitDate = askVisitDate();
        return askOrder(visitDate);
    }

    private int askVisitDate() {
        try {
            return inputView.readDate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askVisitDate();
        }
    }

    private Order askOrder(int visitDate) {
        try {
            Order orderDetails = eventService.convertToMenuItems(inputView.readMenu(), visitDate);
            outputView.printEventPreviewForDate(visitDate);
            return orderDetails;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askOrder(visitDate);
        }
    }

    private void previewEvent(Order orderDetails) {
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
