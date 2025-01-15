package org.example.lv6.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 장바구니을 나타내는 클래스이다.
 *
 * - `cartItems`: 장바구니에 담긴 메뉴 항목의 리스트
 */
public class Cart {

    private List<MenuItem> cartItems = new ArrayList<>();

    /**
     * 메뉴 항목을 장바구니에 추가한다.
     *
     * @param menuItem 장바구니에 추가할 메뉴 항목
     */
    public void addCartItem(MenuItem menuItem) {
        cartItems.add(menuItem);
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
                .mapToDouble(MenuItem::getPrice)
                .sum();
    }

    /**
     * 주어진 문자열과 대소문자 구분 없이 동일한 이름을 가진 메뉴 항목을 장바구니에서 제거한다.
     *
     * @param itemName 제거할 메뉴 항목의 이름
     */
    public void removeItemByName(String itemName) {
        cartItems = cartItems.stream()
                .filter(i -> !i.getName().equalsIgnoreCase(itemName))
                .collect(Collectors.toList());
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
