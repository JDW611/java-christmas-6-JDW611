package christmas.view;

import christmas.models.DiscountInfo;
import christmas.models.Order;
import java.util.List;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreviewForDate(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", date);
    }

    public void printOrderMenuDetail(Order orderMenu) {
        System.out.println("<주문 메뉴>");

        orderMenu.getItems().forEach((menu, quantity) -> {
            String menuName = menu.getName();
            System.out.println(menuName + ": " + quantity + "개");
        });
    }
    public void printOrderTotalPrice(int orderTotalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원\n",orderTotalPrice);
    }
    public void printPromotionMenu(String promotionResult) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(promotionResult);
    }

    public void printDiscountList(List<DiscountInfo> discountInfos) {
        System.out.println("\n<혜택 내역>");
        discountInfos.stream()
                .filter(discountInfo -> discountInfo.getDiscountPrice() > 0)
                .forEach(discountInfo -> {
                    System.out.printf("%s: -%,d원\n", discountInfo.getName(), discountInfo.getDiscountPrice());
                });
    }

    public void printTotalDiscountPrice(int totalDiscountPrice) {
        System.out.println("\n<총혜택 금액>");
        System.out.printf("-%,d원\n", totalDiscountPrice);
    }

    public void printExpectedPayPrice(int expectedPayPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n", expectedPayPrice);
    }

    public void printEventBadge(String Badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(Badge);
    }
}
