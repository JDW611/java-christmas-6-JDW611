package christmas.enums;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String badgeName;
    private final int benefitAmount;

    Badge(String badgeName, int benefitAmount) {
        this.badgeName = badgeName;
        this.benefitAmount = benefitAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
