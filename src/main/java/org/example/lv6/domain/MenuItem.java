package org.example.lv6.domain;

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

    public MenuItem(String name, Float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " ".repeat(13 - name.length()) + " | W " + price + " | " + description;
    }
}