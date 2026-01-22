package commerce;

public class CommerceSystem {
    Keyboard sc = new Keyboard();
    Menu menu = new Menu();
    private boolean isRuninng = true;

    public void strat() {

        while (isRuninng)

            menu.getMenus();

        int choice = sc.inputNum("메뉴 숫자를 입력하세요 :");
        endChoice(choice);






    }







    public void endChoice(int choice) {
        if (choice == 0) {
            System.out.println(" 프로그램을 종료합니다");
            sc.scClose();
            isRuninng = false;
        }

    }

}










