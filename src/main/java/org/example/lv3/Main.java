package org.example.lv3;

import org.example.lv3.app.Kiosk;
import org.example.lv3.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 키오스크를 실행시키는 클래스이다.
 *
 * 메뉴 항목을 초기화하고, 이를 활용해 `Kiosk` 객체를 생성 및 실행한다.
 */
public class Main {

    public static void main(String[] args) {
        // 메뉴 항목 초기화
        List<MenuItem> menuItems = initializeMenuItems();

        // Kiosk 객체 생성 및 실행
        Kiosk kiosk = new Kiosk(menuItems);
        kiosk.start();
    }

    /**
     * 메뉴 항목을 초기화하여 리스트로 반환한다.
     *
     * `MenuItem` 객체들을 생성해 `ArrayList`에 추가한 후 해당 리스트를 반환한다.
     * `MenuItem`은 이름, 가격, 설명을 포함한 햄버거 메뉴를 나타낸다.
     *
     * @return 초기화된 메뉴 항목 리스트
     */
    private static List<MenuItem> initializeMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("ShackBurger", 6.9f, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9f, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9f, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4f, "비프패티를 기반으로 야채가 들어간 기본버거"));
        return menuItems;
    }
}