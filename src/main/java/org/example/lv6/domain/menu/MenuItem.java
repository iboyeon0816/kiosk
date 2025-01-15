package org.example.lv6.domain.menu;

/**
 * 메뉴 항목을 나타내는 클래스이다.
 *
 * - `name`: 메뉴 항목의 이름
 * - `price`: 메뉴 항목의 가격
 * - `description`: 메뉴 항목에 대한 설명
 */
public class MenuItem {
    private final String name;
    private final Float price;
    private final String description;

    /**
     * 주어진 이름, 가격, 설명을 사용하여 `MenuItem` 객체를 생성한다.
     *
     * @param name 메뉴 항목의 이름
     * @param price 메뉴 항목의 가격
     * @param description 메뉴 항목에 대한 설명
     */
    public MenuItem(String name, Float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * 메뉴 항목의 이름을 반환한다.
     *
     * @return 메뉴 항목의 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 메뉴 항목의 가격을 반환한다.
     *
     * @return 메뉴 항목의 가격
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 메뉴 항목의 세부 정보를 문자열 형식으로 반환한다.
     * - 이름, 가격, 설명을 포함하며, 일정한 형식으로 출력된다.
     *
     * @return 메뉴 항목의 세부 정보를 포함한 문자열
     */
    @Override
    public String toString() {
        return name + " ".repeat(13 - name.length()) + " | W " + price + " | " + description;
    }
}
