package org.example.lv6.domain.cart;

import org.example.lv6.domain.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 장바구니을 나타내는 클래스이다.
 *
 * - `cartItems`: 장바구니 항목의 리스트
 */
public class Cart {

    private final List<CartItem> cartItems = new ArrayList<>();

    /**
     * 메뉴 항목을 장바구니에 추가한다.
     *
     * 동일한 메뉴 항목이 이미 장바구니에 존재하면, 해당 항목의 수량을
     * 1 증가시키고, 그렇지 않으면 새로운 항목을 장바구니에 추가한다.
     *
     * @param menuItem 장바구니에 추가할 메뉴 항목
     */
    public void addCartItem(MenuItem menuItem) {
        cartItems.stream()
                .filter(c -> c.getMenuItem().getName().equals(menuItem.getName()))
                .findFirst()
                .ifPresentOrElse(
                        CartItem::countUp,
                        () -> cartItems.add(new CartItem(menuItem))
                );
    }

    /**
     * 장바구니에 담긴 모든 항목을 순서대로 출력한다.
     */
    public void printCartItems() {
        cartItems.forEach(System.out::println);
    }

    /**
     * 장바구니에 담긴 모든 항목들의 총 금액을 계산하여 반환한다.
     *
     * @return 장바구니 항목들의 총 금액
     */
    public float getTotalPrice() {
        return (float) cartItems.stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
    }

    /**
     * 주어진 문자열과 대소문자 구분 없이 동일한 이름을 가진 항목을
     * 장바구니에서 제거한다.
     *
     * @param itemName 제거할 메뉴 항목의 이름
     * @return 제거 성공 시 true, 실패 시 false
     */
    public boolean removeItemByName(String itemName) {
        return cartItems.removeIf(c -> c.getMenuItem().getName().equalsIgnoreCase(itemName));
    }

    /**
     * 장바구니가 비었는지 확인한다.
     *
     * @return 장바구니가 비었으면 true, 그렇지 않으면 false
     */
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    /**
     * 장바구니를 비운다.
     */
    public void clear() {
        cartItems.clear();
    }
}
