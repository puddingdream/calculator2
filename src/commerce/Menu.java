package commerce;

import java.util.*;

public class Menu {

    // 번호 - 기능 이름 보관함
    private Map<Integer, String> menuNames = new LinkedHashMap<>();
    // 번호 - 실행할 코드 보관함
    private Map<Integer, MenuAction> menuActions = new HashMap<>();

    // 메뉴를 등록하는 메서드
    public void registerMenu(int key, String name, MenuAction action) {
        menuNames.put(key, name);
        menuActions.put(key, action);
    }

    // [중요] 저장된 메뉴 목록을 화면에 예쁘게 출력
    public void showMenuSelection() {
        System.out.println("\n--- [ 실행할 메뉴를 선택하세요 ] ---");
        for (int key : menuNames.keySet()) {
            System.out.println(key + ". " + menuNames.get(key));
        }
        System.out.println("0. 프로그램 종료");
    }

    // 사용자가 번호를 눌렀을 때 해당 기능 실행
    public void runAction(int choice) {
        MenuAction action = menuActions.get(choice);
        if (action != null) {
            action.execute();
        } else if (choice != 0) {
            System.out.println("⚠️ 잘못된 번호입니다.");
        }
    }

}