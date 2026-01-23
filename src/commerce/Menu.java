package commerce;

import java.util.*;
import java.util.function.Supplier;

public class Menu {

    // 번호 - 기능 이름 보관함
    private Map<Integer, String> menuNames = new LinkedHashMap<>();
    // 번호 - 실행할 코드 보관함
    private Map<Integer, MenuAction> menuActions = new HashMap<>();
    // 번호 - 조건보관함
    private Map<Integer, Supplier<Boolean>> menuConditions = new HashMap<>();
                       // 조건을 담고 호출되면  <T> 가져옴
    //menuConditions.get(4)를 통해 저장된 감시원(Supplier)을 꺼냅니다.
    //감시원.get()을 호출합니다.
    //그 순간! 감시원이 장바구니로 달려가서 **"지금 비었나?"**를 확인하고 true나 false를 들고 옵니다.
    //그 결과가 true일 때만 System.out.println이 실행됩니다.

    // 기본적으로는 항상 보이는 메뉴로 등록
    public void registerMenu(int key, String name, MenuAction action) {
        registerMenu(key, name, action, () -> true);
    }
    // 위랑 아래랑 두가지 버전의 registerMenu 가있는데 파라메터에따라 맞춰서 아래에 있는 로직을 진행한다
    // 조건없어서 안적는 애는 위에있는걸로 로직이 진행
    // 조건있어서 적는 애는 아래거가 적용이되어 조건이 Supplier에 들어가서 나중에 showMenuSelection 여기서 호출될때
    // (menuConditions.get(key).get()) 로직을통해 true인지 false인지 확인한다
    // 하지만 if (menuConditions.get(key).get()) 를쓸때 위에있는걸 쓴애들은  menuConditions 요게없다
    // 어떻게 실행되는건가하면 위에있는놈은 그대로 저장되는게아니고 아래있는놈을 호출해서 () -> true 이걸끼워서 다시 저장하라고 하청보낸다
    // 메서드 오버로딩되서 아래있는놈이 적용이된다 정말쉽지않다

    // 노출 조건이 있는 메뉴 등록 (객체지향의 핵심!)
    public void registerMenu(int key, String name, MenuAction action, Supplier<Boolean> condition) {
        menuNames.put(key, name); // 메뉴이름
        menuActions.put(key, action);  // 메뉴가 하는일담는거
        menuConditions.put(key, condition);  // 출력조건 담고있음
    }

    // [중요] 저장된 메뉴 목록을 화면에 예쁘게 출력
    public void showMenuSelection() {
        System.out.println("\n--- [ 메뉴를 선택하세요 ] ---");
        menuNames.forEach((key, name) -> {  // registerMenu를 통해 넣은 메뉴들 가져와서
            // 스스로 저장된 조건을 검사해서 true일 때만 출력!
            if (menuConditions.get(key).get()) {   // 위에설명함
                System.out.println(key + ". " + name);   // 번호랑 이름만 출력해주기
            }
        });
        System.out.println("0. 프로그램 종료");
    }
    // 실행 시에도 조건 확인 (보안 강화)
    public boolean canRun(int key) {
        return menuConditions.containsKey(key) && menuConditions.get(key).get();
        // Map에 Key가 있다면 true 없다면 false           위에서썻던로직
        // 두개다 true여야 실행하게끔 만듬
    }

    // 사용자가 번호를 눌렀을 때 해당 기능 실행
    public void runAction(int choice) {
        MenuAction action = menuActions.get(choice); // 나온메뉴중에 번호로 인덱스 해서
        if (action != null) {
            action.execute();                        // 진행시켜
        } else if (choice != 0) {
            System.out.println("⚠️ 잘못된 번호입니다.");
        }
    }

}
