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
            menu.showMenuSelection();
            int choice = sc.inputInt("메뉴 숫자를 입력하세요 :");

            if (choice == 0) endProgram();
            else if(menu.canRun(choice)){
                menu.runAction(choice);
                }else {
                System.out.println("선택할수 없는 메뉴입니다.");
            }

        }
    }

    // [수정] 어떤 카테고리를 볼지 먼저 물어보고 리스트를 출력합니다.
    public void viewProducts() {
        warehouse.showCategoryList();
        int catId = sc.inputInt("조회할 카테고리 번호: ");
        Category target = warehouse.getCategory(catId);

        if (target == null) {
            System.out.println(" 존재하지 않는 카테고리입니다.");
            return;
        }

        System.out.println("\n[" + target.getName() + " 상품 목록]");
        List<Product> list = target.getProducts(); // 리스트를 따로 뽑아옵니다.

        if (list.isEmpty()) {
            System.out.println("카테고리에 등록된 상품이 없습니다.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            // i + 1 을 해서 1번부터 번호를 매겨줍니다.
            System.out.println((i + 1) + ". " + list.get(i));
        }
        int choiceItem = sc.inputInt("선택할 상품 번호를 입력하세요 (0. 돌아가기) : ");
        if (choiceItem >= 0 && choiceItem <= list.size()) {
            if (choiceItem == 0) return;
            Product getItem = list.get(--choiceItem);
            System.out.println("선택 : " + getItem);
            addtoCart(getItem);
        } else System.out.println("존재하지 않는 상품번호입니다.");
    }


    public void newProduct() {
        System.out.println("\n[ 신규 상품 등록 ]");
        warehouse.showCategoryList();
        int catId = sc.inputInt("등록할 카테고리 번호: ");
        Category target = warehouse.getCategory(catId);

        if (target != null) {
            String ename = getValidString("영어이름", true);
            String kname = getValidString("한글이름", false);
            int price = sc.inputInt("가격: ");
            String desc = sc.inputString("설명: ");
            int stock = sc.inputInt("재고: ");

            target.addProduct(new Product(ename, kname, price, desc, stock));
            System.out.println(target.getName() + "에 상품이 등록되었습니다.");
        }
    }

    private String getValidString(String label, boolean isEnglish) {
        while (true) {
            String input = sc.inputString(label + "을 입력하시오 : ");
            if (isEnglish && sc.isEnglish(input)) return input;
            if (!isEnglish && sc.isKorean(input)) return input;
            System.out.println("형식에 맞지 않습니다.");
        }
    }

    public void endProgram() {
        System.out.println("프로그램을 종료합니다.");
        sc.scClose();
        isRunning = false;
    }

    public void productSearch() {
        String name = sc.inputString("검색할 물건이름 : ");
        List<Product> searchList = warehouse.getAllProduct().stream()
                .filter(item -> item.korName.contains(name) || item.engName.contains(name))
                .toList();
        if (searchList.isEmpty()) {
            System.out.println("찾는 상품이 없습니다.");
            return;
        }
        System.out.println("검색된 물품 목록");
        for (int i = 0; i < searchList.size(); i++) {
            // i + 1 을 해서 1번부터 번호를 매겨줍니다.
            System.out.println((i + 1) + ". " + searchList.get(i));
        }
        int choiceItem = sc.inputInt("선택할 상품 번호를 입력하세요 (0. 돌아가기) : ");
        if (choiceItem >= 0 && choiceItem <= searchList.size()) {
            if (choiceItem == 0) return;
            Product getItem = searchList.get(--choiceItem);
            System.out.println("선택 : " + getItem);
            addtoCart(getItem);

        }
    }

    public void addtoCart(Product getItem) {
        if (getItem == null) return;

        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인         2. 취소");
        int num = sc.inputInt("입력: ");

        if (num == 1) {
            int quantity = sc.inputInt("주문할 수량을 입력하세요 : ");
            if (quantity > 0 && quantity <= getItem.stock) {
                getItem.stock -= quantity;
                cart.addProduct(getItem, quantity);
                System.out.println(getItem.korName + "이(가) " + quantity + "개 담겼습니다.");
            } else {
                System.out.println("주문 수량은 재고( " + getItem.stock + "개) 이하여야 합니다.");
            }
        }
    }

    public void showCart() {
        System.out.println("장바구니 내역");
        if (cart.getCartMap().isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
            return;
        }
        cart.getCartMap().forEach((product, count) -> {
            System.out.println(product.korName + " | " + count + "개 | 합계: " + (product.price * count) + "원");
        });
        int total = cart.getCartMap().entrySet().stream()
                .mapToInt(i -> i.getKey().price * i.getValue())
                .sum();
        System.out.println("총 합계 " + total);
        System.out.println("주문하시겠습니까?");
        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
        int choice = sc.inputInt("입력 :");
        if (choice == 1){
            cart.getCartMap().forEach((product, orderCount)->{
                product.stock -= orderCount;
            });
            System.out.println("주문이 완료되었습니다! 총 금액 : " + total + "원");

            cart.getCartMap().clear();
        }
    }

    public void selectRemove() {
        System.out.println("장바구니 삭제");
        System.out.println("1. 전체삭제 2. 일부삭제");
        int num = sc.inputInt("입력");
        if (num == 1) cartCancel();
        if (num == 2) cartRemove();

    }


    public void cartRemove() {
        String name = sc.inputString("삭제할 물품의 상품명을 입력하세요 일부입력가능");
        List<Product> quantity = cart.getCartMap().keySet().stream()
                .filter(n -> n.korName.contains(name) || n.engName.contains(name))
                .toList();
        if(quantity.isEmpty()){
            System.out.println("장바구니에 해당상품이 없습니다.");
            return;
        }
        for (Product product : quantity) {
            int count = cart.getCartMap().get(product);
            product.stock += count;
            cart.getCartMap().remove(product);
            System.out.println("해당물품 삭제완료");
        }
    }

    public void cartCancel() {
        cart.getCartMap().forEach((product,count) -> {
            product.stock += count;
        });
        cart.getCartMap().clear();
        System.out.println("장바구니를 비웠습니다.");
    }





}










