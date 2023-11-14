package christmas.controller;

import christmas.domain.Order;
import christmas.service.EventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final EventPlannerService eventPlannerService = new EventPlannerService();
    public void run() {
        outputView.printWelcomeMessage();
        int visitDate = inputView.readDate();
        Order orderDetails = eventPlannerService.convertToMenuItems(inputView.readMenu());
        outputView.printEventPreviewForDate(visitDate);
        outputView.printOrderMenuDetail(orderDetails);
        int orderTotalPrice = eventPlannerService.calculateOrderTotalPrice(orderDetails);
        outputView.printOrderTotalPrice(orderTotalPrice);

        String promotionResult = eventPlannerService.givePromotion(orderTotalPrice);
        outputView.printPromotionMenu(promotionResult);
    }

}
