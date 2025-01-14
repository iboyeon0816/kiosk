package org.example.lv6.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<MenuItem> cartItems = new ArrayList<>();

    public void addCartItem(MenuItem menuItem) {
        cartItems.add(menuItem);
    }

    public void printCartItems() {
        cartItems.forEach(System.out::println);
    }

    public float getTotalPrice() {
        return (float) cartItems.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public void clear() {
        cartItems.clear();
    }
}
