package step2;

import java.util.Scanner;

public class App {
    static void main() {
        Calculator cal = new Calculator();
        Scanner sc = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;
        String end = "";

        do {
            try {
                System.out.println("첫 번째 숫자를 입력하세요");
                num1 = sc.nextInt();
                System.out.println("두 번째 숫자를 입력하세요");
                num2 = sc.nextInt();
            } catch (RuntimeException e) {
                System.out.println("소수나 문자는 입력불가능 처음부터다시");
                sc.next();
                continue;
            }
            System.out.println("사칙연산 기호를 입력하세요");
            char oper = sc.next().charAt(0);

            int result = cal.calculator(num1,num2,oper);
            cal.addResults(result);




            System.out.println("프로그램 끝내기 'exit' 입력");
            end = sc.next();

        } while (!end.equalsIgnoreCase("exit"));
        sc.close();


    }
}
