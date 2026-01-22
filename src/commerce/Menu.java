package commerce;

import java.util.*;

public class Menu {
    private Map<String, Product> menus = new HashMap<>(Map.of(
            "Galaxy S25", new Product("갤럭시S25", 1_200_000, "최신 안드로이드 스마트폰", 10),
            "iPhone 16", new Product("아이폰16", 1_300_000, "Apple의 최신 스마트폰", 10),
            "MacBook Pro", new Product("맥북프로", 2_400_000, "M3 칩셋이 탑재된 노트북", 10),
            "AirPods Pro", new Product("에어팟프로", 350_000, "노이즈 캔슬링 무선 이어폰", 10)
    ));

    public void addMenus(String name ,Product item) {
        menus.put(name, item);
    }

    public void getMenus() {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        List<Product> productList =new ArrayList<>(menus.values());
        for (int i = 0; i < productList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(productList.get(i));

        }System.out.println("0. 종료        | 프로그램 종료");
    }



}

