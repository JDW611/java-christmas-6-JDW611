package christmas.domain;

public class Promotion {
    private static final int CHAMPAGNE_GIFT_LIMIT = 120000;

    public static String isChampagneEligible(int totalPriceBeforeDiscount) {
        if(totalPriceBeforeDiscount >= CHAMPAGNE_GIFT_LIMIT){
            return "샴페인 1개";
        }
        return "없음";
    }
}
