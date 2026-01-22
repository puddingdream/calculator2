package commerce;

public class CommerceSystem {
    Keyboard sc = new Keyboard();
    Menu menu = new Menu();

    public void strat(){





        menu.getMenus();
        double choice = sc.inputNum("메뉴 숫자를 입력하세요 :") ;









        sc.scClose();
    }


}
