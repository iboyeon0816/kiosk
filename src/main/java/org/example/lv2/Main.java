package org.example.lv2;

import org.example.lv2.domain.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 간단한 키오스크 프로그램을 구현한 클래스이다.
 *
 * 사용자에게 메뉴를 출력하고, 메뉴 번호를 입력받아 처리한다.
 * 프로그램의 주요 기능은 다음과 같다:
 * 1. 메뉴판 출력: 햄버거 메뉴와 종료 옵션을 출력한다.
 * 2. 사용자 입력 처리: 입력된 메뉴 번호를 검증하고, 선택된 메뉴를 출력한다.
 * 3. 프로그램 종료: 사용자가 0을 입력한 경우 프로그램을 종료한다.
 */
public class Main {

    private static final String MENU_TITLE = "[ SHAKESHACK MENU ]";
    private static final String EXIT_ITEM = "종료           | 종료";
    private static final String INVALID_INPUT_MESSAGE = "메뉴판에 존재하는 번호를 입력해주세요.";
    private static final String EXIT_MESSAGE = "\n프로그램을 종료합니다.";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        // 메뉴 항목 초기화
        List<MenuItem> menuItems = initializeMenuItems();

        // 종료 입력을 받기 전까지 반복
        while(true) {
            // 메뉴판 이름 출력
            print(MENU_TITLE);

            // 메뉴 출력
            printMenuItems(menuItems);
            printExitItem();

            // 사용자 입력 처리
            int userNum = getUserNum(menuItems.size());
            if (userNum == 0) {
                // 프로그램 종료
                print(EXIT_MESSAGE);
                break;
            }
            else {
                // 선택한 메뉴 출력
                print("\n선택한 메뉴: " + menuItems.get(userNum - 1) + "\n");
            }
        }
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

    /**
     * 주어진 문자열을 출력한다.
     *
     * @param str 출력할 문자열
     */
    private static void print(String str) {
        System.out.println(str);
    }

    /**
     * 주어진 메뉴 항목을 순서대로 출력한다.
     *
     * 각 메뉴 항목은 번호와 함께 출력되며, 번호는 1부터 시작한다.
     *
     * @param menuItems 출력할 메뉴 항목을 포함한 리스트
     */
    private static void printMenuItems(List<MenuItem> menuItems) {
        int size = menuItems.size();
        for (int i = 1; i <= size; i++) {
            System.out.println(i + ". " + menuItems.get(i - 1));
        }
    }

    /**
     * 프로그램 종료 항목을 출력한다.
     *
     * 종료 항목은 항상 0번으로 표시된다.
     */
    private static void printExitItem() {
        System.out.println("0. " + EXIT_ITEM);
    }

    /**
     * 사용자로부터 유효한 메뉴 번호를 입력받아 반환한다.
     *
     * 유효한 입력은 0부터 `menuCount`까지의 정수이다.
     * 잘못된 입력이 들어오면 경고 메시지를 출력하고, 다시 입력받는다.
     *
     * @param menuCount 메뉴 항목의 수
     * @return 입력받은 유효한 메뉴 번호
     */
    private static int getUserNum(int menuCount) {
        while(true) {
            try {
                int num = SCANNER.nextInt();
                if (num < 0 || num > menuCount)
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
}