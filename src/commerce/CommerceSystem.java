package commerce;

import java.util.List;

public class CommerceSystem {
    Keyboard sc = new Keyboard();
    Menu menu = new Menu();
    Warehouse warehouse = new Warehouse(); // 창고 추가
    Cart cart = new Cart();
    private boolean isRunning = true;

    public void start() {
        // 메뉴 등록
        menu.registerMenu(1, "상품 조회", () -> viewProducts());
        menu.registerMenu(2, "상품 등록", () -> newProduct());
        menu.registerMenu(3, "상품명 조회", () -> productSearch());
        menu.registerMenu(4, "장바구니 보기", () -> showCart(), () -> !cart.getCartMap().isEmpty());
        menu.registerMenu(5, "장바구니 삭제", () -> selectRemove(), () -> !cart.getCartMap().isEmpty());

        while (isRunning) {
            menu.showMenuSelection(); // 메뉴보여주고
            int choice = sc.inputInt("메뉴 숫자를 입력하세요 :"); //메뉴에서 숫자고르고

            if (choice == 0) endProgram();  // 0이면 프로그램종료
            else if (menu.canRun(choice)) { // true 받으면 실행해라
                menu.runAction(choice);     // 메뉴에 저장된 코드 진행시켜
            } else {
                System.out.println("선택할수 없는 메뉴입니다.");
            }

        }
    }

    // [수정] 어떤 카테고리를 볼지 먼저 물어보고 리스트를 출력합니다.
    public void viewProducts() {
        warehouse.showCategoryList(); // 창고에있는 카테고리 리스트 출력
        int catId = sc.inputInt("조회할 카테고리 번호: ");
        Category target = warehouse.getCategory(catId); // 카테고리번호 인덱스로 입력해서 해당카테고리 가져옴

        if (target == null) { // 카테고리가 없다면
            System.out.println(" 존재하지 않는 카테고리입니다.");
            return;
        }
        System.out.println("\n[" + target.getName() + " 상품 목록]");  // 카테고리의 이름출력
        List<Product> list = target.getProducts(); // 해당 카테고리의 물품 리스트를 따로 만들기.

        selectAndAddCart(list);
    }


    public void newProduct() {  // 상품추가하는기능
        System.out.println("\n[ 신규 상품 등록 ]");
        warehouse.showCategoryList();  // 상품을 넣을 카테고리고르기
        int catId = sc.inputInt("등록할 카테고리 번호: ");
        Category target = warehouse.getCategory(catId);

        if (target != null) {    // 카테고리가 있다면
            String ename = getValidString("영어이름", true);
            String kname = getValidString("한글이름", false);
            int price = sc.inputInt("가격: ");
            String desc = sc.inputString("설명: ");
            int stock = sc.inputInt("재고: ");

            target.addProduct(new Product(ename, kname, price, desc, stock)); // 입력해서 넣어주기
            System.out.println(target.getName() + "에 상품이 등록되었습니다.");
        }
    }

    // 한글 또는 영어 검증 로직
    private String getValidString(String label, boolean isEnglish) {
        while (true) {
            String input = sc.inputString(label + "을 입력하시오 : ");
            if (isEnglish && sc.isEnglish(input)) return input;
            if (!isEnglish && sc.isKorean(input)) return input;
            System.out.println("형식에 맞지 않습니다.");
        }
    }

    // 프로그램종료
    public void endProgram() {
        System.out.println("프로그램을 종료합니다.");
        sc.scClose();
        isRunning = false; // 루프끝
    }

    // 상품 이름 키워드 검색해서 가져오기
    public void productSearch() {
        String name = sc.inputString("검색할 물건이름 : ");
        List<Product> searchList = warehouse.getAllProduct().stream() //필터링할 모든 Product를 리스트로 만들기
                .filter(item -> item.korName.contains(name) || item.engName.contains(name)) // 적은문구가영어든 한글이든 포함만되어있으면 찾아옴
                .toList(); // 리스트로
        selectAndAddCart(searchList);
    }

    // 리스트 선택하고 장바구니에 넣는거까지 연결하는 기능
    public void selectAndAddCart(List<Product> itemList) {
        if (itemList == null || itemList.isEmpty()) {  // 찾은상품이없다면
            System.out.println("표시할 상품이 없습니다");
            return;
        }
        System.out.println("물품 목록");
        for (int i = 0; i < itemList.size(); i++) { // 물건 선택 인덱스를 해줘야하기때문에 번호매겨줌
            // i + 1 을 해서 1번부터 번호를 매겨줍니다.
            System.out.println((i + 1) + ". " + itemList.get(i));  // 번호 . 물품리스트 쭉 출력
        }
        int choiceItem = sc.inputInt("선택할 상품 번호를 입력하세요 (0. 돌아가기) : ");
        if (choiceItem > 0 && choiceItem <= itemList.size()) { //리스트에 출력된 물품번호를 고르기
            Product getItem = itemList.get(--choiceItem); // 리스트중 골랏다면 사용자가 보이는번호와 인덱스번호가 다르므로 -1빼줌
            System.out.println("선택 : " + getItem);  // 선택햇다고 출력해주고
            addtoCart(getItem);                      // 카트에 담는 로직 실행
        } else if (choiceItem != 0) {
            System.out.println("존재하지 않는 상품번호입니다.");  // if 에 안걸리고 0이아니라면 출력
        }
    }


    // 카트에 담아서 재고 연동까지 하는 기능 사실 위에있는 메서드랑 그대로 연결해서 써도되지만 길면 보기힘드니까 나눔
    public void addtoCart(Product getItem) {
        if (getItem == null) return;

        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인         2. 취소");
        int num = sc.inputInt("입력: ");

        if (num == 1) {
            int quantity = sc.inputInt("주문할 수량을 입력하세요 : ");
            if (quantity > 0 && quantity <= getItem.stock) { // 장바구니 넣을수있는수량과 재고랑 비교하기
                getItem.stock -= quantity;                   // 넣기전에 재고부터줄여주고
                cart.addProduct(getItem, quantity);          // 장바구니에 넣기
                System.out.println(getItem.korName + "이(가) " + quantity + "개 담겼습니다.");
            } else {
                System.out.println("주문 수량은 재고( " + getItem.stock + "개) 이하여야 합니다.");
            }
        }
    }

    // 카트에 뭐들었는지 보여주고 선택해서 주문하는기능
    // 장바구니가 비어있으면 메뉴선택이안되서 예외설정 안해도됨
    public void showCart() {
        System.out.println("장바구니 내역");
        cart.getCartMap().forEach((product, count) -> {
            // 장바구니해쉬맵에 있는     (키 - Product , 값 - 주문수량)
            // 가져와서 무슨물건인지 각 물건당 총합계가 얼마인지 출력
            System.out.println(product.korName + " | " + count + "개 | 합계: " + (product.price * count) + "원");
        });
        int total = cart.getCartMap().entrySet().stream() // 총금액 구하기위해 entrySet으로 키 값 모두 가져옴
                .mapToInt(i -> i.getKey().price * i.getValue()) // 키에서 가격빼오고 값으로 수량가져와서 곱해줌
                .sum(); // 각물건마다 총얼마인지 확인됬으면 다 더해줌
        System.out.println("총 합계 " + total);
        System.out.println("주문하시겠습니까?");
        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
        int choice = sc.inputInt("입력 :");
        if (choice == 1) {
            System.out.println("주문이 완료되었습니다! 총 금액 : " + total + "원");
            cart.getCartMap().clear(); // 카트를 직접 비우므로 카트비울때 재고다시채워넣던건 해당안함
        }
    }

    public void selectRemove() { // 장바구니 삭제 메뉴
        System.out.println("장바구니 삭제");
        System.out.println("1. 전체삭제 2. 선택삭제");
        int num = sc.inputInt("입력");
        if (num == 1) cartCancel();
        if (num == 2) cartRemove();

    }

    public void cartRemove() {  // 선택삭제기능
        String name = sc.inputString("삭제할 물품의 상품명을 입력하세요 일부입력가능");
        List<Product> quantity = cart.getCartMap().keySet().stream()
                .filter(n -> n.korName.contains(name) || n.engName.contains(name)) //위에썻던로직
                .toList();
        if (quantity.isEmpty()) {  // 잘못입력처리
            System.out.println("장바구니에 해당상품이 없습니다.");
            return;
        }
        for (Product product : quantity) { // 삭제할것들 삭제하기전에 재고채워줘야하니까 조회한 목록들 찾아서 가져오기 quantity가 삭제예정애들임
            int count = cart.getCartMap().get(product);
            product.stock += count;       // 장바구니 담을때 미리 줄였던 재고 올려주기
            cart.getCartMap().remove(product); // 진짜 삭제
            System.out.println("해당물품 삭제완료");
        }
    }

    public void cartCancel() { // 장바구니비우기
        cart.getCartMap().forEach((product, count) -> {
            product.stock += count;
        });
        cart.getCartMap().clear();
        System.out.println("장바구니를 비웠습니다.");
    }


}










