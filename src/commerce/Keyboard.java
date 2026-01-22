package commerce;

import java.util.Scanner;

public class Keyboard {
    private Scanner sc = new Scanner(System.in);
    // 숫자받기
    public double inputNum (String message){
        System.out.println(message);
        while (!sc.hasNextDouble()){
            System.out.println("숫자만 입력가능합니다.");
            sc.next();
        } return sc.nextDouble();
    }
    // 문자받기
    public String inputString(String message) {
        System.out.println(message);
        return sc.next();
    }
    // 스캐너닫기
    public void scClose(){
        sc.close();
    }

}
