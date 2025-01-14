package org.example.lv6;

import org.example.lv6.app.Kiosk;
import org.example.lv6.domain.Menu;
import org.example.lv6.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 키오스크를 실행시키는 클래스이다.
 *
 * 메뉴를 초기화하고, 이를 활용해 `Kiosk` 객체를 생성 및 실행한다.
 */
public class Main {

    public static void main(String[] args) {
        // 메뉴 초기화
        List<Menu> menus = initializeMenus();

        // Kiosk 객체 생성 및 실행
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start();
    }

    /**
     * 메뉴를 초기화하여 리스트로 반환한다.
     *
     * 각 메뉴 카테고리(Burgers, Drinks, Desserts)에 대해
     * 해당 카테고리의 메뉴 항목들을 생성하고, 각 카테고리에 메뉴 항목들을 추가한다.
     * 최종적으로 각 메뉴 카테고리를 하나의 리스트에 추가하고, 그 리스트를 반환한다.
     *
     * @return 초기화된 메뉴 리스트
     */
    private static List<Menu> initializeMenus() {
        List<Menu> menus = new ArrayList<>();

        Menu burgerMenu = new Menu("Burgers");
        burgerMenu.addMenuItem(new MenuItem("ShackBurger", 6.9f, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("SmokeShack", 8.9f, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Cheeseburger", 6.9f, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Hamburger", 5.4f, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Menu drinkMenu = new Menu("Drinks");
        drinkMenu.addMenuItem(new MenuItem("Coke", 2.5f, "시원한 탄산음료 코카콜라"));
        drinkMenu.addMenuItem(new MenuItem("ZeroCoke", 2.5f, "칼로리가 0인 다이어트 코카콜라"));
        drinkMenu.addMenuItem(new MenuItem("Sprite", 2.5f, "레몬과 라임이 어우러진 탄산음료"));

        Menu dessertMenu = new Menu("Desserts");
        dessertMenu.addMenuItem(new MenuItem("IceCreamCone", 3.5f, "클래식 바닐라 아이스크림 콘"));
        dessertMenu.addMenuItem(new MenuItem("Vanilla Shake", 5.0f, "부드럽고 달콤한 바닐라 밀크셰이크"));

        menus.add(burgerMenu);
        menus.add(drinkMenu);
        menus.add(dessertMenu);

        return menus;
    }
}