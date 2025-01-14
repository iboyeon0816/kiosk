package org.example.lv6.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {

    private List<MenuItem> cartItems = new ArrayList<>();

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

    public void removeItemByName(String itemName) {
        cartItems = cartItems.stream()
                .filter(i -> !i.getName().equals(itemName))
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public void clear() {
        cartItems.clear();
    }
}
