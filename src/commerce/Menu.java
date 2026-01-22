package commerce;

import java.util.*;
import java.util.function.Supplier;

public class Menu {

    // 번호 - 기능 이름 보관함
    private Map<Integer, String> menuNames = new LinkedHashMap<>();
    // 번호 - 실행할 코드 보관함
    private Map<Integer, MenuAction> menuActions = new HashMap<>();
    private Map<Integer, Supplier<Boolean>> menuConditions = new HashMap<>();

    // 기본적으로는 항상 보이는 메뉴로 등록
    public void registerMenu(int key, String name, MenuAction action) {
        registerMenu(key, name, action, () -> true);
    }

    // 노출 조건이 있는 메뉴 등록 (객체지향의 핵심!)
    public void registerMenu(int key, String name, MenuAction action, Supplier<Boolean> condition) {
        menuNames.put(key, name);
        menuActions.put(key, action);
        menuConditions.put(key, condition);
    }

    // [중요] 저장된 메뉴 목록을 화면에 예쁘게 출력
    public void showMenuSelection() {
        System.out.println("\n--- [ 메뉴를 선택하세요 ] ---");
        menuNames.forEach((key, name) -> {
            // 스스로 저장된 조건을 검사해서 true일 때만 출력! [cite: 2026-01-23]
            if (menuConditions.get(key).get()) {
                System.out.println(key + ". " + name);
            }
        });
        System.out.println("0. 프로그램 종료");
    }
    // 실행 시에도 조건 확인 (보안 강화)
    public boolean canRun(int key) {
        return menuConditions.containsKey(key) && menuConditions.get(key).get();
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
