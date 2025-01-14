package org.example.lv6.app;

import org.example.lv6.domain.Cart;
import org.example.lv6.domain.Menu;
import org.example.lv6.domain.MenuItem;
import org.example.lv6.domain.enums.UserType;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private static final String INVALID_INPUT_MESSAGE = "메뉴판에 존재하는 번호를 입력해주세요.";

    private final Scanner scanner = new Scanner(System.in);
    private final Cart cart = new Cart();
    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        while(true) {
            // 메인 메뉴 출력
            printMainMenu();

            // 장바구니 확인
            if (!cart.isEmpty()) { // 주문 메뉴 출력
                printOrderMenu();
            }

            // 사용자 입력 받기
            int maxOption = calculateMaxOption();
            int userOption = getUserInput(0, maxOption);

            // 사용자 입력 처리
            if (isMenuSelection(userOption)) { // 메뉴 선택
                handleMenu(menus.get(userOption - 1));
            } else if (isOrder(userOption)) { // 주문
                handleOrder();
            } else if (isReset(userOption)) { // 주문 취소
                handelReset();
            } else { // 프로그램 종료
                terminate();
                break;
            }

            System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");
        }
    }

    private void printMainMenu() {
        printMenuName("MAIN");
        printCategories();
        printZeroOption("종료          | 종료");
    }

    private void printOrderMenu() {
        System.out.println();
        printMenuName("ORDER");
        printOrderMenuItems();
    }

    private int calculateMaxOption() {
        return cart.isEmpty() ? menus.size() : menus.size() + 2;
    }

    private boolean isMenuSelection(int userOption) {
        return userOption <= menus.size() && userOption > 0;
    }

    private boolean isOrder(int userOption) {
        return userOption == menus.size() + 1;
    }

    private boolean isReset(int userOption) {
        return userOption == menus.size() + 2;
    }

    private void handleMenu(Menu menu) {
        List<MenuItem> menuItems = menu.getMenuItems();

        // 메뉴 항목 출력 및 사용자 입력 받기
        printMenuItems(menu);
        int userOption = getUserInput(0, menuItems.size());
        if (userOption != 0) { // 메뉴 항목 선택
            handleCartAddition(menuItems.get(userOption - 1));
        }
    }

    private void handleOrder() {
        while (true) {
            // 장바구니 아이템 목록 출력
            printCartItems();

            // 사용자 입력 받기
            int userInput = getUserInput(1, 3);
            if (userInput == 1) { // 주문하기
                int discountRate = getDiscountRate();
                float totalPrice = cart.getTotalPrice() * (100 - discountRate) / 100;
                System.out.println("\n주문이 완료되었습니다. 금액은 W " + totalPrice + " 입니다.");
                cart.clear();
                break;
            }
            else if (userInput == 2) { // 장바구니에서 항목 제거
                System.out.println("\n제거할 항목의 이름을 입력하세요.");
                String itemName = scanner.next();
                cart.removeItemByName(itemName);
                System.out.println("\n제거되었습니다.");

                if (cart.isEmpty()) {
                    break;
                }
            }
            else {
                break;
            }
        }
    }

    private int getDiscountRate() {
        System.out.println("\n할인 정보를 입력해주세요.");
        int i = 1;
        for (UserType userType : UserType.values()) {
            System.out.println(i++ + ". " + userType);
        }

        int userInput = getUserInput(1, UserType.values().length);
        return UserType.values()[userInput - 1].getDiscountRate();
    }

    private void handelReset() {
        System.out.println("\n주문이 취소되었습니다.");
        cart.clear();
    }

    private void terminate() {
        System.out.println("\n프로그램을 종료합니다.");
        cart.clear();
    }

    private void handleCartAddition(MenuItem selectedItem) {
        // 선택한 항목을 장바구니에 추가할지 묻는 메시지 출력
        printAddToCart(selectedItem);

        // 사용자 입력 받기
        int userInput = getUserInput(1, 2);
        if (userInput == 1) { // 장바구니 담기
            cart.addCartItem(selectedItem);
            System.out.println("\n" + selectedItem.getName() + " 이 장바구니에 추가되었습니다.");
        }
        else { // 취소
            System.out.println("\n취소되었습니다.");
        }
    }

    private void printMenuItems(Menu menu) {
        System.out.println();
        printMenuName(menu.getCategory());
        menu.printMenuItems();
        printZeroOption("뒤로가기");
    }

    private void printAddToCart(MenuItem menuItem) {
        System.out.println("선택한 메뉴: " + menuItem);
        System.out.println("\n\"" + menuItem + "\"");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
    }

    private void printCartItems() {
        System.out.println("\n아래와 같이 주문 하시겠습니까?\n[ Orders ]");
        cart.printCartItems();
        System.out.println("\n[ Total ]\nW " + cart.getTotalPrice() + "\n\n1. 주문       2. 항목 제거     3. 메뉴판");
    }

    private void printMenuName(String menuName) {
        System.out.println("[ " + menuName.toUpperCase() + " MENU ]");
    }

    private void printCategories() {
        int size = menus.size();
        for (int i = 1; i <= size; i++) {
            System.out.println(i + ". " + menus.get(i - 1).getCategory());
        }
    }

    private void printZeroOption(String label) {
        System.out.println("0. " + label);
    }

    private void printOrderMenuItems() {
        int startNum = menus.size() + 1;
        System.out.println(startNum + ". " + "Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println((startNum + 1) + ". " + "Cancel       | 진행중인 주문을 취소합니다.");
    }

    private int getUserInput(int minOption, int maxOption) {
        while(true) {
            try {
                int num = scanner.nextInt();
                if (num < minOption || num > maxOption)
                    throw new IllegalArgumentException();
                return num;
            } catch (InputMismatchException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }
}
