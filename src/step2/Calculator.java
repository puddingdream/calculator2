package step2;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<Integer> results = new ArrayList<>();


    public int result;

    public void addResults(int result){
        results.add(result);
    }

    public int calculator(int num1, int num2, char oper) {
        switch (oper) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                if (num2 != 0) {
                    result = num1 / num2;
                }throw new RuntimeException("나눗셈은 0으로 나눌수없습니다.");
            }default -> throw new RuntimeException("잘못된 부호 입력 : " + oper);
        } return result;
    }
}
