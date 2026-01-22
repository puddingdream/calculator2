package commerce;

import java.util.*;

public class Menu {
    private Map<String, Product> productMenus = new HashMap<>(Map.of(
            "Galaxy S25", new Product("갤럭시S25", 1_200_000, "최신 안드로이드 스마트폰", 10),
            "iPhone 16", new Product("아이폰16", 1_300_000, "Apple의 최신 스마트폰", 10),
            "MacBook Pro", new Product("맥북프로", 2_400_000, "M3 칩셋이 탑재된 노트북", 10),
            "AirPods Pro", new Product("에어팟프로", 350_000, "노이즈 캔슬링 무선 이어폰", 10)
    ));
    // 번호 - 기능 이름 보관함
    private Map<Integer, String> menuNames = new LinkedHashMap<>();
    // 번호 - 실행할 코드 보관함
    private Map<Integer, MenuAction> menuActions = new HashMap<>();
    // 메뉴를 등록하는 메서드
    public void registerMenu(int key, String name, MenuAction action) {
        menuNames.put(key, name);
        menuActions.put(key, action);
    }

    // 상품 추가
    public void addProductMenus(String name , Product item) {
        productMenus.put(name, item);
    }

    //상품리스트보기
    public void getProductList() {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        List<Product> productList =new ArrayList<>(productMenus.values());
        for (int i = 0; i < productList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(productList.get(i));
        }
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