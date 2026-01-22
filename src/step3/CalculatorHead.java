package step3;

public class CalculatorHead {
    Input input = new Input();
    ArithmeticCalculator<Number> arithmeticCalculator = new ArithmeticCalculator<>();
    private boolean isRinng =true;

    public void run() {
        while (isRinng) {
            try {
                double num1 = input.inputNum("첫번째 숫자를 입력하시오");
                double num2 = input.inputNum("두번째 숫자를 입력하시오");
                char oper = input.inputOper("사칙연산 부호를 입력하시오");

                double result = Calculator.calculator(num1, num2, oper); //계산하기
                arithmeticCalculator.addResult(result); // 계산한값 리스트에넣기
                System.out.println("result = " + result); // 결과출력

                additional(); // 추가기능선택하기

            } catch (Exception e) {
                System.out.println("오류 " + e.getMessage() + "\n 처음부터 다시입력"); // 진행중 오류나면 처음부터 다시하기
            }
        }
        System.out.println("프로그램 종료");
        input.scClose();

    }

    // 추가기능 구현
    public void additional() {
        System.out.println("추가기능 선택");
        System.out.println("0. 프로그램 종료 | 1. 리스트 조회 | 2. 리스트 선택제거 | 3. 리스트 비우기 | 4. 기준값 이상 필터링 | 그외 다시 계산하기");
        String in = input.inputString("입력 : ");

        switch (in) {
            case "1" -> System.out.println("이력 : " + arithmeticCalculator.getResults()); //리스트출력
            case "2" -> {
                System.out.println("이력 : " + arithmeticCalculator.getResults());
                double remove = input.inputNum("위 리스트중 삭제대상 번호 1~리스트총갯수 입력 : "); // 지울거 선택하기
                arithmeticCalculator.removeResult(remove);
            }
            case "3" -> {
                arithmeticCalculator.clearResutl(); //리스트전체삭제
                System.out.println("리스트 전체 삭제 완료");
            }
            case "4" -> {
                double number = input.inputNum("필터 기준값을 입력하시오 : "); // 기준값보다 큰값 리스트에서뽑기
                arithmeticCalculator.betterFilter(number);
            }
            case "0" -> isRinng = false;

        }

    }

}
