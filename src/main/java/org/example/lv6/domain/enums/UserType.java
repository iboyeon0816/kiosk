package org.example.lv6.domain.enums;

public enum UserType {
    NATIONAL_MERIT("국가유공자", 10),
    MILITARY("군인", 5),
    STUDENT("학생", 3),
    GENERAL("일반", 0);

    private final String description;
    private final int discountRate;

    UserType(String description, int discountRate) {
        this.description = description;
        this.discountRate = discountRate;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    @Override
    public String toString() {
        return description + " ".repeat(5 - description.length()) + " : " + discountRate + "%";
    }
}
