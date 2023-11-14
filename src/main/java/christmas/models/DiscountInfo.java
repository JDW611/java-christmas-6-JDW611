package christmas.models;

public class DiscountInfo {
    private String name;
    private int discountPrice;

    public DiscountInfo(String name, int discountPrice) {
        this.name = name;
        this.discountPrice = discountPrice;
    }

    public String getName() {
        return name;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
