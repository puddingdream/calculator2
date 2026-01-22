package commerce;

public class CommerceSystem {
    Keyboard sc = new Keyboard();
    Menu menu = new Menu();
    Warehouse warehouse = new Warehouse(); // 창고 추가
    private boolean isRunning = true;

    public void start() {
        // 메뉴 등록
        menu.registerMenu(1, "상품 조회", () -> viewProducts());
        menu.registerMenu(2, "상품 등록", () -> newProduct());

        while (isRunning) {
            menu.showMenuSelection();
            int choice = sc.inputInt("메뉴 숫자를 입력하세요 :");
            if (choice == 0) endProgram();
            else menu.runAction(choice);
        }
    }

    // [수정] 어떤 카테고리를 볼지 먼저 물어보고 리스트를 출력합니다.
    public void viewProducts() {
        warehouse.showCategoryList();
        int catId = sc.inputInt("조회할 카테고리 번호: ");
        Category target = warehouse.getCategory(catId);

        if (target != null) {
            System.out.println("\n[" + target.getName() + " 상품 목록]");
            for (Product p : target.getProducts()) {
                System.out.println(p);
            }
        } else {
            System.out.println(" 존재하지 않는 카테고리입니다.");
        }
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
            System.out.println( target.getName() + "에 상품이 등록되었습니다.");
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
}









