package step1;

import java.util.Scanner;

public class App {
    static void main() {
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
            } catch (Exception e) {
                throw new RuntimeException("소수나 문자는 입력불가능합니다.", e);
            }
            System.out.println("사칙연산 기호를 입력하세요");
            char operator = sc.next().charAt(0);
            int result = 0;
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> {
                    if (num2 != 0) {
                        result = num1 / num2;
                    }
                    throw new RuntimeException("나눗셈은 0으로 나눌수 없습니다.");
                }
            }
            System.out.println("결과 = " + result);
            System.out.println("그만 계산하려면 'exit' 입력");
            end = sc.next();

        } while (!end.equalsIgnoreCase("exit"));

        sc.close();

    }
}
