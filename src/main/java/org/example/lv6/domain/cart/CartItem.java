package org.example.lv6.domain.cart;

import org.example.lv6.domain.menu.MenuItem;

/**
 * 장바구니에 담긴 메뉴 항목을 나타내는 클래스이다.
 *
 * - `menuItem`: 장바구니에 담긴 메뉴 항목
 * - `count`: 해당 메뉴 항목의 수량
 */
public class CartItem {
    private final MenuItem menuItem;
    private int count = 1;

    /**
     * 주어진 메뉴 항목으로 `CartItem` 객체를 생성한다.
     *
     * @param menuItem 장바구니에 담을 메뉴 항목
     */
    public CartItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * 장바구니에 담긴 메뉴 항목을 반환한다.
     *
     * @return 메뉴 항목
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * 장바구니에 담긴 메뉴 항목의 총 가격을 계산하여 반환한다.
     *
     * @return 메뉴 항목의 총 가격
     */
    public float getPrice() {
        return menuItem.getPrice() * count;
    }

    /**
     * 메뉴 항목의 수량을 1 증가시킨다.
     */
    public void countUp() {
        count++;
    }

    /**
     * 장바구니 항목의 정보를 문자열 형식으로 반환한다.
     *
     * @return 메뉴 항목과 수량을 포함한 문자열
     */
    @Override
    public String toString() {
        return menuItem + " (" + count + "개)";
    }
}
