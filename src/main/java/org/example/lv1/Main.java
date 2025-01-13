package org.example.lv1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 간단한 키오스크 프로그램을 구현한 클래스이다.
 * 사용자에게 메뉴를 출력하고, 메뉴 번호를 입력받아 처리한다.
 * 프로그램의 주요 기능은 다음과 같다:
 *
 * 1. 메뉴판 출력: 햄버거 메뉴와 종료 옵션을 출력한다.
 * 2. 사용자 입력 처리: 입력된 메뉴 번호를 검증하고, 선택된 메뉴를 출력한다.
 * 3. 프로그램 종료: 사용자가 0을 입력한 경우 프로그램을 종료한다.
 */
public class Main {

    private static final String MENU_TITLE = "[ SHAKESHACK MENU ]";
    private static final String[] MENU_ITEMS = {
            "ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거",
            "SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
            "Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",
            "Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거"
    };
    private static final String EXIT_ITEM = "종료           | 종료";
    private static final String INVALID_INPUT_MESSAGE = "메뉴판에 존재하는 번호를 입력해주세요.";
    private static final String EXIT_MESSAGE = "\n프로그램을 종료합니다.";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        // 종료 입력을 받기 전까지 반복
        while(true) {
            // 메뉴판 이름 출력
            print(MENU_TITLE);

            // 메뉴 출력
            printMenuItems();
            printExitItem();

            // 사용자 입력 처리
            int userNum = getUserNum();
            if (userNum == 0) {
                // 프로그램 종료
                print(EXIT_MESSAGE);
                break;
            }
            else {
                // 선택한 메뉴 출력
                print("\n선택한 메뉴: " + MENU_ITEMS[userNum - 1] + "\n");
            }
        }
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
     * 메뉴 항목을 순서대로 출력한다.
     * 메뉴 항목은 1번부터 시작하며, 각 항목의 번호와 내용을 포함한다.
     */
    private static void printMenuItems() {
        for (int i = 1; i <= MENU_ITEMS.length; i++) {
            System.out.println(i + ". " + MENU_ITEMS[i - 1]);
        }
    }

    /**
     * 프로그램 종료 항목을 출력한다.
     * 종료 항목은 항상 0번으로 표시된다.
     */
    private static void printExitItem() {
        System.out.println("0. " + EXIT_ITEM);
    }

    /**
     * 사용자로부터 유효한 메뉴 번호를 입력받아 반환한다.
     * 유효한 입력은 0부터 메뉴 항목의 개수까지의 정수이다.
     * 잘못된 입력이 들어오면 경고 메시지를 출력하고, 다시 입력받는다.
     *
     * @return 입력받은 유효한 메뉴 번호
     */
    private static int getUserNum() {
        while(true) {
            try {
                int num = SCANNER.nextInt();
                if (num < 0 || num > MENU_ITEMS.length)
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