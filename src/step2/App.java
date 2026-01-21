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
                System.out.println("소수나 문자는 입력불가능 처음부터다시"); // 실수로 숫자입력안했을때 다시하기
                sc.next();
                continue;
            }

            int result = 0;
            try { // 연산시 발생하는 예외만들기
                System.out.println("사칙연산 기호를 입력하세요");
                char oper = sc.next().charAt(0);
                result = cal.calculator(num1, num2, oper);
            } catch (RuntimeException e) {
                System.out.println("오류발생! : " +e.getMessage() + " 처음부터 다시입력");
                continue;
            }
            cal.addResults(result);  // 결과값 리스트에넣기

            System.out.println("결과 : " + result); // 계산기돌린값 출력
            System.out.println("저장된 결과 " + cal.getResults()); //

            System.out.println("저장된결과중 첫번째를 삭제 (y)");
            String remove = sc.next();
            if (remove.equalsIgnoreCase("y")) { // 배열중 첫번째 삭제
                cal.removeResults();
                System.out.println("저장된 결과 " + cal.getResults());
            }
            System.out.println("프로그램 끝내기 'exit' 입력");
            end = sc.next();

        } while (!end.equalsIgnoreCase("exit"));
        sc.close();

    }
}
