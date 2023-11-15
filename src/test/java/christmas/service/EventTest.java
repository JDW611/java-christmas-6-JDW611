package christmas.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.enums.Menu;
import christmas.models.Order;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {
    private final InputStream systemIn = System.in;
    EventService eventService;
    InputView inputView;
    Order order;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
        order = new Order();
        eventService = new EventService();
    }

    void setUpInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @DisplayName("방문자가 입력한 메뉴와 개수가 Order객체로 변환되는지 테스트")
    @Test()
    void convertToOrderDetail() {
        setUpInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        final int visitDate = 3;

        Order orderDetail = eventService.convertToMenuItems(inputView.readMenu(), visitDate);

        assertThat(orderDetail.getItems().get(Menu.T_BONE_STEAK)).isEqualTo(1);
        assertThat(orderDetail.getItems().get(Menu.BBQ_RIBS)).isEqualTo(1);
        assertThat(orderDetail.getItems().get(Menu.CHOCOLATE_CAKE)).isEqualTo(2);
        assertThat(orderDetail.getItems().get(Menu.ZERO_COLA)).isEqualTo(1);
    }

    @DisplayName("총 주문 금액 계산 결과 확인")
    @Test()
    void calculateTotalOrderPrice() {
        setUpInput("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        final int visitDate = 3;

        Order orderDetail = eventService.convertToMenuItems(inputView.readMenu(), visitDate);

        int orderTotalPrice = eventService.calculateOrderTotalPrice(orderDetail);

        assertThat(orderTotalPrice).isEqualTo(142000);
    }

    @DisplayName("증정 이벤트 여부 확인")
    @Test()
    void isProperGiveGift() {
        final int test1Price = 142000;
        final int test2Price = 10000;

        assertThat(eventService.giveGift(test1Price)).isEqualTo("샴페인 1개");
        assertThat(eventService.giveGift(test2Price)).isEqualTo("없음");
    }

    @DisplayName("할인 가격에 따른 이벤트 배지 부여 확인")
    @Test()
    void isProperGiveBadge() {
        final int test1Price = 6000;
        final int test2Price = 11000;
        final int test3Price = 21000;

        assertThat(eventService.checkBadgesType(test1Price)).isEqualTo("별");
        assertThat(eventService.checkBadgesType(test2Price)).isEqualTo("트리");
        assertThat(eventService.checkBadgesType(test3Price)).isEqualTo("산타");
    }
}
