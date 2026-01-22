package commerce;

public class CommerceSystem {
    Keyboard sc = new Keyboard();
    Menu menu = new Menu();
    private boolean isRuninng = true;

    public void strat() {

//        while (isRuninng)

        newProduct();
        newProduct();

        menu.getMenus();


        int choice = sc.inputInt("메뉴 숫자를 입력하세요 :");
        endChoice(choice);


    }


    public void endChoice(int choice) {
        if (choice == 0) {
            System.out.println(" 프로그램을 종료합니다");
            sc.scClose();
            isRuninng = false;
        }
    }

    public void newProduct() {

        String ename;
        String kname;
        while (true) {
            ename = sc.inputString("추가할 상품의 영어이름을 입력하시오 : ");
            if(sc.isEnglish(ename))break;
            System.out.println("영어와 숫자만 입력가능합니다.");
        }
        while (true) {
            kname = sc.inputString("추가할 상품의 한글이름을 입력하시오 : ");
            if (sc.isKorean(kname))break;
            System.out.println("한글과 숫자만 입력가능합니다");
        }
        int price = sc.inputInt("추가할 상품의 가격을 입력하시오 : ");
        String description = sc.inputString("추가할 상품의 설명을 입력하시오 : ");
        int stock = sc.inputInt("추가할 상품의 재고를 입력하시오 : ");
        menu.addMenus(ename, new Product(kname, price, description, stock));
    }


}










