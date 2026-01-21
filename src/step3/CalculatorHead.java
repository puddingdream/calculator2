package step3;

public class CalculatorHead {

    public void run(){
        Input input = new Input();
        ArithmeticCalculator<Number> arithmeticCalculator = new ArithmeticCalculator<>();

        double num1 = input.inputNum("첫번째 숫자를 입력하시오");
        double num2 = input.inputNum("두번째 숫자를 입력하시오");
        char oper = input.inputOper("사칙연산 부호를 입력하시오");

        double result = Calculator.calculator(num1, num2, oper);
        arithmeticCalculator.addResult(result);
        System.out.println("result = " + result);









        input.scClose();

    }
}
