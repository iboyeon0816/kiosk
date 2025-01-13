package org.example.lv5.app;

import org.example.lv5.domain.Menu;
import org.example.lv5.domain.MenuItem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * 간단한 키오스크 프로그램을 구현한 클래스이다.
 *
 * 메뉴를 출력하고, 사용자로부터 입력을 받아서 적절한 메뉴 항목을 선택할 수 있게 한다.
 */
public class Kiosk {
    private static final String MAIN = "MAIN";
    private static final String EXIT_LABEL = "종료";
    private static final String BACK_LABEL = "뒤로가기";
    private static final String INVALID_INPUT_MESSAGE = "메뉴판에 존재하는 번호를 입력해주세요.";
    private static final String EXIT_MESSAGE = "\n프로그램을 종료합니다.";
    private static final Scanner SCANNER = new Scanner(System.in);

    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    /**
     * 키오스크 동작을 시작한다.
     *
     * 사용자에게 메뉴를 출력하고, 메뉴 번호를 입력받아 처리한다.
     * 주요 기능은 다음과 같다:
     * 1. 사용자는 카테고리를 선택한 후, 해당 카테고리 내의 메뉴 항목을 선택한다.
     * 2. 종료를 선택하면 프로그램이 종료된다.
     * 3. 돌아가기를 선택하면 상위 카테고리로 돌아간다.
     * 4. 메뉴 항목을 선택하면, 항목의 내용을 출력한 후 처음으로 돌아간다.
     */
    public void start() {
        while(true) {
            // 상위 카테고리 출력
            showCategories();

            // 사용자 입력 처리
            int userNum = getUserNum(menus.size());
            if (userNum == 0) {
                // 프로그램 종료
                print(EXIT_MESSAGE);
                break;
            }

            // 선택한 메뉴의 항목 출력
            Menu selectedMenu = menus.get(userNum - 1);
            showMenuItems(selectedMenu);

            // 사용자 입력 처리
            List<MenuItem> selectedMenuItems = selectedMenu.getMenuItems();
            userNum = getUserNum(selectedMenuItems.size());
            if (userNum == 0) {
                // 처음으로 돌아가기
                System.out.println();
                continue;
            }

            // 선택한 메뉴 출력
            print("\n선택한 메뉴: " + selectedMenuItems.get(userNum - 1) + "\n");
        }
    }

    /**
     * 상위 카테고리 메뉴를 출력한다.
     *
     * 1. "MAIN"이라는 이름으로 메뉴판 제목을 출력한다.
     * 2. 카테고리 목록을 출력한다.
     * 3. 종료 옵션(0번)을 출력한다.
     */
    private void showCategories() {
        printMenuName(MAIN);
        printCategories();
        printZeroOption(EXIT_LABEL);
    }

    /**
     * 선택된 메뉴의 항목을 출력한다.
     *
     * 1. 메뉴의 이름(카테고리)을 출력한다.
     * 2. 해당 메뉴에 포함된 메뉴 항목들을 출력한다.
     * 3. 뒤로가기 옵션(0번)을 출력한다.
     */
    private void showMenuItems(Menu menu) {
        System.out.println();
        printMenuName(menu.getCategory());
        menu.printMenuItems();
        printZeroOption(BACK_LABEL);
    }

    /**
     * 주어진 메뉴명을 출력한다.
     *
     * @param menuName 출력할 메뉴명
     */
    private void printMenuName(String menuName) {
        System.out.println("[ " + menuName.toUpperCase() + " MENU ]");
    }

    /**
     * 카테고리명을 순서대로 출력한다.
     *
     * 각 카테고리는 번호와 함께 출력되며, 번호는 1부터 시작한다.
     */
    private void printCategories() {
        int size = menus.size();
        for (int i = 1; i <= size; i++) {
            System.out.println(i + ". " + menus.get(i - 1).getCategory());
        }
    }

    /**
     * 주어진 레이블과 함께 0번 옵션을 출력한다.
     *
     * @param label 출력할 레이블
     */
    private void printZeroOption(String label) {
        System.out.println("0. " + label);
    }

    /**
     * 사용자로부터 유효한 수를 입력받아 반환한다.
     *
     * 유효한 입력은 0부터 `optionCount`까지의 정수이다.
     * 잘못된 입력이 들어오면 경고 메시지를 출력하고, 다시 입력받는다.
     *
     * @param optionCount 유효한 옵션 수
     * @return 입력받은 수
     */
    private int getUserNum(int optionCount) {
        while(true) {
            try {
                int num = SCANNER.nextInt();
                if (num < 0 || num > optionCount)
                    throw new IllegalArgumentException();
                return num;
            } catch (InputMismatchException e) {
                print(INVALID_INPUT_MESSAGE);
                SCANNER.next();
            } catch (IllegalArgumentException e) {
                print(INVALID_INPUT_MESSAGE);
            }
        }
    }

    /**
     * 주어진 문자열을 출력한다.
     *
     * @param str 출력할 문자열
     */
    private void print(String str) {
        System.out.println(str);
    }
}
