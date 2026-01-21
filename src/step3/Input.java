package step3;

import java.util.Scanner;

public class Input {
    private Scanner sc = new Scanner(System.in);

    public double inputNum (String message){
        System.out.println(message);
        while (!sc.hasNextDouble()){
            System.out.println("숫자만 입력가능합니다.");
            sc.next();
        } return sc.nextDouble();
    }

    public char inputOper(String message){
        System.out.println(message);
        return sc.next().charAt(0);
    }

    public String inputString(String message) {
        System.out.println(message);
        return sc.next();
    }

    public void scClose(){
        sc.close();
    }

}
