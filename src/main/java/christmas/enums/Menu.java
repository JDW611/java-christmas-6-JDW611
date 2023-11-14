package christmas.enums;
public enum Menu {
    MUSHROOM_SOUP("양송이수프", "Appetizer", 6000),
    TAPAS("타파스", "Appetizer", 5500),
    CAESAR_SALAD("시저샐러드", "Appetizer", 8000),
    T_BONE_STEAK("티본스테이크", "Main", 55000),
    BBQ_RIBS("바비큐립", "Main", 54000),
    SEAFOOD_PASTA("해산물파스타", "Main", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", "Main", 25000),
    CHOCOLATE_CAKE("초코케이크", "Dessert", 15000),
    ICE_CREAM("아이스크림", "Dessert", 5000),
    ZERO_COLA("제로콜라", "Drink", 3000),
    RED_WINE("레드와인", "Drink", 60000),
    CHAMPAGNE("샴페인", "Drink", 25000);

    private final String name;
    private final String category;
    private final int price;


    Menu(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }


    public static Menu checkMenu(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
