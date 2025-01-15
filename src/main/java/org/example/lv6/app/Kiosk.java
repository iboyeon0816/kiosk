package org.example.lv6.app;

import org.example.lv6.domain.Cart;
import org.example.lv6.domain.Menu;
import org.example.lv6.domain.MenuItem;
import org.example.lv6.domain.enums.UserType;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 키오스크를 구현한 클래스이다.
 *
 * 사용자는 이 키오스크를 통해 메뉴를 선택하고, 장바구니에 담거나 주문을 처리할 수 있다.
 */
public class Kiosk {
    private static final String INVALID_INPUT_MESSAGE = "메뉴판에 존재하는 번호를 입력해주세요."; // 잘못된 입력 메시지

    private final Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체
    private final Cart cart = new Cart(); // 장바구니를 관리하는 객체
    private final List<Menu> menus; // 키오스크에서 제공하는 메뉴 리스트

    /**
     * 주어진 메뉴 리스트로 `Kiosk` 객체를 생성한다.
     *
     * @param menus 키오스크에서 제공하는 메뉴 리스트
     */
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    /**
     * 키오스크를 실행하는 메서드이다.
     *
     * 사용자의 입력을 처리하여 메뉴 선택, 장바구니 확인,
     * 주문 진행, 프로그램 종료 등 키오스크의 주요 기능을 제어한다.
     */
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
            int userInput = getUserInput(0, maxOption);

            // 사용자 입력 처리
            if (isMenuSelection(userInput)) { // 메뉴 선택
                handleMenu(menus.get(userInput - 1));
            } else if (isOrder(userInput)) { // 주문
                handleOrder();
            } else if (isReset(userInput)) { // 주문 취소
                handelReset();
            } else { // 프로그램 종료
                terminate();
                break;
            }

            System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");
        }
    }

    /**
     * 메인 메뉴를 출력한다.
     *
     * 1. 메뉴 이름을 "MAIN"으로 출력한다.
     * 2. 상위 카테고리 목록을 출력한다.
     * 3. 종료 옵션(0번)을 출력한다.
     */
    private void printMainMenu() {
        printMenu("MAIN",
                () -> {
                    for (int i = 1; i <= menus.size(); i++) {
                        System.out.println((i) + ". " + menus.get(i - 1).getCategory());
                    }
                },
                "종료");
    }

    /**
     * 주문 메뉴를 출력한다.
     *
     * 1. 메뉴 이름을 "ORDER"로 출력한다.
     * 2. 주문 목록(주문, 주문 취소)을 출력한다.
     */
    private void printOrderMenu() {
        System.out.println();
        printMenu("ORDER",
                () -> {
                    System.out.println((menus.size() + 1) + ". " + "Orders       | 장바구니를 확인 후 주문합니다.");
                    System.out.println((menus.size() + 2) + ". " + "Cancel       | 진행중인 주문을 취소합니다.");
                },
                "");
    }

    /**
     * 선택된 메뉴를 출력한다.
     *
     * 1. 메뉴의 카테고리를 메뉴 이름으로 출력한다.
     * 2. 해당 메뉴에 포함된 메뉴 항목들을 출력한다.
     * 3. 뒤로가기 옵션(0번)을 출력한다.
     *
     * @param menu 사용자가 선택한 메뉴
     */
    private void printMenuItems(Menu menu) {
        System.out.println();
        printMenu(menu.getCategory(), menu::printMenuItems, "뒤로가기");
    }

    /**
     * 공통적인 메뉴 출력 형식을 제공한다.
     *
     * @param menuName 메뉴 이름
     * @param printer 메뉴 내용을 출력하는 람다식
     * @param option 옵션 문자열(종료, 뒤로가기)
     */
    private void printMenu(String menuName, Runnable printer, String option) {
        System.out.println("[ " + menuName.toUpperCase() + " MENU ]");
        printer.run();
        if (!option.isEmpty()) {
            System.out.println("0. " + option);
        }
    }

    /**
     * 사용자가 메인 메뉴에서 선택할 수 있는 최대 번호를 계산한다.
     *
     * 장바구니가 비었을 경우에는 메뉴 항목의 개수만큼, 장바구니에 항목이
     * 있을 경우에는 메뉴 항목과, 주문 항목을 포함한 번호를 반환한다.
     *
     * @return 선택할 수 있는 최대 번호
     */
    private int calculateMaxOption() {
        return cart.isEmpty() ? menus.size() : menus.size() + 2;
    }

    /**
     * 메인 메뉴에서 사용자 입력이 메뉴 선택인지 확인한다.
     *
     * @param userInput 사용자 입력값
     * @return 메뉴 선택 여부
     */
    private boolean isMenuSelection(int userInput) {
        return userInput <= menus.size() && userInput > 0;
    }

    /**
     * 메인 메뉴에서 사용자 입력이 주문인지 확인한다.
     *
     * @param userInput 사용자 입력값
     * @return 주문 여부
     */
    private boolean isOrder(int userInput) {
        return userInput == menus.size() + 1;
    }

    /**
     * 메인 메뉴에서 사용자 입력이 주문 취소인지 확인한다.
     *
     * @param userInput 사용자 입력값
     * @return 주문 취소 여부
     */
    private boolean isReset(int userInput) {
        return userInput == menus.size() + 2;
    }

    /**
     * 메인 메뉴에서 사용자가 메뉴를 선택했을 경우 해당 메뉴를 처리한다.
     *
     * 선택된 메뉴의 항목을 출력하고, 사용자가 메뉴 항목을 선택하면
     * 해당 항목을 장바구니에 추가할지 결정한다.
     *
     * @param menu 선택된 메뉴
     */
    private void handleMenu(Menu menu) {
        List<MenuItem> menuItems = menu.getMenuItems();

        // 메뉴 항목 출력
        printMenuItems(menu);

        // 사용자 입력 받기
        int userInput = getUserInput(0, menuItems.size());
        if (userInput != 0) { // 메뉴 항목 선택
            handleCartAddition(menuItems.get(userInput - 1));
        }
    }

    /**
     * 선택한 항목을 장바구니에 넣을지 결정한다.
     *
     * 사용자에게 선택한 메뉴 항목을 장바구니에 넣을지 물어보는 메시지를
     * 출력하고, 추가를 선택하면 해당 항목을 장바구니에 추가하고, 완료
     * 메시지를 출력한다.
     *
     * @param menuItem 선택된 메뉴 항목
     */
    private void handleCartAddition(MenuItem menuItem) {
        // 장바구니에 메뉴 항목을 추가할지 물어보는 메시지 출력
        printAddToCart(menuItem);

        // 사용자 입력 받기 (1: 확인, 2: 취소)
        int userInput = getUserInput(1, 2);
        if (userInput == 1) { // 장바구니에 추가 선택
            cart.addCartItem(menuItem);
            System.out.println("\n" + menuItem.getName() + " 이 장바구니에 추가되었습니다.");
        }
    }

    /**
     * 장바구니에 선택한 메뉴 항목을 추가할지 물어보는 메시지를 출력한다.
     *
     * @param menuItem 선택한 메뉴 항목
     */
    private static void printAddToCart(MenuItem menuItem) {
        System.out.println("선택한 메뉴: " + menuItem);
        System.out.println("\n\"" + menuItem + "\"");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
    }

    /**
     * 사용자의 주문 요청을 처리한다.
     *
     * 주문 확인 메시지를 출력하고, 항목을 제거하거나,
     * 주문을 완료할 수 있는 옵션을 제공한다.
     */
    private void handleOrder() {
        // 장바구니에서 항목을 제거할 동안 반복
        while (true) {
            // 주문 확인 메시지 출력
            printOrderConfirmation();

            // 사용자 입력 받기 (1: 주문, 2: 항목 제거, 3:메뉴판)
            int userInput = getUserInput(1, 3);
            if (userInput == 1) { // 주문 완료 처리
                processOrder();
                break;
            }
            else if (userInput == 2) { // 장바구니에서 항목 제거
                removeItemFromCart();
                if (cart.isEmpty()) { // 장바구니가 비면 종료
                    break;
                }
            }
            else { // 메뉴판으로 돌아가기
                break;
            }
        }
    }

    /**
     * 주문 확인 메시지를 출력한다.
     *
     * 장바구니에 담긴 항목들과 총 금액을 출력 한 후,
     * '주문 확정', '항목 제거', '메뉴판으로 돌아가기' 옵션을 제공한다.
     */
    private void printOrderConfirmation() {
        System.out.println("\n아래와 같이 주문 하시겠습니까?");
        System.out.println("[ Orders ]");
        cart.printCartItems();
        System.out.println("\n[ Total ]");
        System.out.println("W " + cart.getTotalPrice() + "\n");
        System.out.println("1. 주문       2. 항목 제거     3. 메뉴판");
    }

    /**
     * 주문 처리를 완료한다.
     *
     * 할인 정보를 적용한 후, 최종 금액을 포함한 주문 완료 메세지를 출력하고,
     * 장바구니를 비운다.
     */
    private void processOrder() {
        float totalPrice = applyDiscount();
        System.out.println("\n주문이 완료되었습니다. 금액은 W " + String.format("%.2f", totalPrice) + " 입니다.");
        cart.clear();
    }

    /**
     * 사용자 유형 별 할인 정보를 처리하고, 최종 가격을 계산하여 반환한다.
     *
     * 사용자 유형 목록을 출력하고, 사용자 유형을 입력 받아
     * 해당 할인율을 적용한 가격을 반환한다.
     *
     * @return 할인이 적용된 최종 금액
     */
    private float applyDiscount() {
        System.out.println("\n할인 정보를 입력해주세요.");
        UserType.printAllTypes();

        UserType[] userTypes = UserType.values();
        int userInput = getUserInput(1, userTypes.length);
        int discountRate = userTypes[userInput - 1].getDiscountRate();
        return cart.getTotalPrice() * (100 - discountRate) / 100;
    }

    /**
     * 장바구니에서 항목을 제거한다.
     *
     * 제거할 항목의 이름을 입력 받아, 해당 항목을 장바구니에서 제거한다.
     */
    private void removeItemFromCart() {
        System.out.println("\n제거할 항목의 이름을 입력하세요.");
        String itemName = scanner.next();
        cart.removeItemByName(itemName);
        System.out.println("\n제거되었습니다.");
    }

    /**
     * 주문을 취소한다.
     *
     * 주문 취소 메시지를 출력하고, 장바구니를 비운다.
     */
    private void handelReset() {
        System.out.println("\n주문이 취소되었습니다.");
        cart.clear();
    }

    /**
     * 프로그램을 종료한다.
     *
     * 종료 메시지를 출력하고, 장바구니를 비운다.
     */
    private void terminate() {
        System.out.println("\n프로그램을 종료합니다.");
        cart.clear();
    }

    /**
     * 사용자 입력을 받는다.
     *
     * 주어진 최소값과 최대값 사이의 숫자를 사용자로부터 입력받고,
     * 잘못된 입력이 있을 경우 다시 입력을 받는다.
     *
     * @param minOption 선택 가능한 번호의 최솟값
     * @param maxOption 선택 가능한 번호의 최댓값
     * @return 사용자 입력 값
     */
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
