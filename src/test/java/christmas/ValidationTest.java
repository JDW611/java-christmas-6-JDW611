package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.enums.Menu;
import christmas.models.Order;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidationTest{
    private final InputStream systemIn = System.in;
    private final String INPUT_MENU_ERR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private final String INPUT_DATE_ERR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    InputView inputView;
    Order order;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
        order = new Order();
    }

    void setUpInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @DisplayName("방문 날짜가 1~31사이의 숫자가 아니면 예외가 발생한다.")
    @Test
    void inputVisitDateByOverSize() {
        setUpInput("32");

        assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_DATE_ERR_MESSAGE);
    }

    @DisplayName("음료만 주문하면 예외가 발생한다.")
    @Test
    void inputMenuOnlyDrink() {
        order.addItem(Menu.ZERO_COLA, 1);


        assertThatThrownBy(() -> order.validateOrder())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MENU_ERR_MESSAGE);
    }

    @DisplayName("주문 메뉴가 20개 초과시 예외가 발생한다.")
    @Test
    void inputMenuByOverSize() {
        order.addItem(Menu.BBQ_RIBS, 21);

        assertThatThrownBy(() -> order.validateOrder())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MENU_ERR_MESSAGE);
    }

    @DisplayName("메뉴판에 없는 메뉴 입력시 예외가 발생한다.")
    @Test
    void inputNoneMenuItem() {
        assertThatThrownBy(() -> Menu.checkExistenceMenu("초코파이"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MENU_ERR_MESSAGE);
    }

    @DisplayName("주문한 메뉴의 개수가 1미만인 경우 예외가 발생한다.")
    @Test
    void inputMenuByUnderSize(){
        assertThatThrownBy(() -> order.addItem(Menu.BBQ_RIBS, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MENU_ERR_MESSAGE);
    }

    @DisplayName("메뉴 형식이 예시와 다른 경우 예외가 발생한다.")
    @Test
    void inputMenuByDifferentType() {
        setUpInput("양송이수프-a");

        assertThatThrownBy(() -> inputView.readMenu())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MENU_ERR_MESSAGE);
    }

    @DisplayName("메뉴를 중복으로 입력한 경우 예외가 발생한다.")
    @Test
    void inputMenuByDuplicateString() {
        order.addItem(Menu.BBQ_RIBS, 1);

        assertThatThrownBy(() -> order.addItem(Menu.BBQ_RIBS, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MENU_ERR_MESSAGE);
    }
}
