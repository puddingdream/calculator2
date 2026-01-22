package commerce;

public class CommerceSystem {
    Keyboard sc = new Keyboard();
    Menu menu = new Menu();
    private boolean isRunning = true;

    public void strat() {
        menu.registerMenu(1, "상품 조회", () -> menu.getProductList());
        menu.registerMenu(2, "상품 등록", () -> newProduct());

        while (isRunning) {

            menu.showMenuSelection();
            int choice = sc.inputInt("메뉴 숫자를 입력하세요 :");

            if (choice == 0) {
                endProgram(); // 0번이면 종료
            } else {
                menu.runAction(choice);

            }
        }
    }

    public void endProgram() {
        System.out.println(" 프로그램을 종료합니다. 이용해주셔서 감사합니다.");
        sc.scClose();
        isRunning = false;
    }

    public void newProduct() {
        System.out.println("\n[ 신규 상품 등록을 시작합니다 ]");
        String ename = getValidString("영어이름", true);
        String kname = getValidString("한글이름", false);
        int price = sc.inputInt("가격: ");
        String desc = sc.inputString("설명: ");
        int stock = sc.inputInt("재고: ");
        menu.addProductMenus(ename, new Product(kname, price, desc, stock));
        menu.getProductList();
    }

    private String getValidString(String label, boolean isEnglish) {
        while (true) {
            String input = sc.inputString(label + "을 입력하시오 : ");
            if (isEnglish && sc.isEnglish(input)) return input;
            if (!isEnglish && sc.isKorean(input)) return input;
            System.out.println("⚠️ 형식에 맞지 않습니다. 다시 입력하세요.");
        }
    }


}










