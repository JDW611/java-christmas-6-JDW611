package christmas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.models.DiscountInfo;
import christmas.models.Order;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {
    private final InputStream systemIn = System.in;
    Order order = new Order();
    EventDiscountService eventDiscountService;
    EventService eventService;
    InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
        order = new Order();
        eventService = new EventService();
        eventDiscountService = new EventDiscountService();
    }

    void setUpInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    void restoreSystemInput() {
        System.setIn(systemIn);
    }
    @DisplayName("모든 할인 종류에 대해 확인")
    @Test
    void createDiscountStrategies() {
        setUpInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        final int visitDate = 3;
        order = eventService.convertToMenuItems(inputView.readMenu(), visitDate);
        final int orderTotalPrice = eventService.calculateOrderTotalPrice(order);


        List<DiscountInfo> discountInfos = eventDiscountService.caculateDiscount(order, orderTotalPrice);

        Map<String, Integer> expectedDiscounts = new HashMap<>();
        expectedDiscounts.put("크리스마스 디데이 할인", 1200);
        expectedDiscounts.put("평일 할인", 4046);
        expectedDiscounts.put("특별 할인", 1000);
        expectedDiscounts.put("증정 이벤트", 25000);

        for (DiscountInfo discountInfo : discountInfos) {
            assertTrue(expectedDiscounts.containsKey(discountInfo.getName()));
            assertEquals(expectedDiscounts.get(discountInfo.getName()).intValue(), discountInfo.getDiscountPrice());
        }

        assertEquals(expectedDiscounts.size(), discountInfos.size());
    }

    @DisplayName("총 혜택 금액 확인")
    @Test
    void checkTotalDiscountPrice() {
        setUpInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        final int visitDate = 3;
        final int expectedDiscountPrice = 31246;
        order = eventService.convertToMenuItems(inputView.readMenu(), visitDate);
        final int orderTotalPrice = eventService.calculateOrderTotalPrice(order);

        final int discountTotalPrice = eventDiscountService.caculateTotalDiscount(order, orderTotalPrice);

        assertEquals(expectedDiscountPrice, discountTotalPrice);
    }

    @DisplayName("실제 지불 금액 확인")
    @Test
    void checkPayPrice() {
        setUpInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        final int visitDate = 3;
        final int expectedPayPrice = 135754;
        order = eventService.convertToMenuItems(inputView.readMenu(), visitDate);
        final int orderTotalPrice = eventService.calculateOrderTotalPrice(order);

        final int payPrice = eventDiscountService.caculateExpectedPayPrice(order, orderTotalPrice);

        assertEquals(expectedPayPrice, payPrice);
    }
}
