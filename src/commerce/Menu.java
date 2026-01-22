package commerce;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Product> menus = new ArrayList<>(List.of(
            new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10),
            new Product("iPhone 16 ", 1300000, "Apple의 최신 스마트폰", 10),
            new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10),
            new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10)
    ));

    public void addMenus(Product item) {
        menus.add(item);
    }

    public void getMenus() {
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(menus.get(i));

        }System.out.println("0. 종료        | 프로그램 종료");
    }
}

