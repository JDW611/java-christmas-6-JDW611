package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int visitDate = convertToInt(Console.readLine());
        validateVisitDate(visitDate);
        return visitDate;
    }

    public String readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        validateOrderMenu(input);
        return input;
    }

    private int convertToInt(String input) {
        validateCorrectVistDate(input);
        return Integer.parseInt(input);
    }

    private void validateVisitDate(int visitDate) {
        if (!(visitDate >= 1 && visitDate <= 31)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOrderMenu(String input) {
        String regex = "([가-힣]+-\\d+(,\\s*[가-힣]+-\\d+)*)";
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateCorrectVistDate(String input) {
        if (!input.matches("^(3[01]|[12][0-9]|[1-9])$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
