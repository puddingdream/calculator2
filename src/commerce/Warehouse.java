package commerce;

import java.util.*;


public class Warehouse {
    // 번호로 카테고리 객체를 찾기 위한 관리용 Map (서랍장 역할)
    private Map<Integer, Category> categoryMap = new LinkedHashMap<>();

    public Warehouse() {
        // 창고가 열릴 때(생성자) 모든 카테고리와 상품을 세팅합니다.
        initWarehouse();
    }

    private void initWarehouse() {
        // 1. 전자제품
        Category electro = new Category("전자제품");
        electro.addProduct(new Product("Galaxy S25", "갤럭시S25", 1_200_000, "최신 안드로이드 스마트폰", 10));
        electro.addProduct(new Product("iPhone 16", "아이폰16", 1_300_000, "Apple의 최신 스마트폰", 10));
        electro.addProduct(new Product("MacBook Pro", "맥북프로", 2_400_000, "M3 칩셋 노트북", 10));
        categoryMap.put(1, electro);

        // 2. 의류
        Category clothing = new Category("의류");
        clothing.addProduct(new Product("Hoodie", "오버핏 후드티", 59_000, "편안한 면 소재 후드", 50));
        clothing.addProduct(new Product("Jeans", "스트레이트 데님", 45_000, "사계절용 청바지", 30));
        categoryMap.put(2, clothing);

        // 3. 과자
        Category snacks = new Category("과자");
        snacks.addProduct(new Product("Potato Chips", "포테이토칩", 2_500, "바삭한 감자칩", 200));
        snacks.addProduct(new Product("Chocolate Bar", "초코바", 1_500, "에너지 충전용", 150));
        categoryMap.put(3, snacks);

        // 4. 생활용품
        Category daily = new Category("생활용품");
        daily.addProduct(new Product("Towel", "호텔 수건", 8_000, "도톰한 40수 면 수건", 60));
        daily.addProduct(new Product("Tumbler", "텀블러", 25_000, "보온 성능 우수", 15));
        categoryMap.put(4, daily);

        // 5. 가구
        Category furniture = new Category("가구");
        furniture.addProduct(new Product("Desk", "원목 책상", 150_000, "깔끔한 워크스테이션", 5));
        furniture.addProduct(new Product("Office Chair", "사무용 의자", 120_000, "인체공학 의자", 8));
        categoryMap.put(5, furniture);
    }

    // 카테고리 목록을 번호와 함께 출력
    public void showCategoryList() {
        System.out.println("\n--- [ 카테고리를 선택하세요 ] ---");
        categoryMap.forEach((id, cat) -> {
            System.out.println(id + ". " + cat.getName());
        });
    }

    // 입력받은 번호에 해당하는 Category 객체 반환
    public Category getCategory(int id) {
        return categoryMap.get(id);
    }
}