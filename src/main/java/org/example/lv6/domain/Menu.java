package org.example.lv6.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 메뉴를 나타내는 클래스이다.
 *
 * - `category`: 메뉴 카테고리의 이름
 * - `menuItems`: 카테고리 내 `MenuItem`(메뉴 항목)의 리스트
 */
public class Menu {
    private final String category;
    private final List<MenuItem> menuItems;

    public Menu(String category) {
        this.category = category;
        this.menuItems = new ArrayList<>();
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * 메뉴 항목을 `menuItems`에 추가한다.
     *
     * @param menuItem 추가할 메뉴 항목
     */
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    /**
     * 메뉴 항목을 순서대로 출력한다.
     *
     * 각 메뉴 항목은 번호와 함께 출력되며, 번호는 1부터 시작한다.
     */
    public void printMenuItems() {
        AtomicInteger i = new AtomicInteger(1);
        menuItems.forEach(m -> System.out.println(i.getAndIncrement() + ". " + m));
    }
}
