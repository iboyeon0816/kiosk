package org.example.lv6.domain.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 특정 카테고리에 속하는 메뉴 항목의 집합을 나타내는 클래스이다.
 *
 * - `category`: 메뉴의 카테고리
 * - `menuItems`: 해당 카테고리에 속하는 `MenuItem`(메뉴 항목)의 리스트
 */
public class Menu {
    private final String category;
    private final List<MenuItem> menuItems = new ArrayList<>();

    /**
     * 주어진 카테고리를 사용하여 `Menu` 객체를 생성한다.
     *
     * @param category 메뉴의 카테고리
     */
    public Menu(String category) {
        this.category = category;
    }

    /**
     * 메뉴의 카테고리를 반환한다.
     *
     * @return 메뉴의 카테고리
     */
    public String getCategory() {
        return category;
    }

    /**
     * 메뉴에 포함된 모든 메뉴 항목의 리스트를 반환한다.
     *
     * @return 메뉴 항목 리스트
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * 메뉴 항목을 메뉴에 추가한다.
     *
     * @param menuItem 추가할 메뉴 항목
     */
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    /**
     * 메뉴에 포함된 모든 메뉴 항목을 순서대로 출력한다.
     *
     * 각 메뉴 항목은 번호와 함께 출력되며, 번호는 1부터 시작한다.
     */
    public void printMenuItems() {
        menuItems.forEach(i -> System.out.println((menuItems.indexOf(i) + 1) + ". " + i));
    }
}
