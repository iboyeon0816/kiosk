package org.example.lv6.domain.enums;

/**
 * 키오스의 사용자 유형을 정의한 Enum 클래스이다.
 *
 * - `description`: 각 사용자 유형에 대한 설명
 * - `discountRate`: 해당 사용자 유형에 적용되는 할인율
 *
 * 정의된 사용자 유형은 다음과 같다:
 * - `NATIONAL_MERIT`: 국가유공자, 10% 할인
 * - `MILITARY`: 군인, 5% 할인
 * - `STUDENT`: 학생, 3% 할인
 * - `GENERAL`: 일반, 할인 없음
 */
public enum UserType {
    NATIONAL_MERIT("국가유공자", 10),
    MILITARY("군인", 5),
    STUDENT("학생", 3),
    GENERAL("일반", 0);

    private final String description;
    private final int discountRate;

    /**
     * 열거형 상수를 초기화한다.
     *
     * @param description 사용자 유형에 대한 설명
     * @param discountRate 해당 사용자 유형의 할인율
     */
    UserType(String description, int discountRate) {
        this.description = description;
        this.discountRate = discountRate;
    }

    /**
     * 사용자 유형에 해당하는 할인율을 반환한다.
     *
     * @return 할인율(%)
     */
    public Integer getDiscountRate() {
        return discountRate;
    }

    /**
     *
     */
    public static void printAllTypes() {
        UserType[] userTypes = values();
        for (int i = 1; i <= userTypes.length; i++) {
            System.out.println(i + ". " + userTypes[i - 1]);
        }
    }

    /**
     * 사용자 유형의 세부 정보를 문자열 형식으로 반환한다.
     * - 설명, 할인율을 포함하며, 일정한 형식으로 출력된다.
     *
     * @return 사용자 유형의 세부 정보를 포함한 문자열
     */
    @Override
    public String toString() {
        return description + " ".repeat(5 - description.length()) + " : " + discountRate + "%";
    }
}
