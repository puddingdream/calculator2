package step3;

import java.util.Scanner;

public class App {
    static void main() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("첫 번째 숫자를 입력하세요");
            int num1 = sc.nextInt();
            System.out.println("두 번째 숫자를 입력하세요");
            int num2 = sc.nextInt();
        } catch (Exception e) {
            throw new RuntimeException ("소수나 문자는 입력불가능합니다.", e);
        }


    }
}
