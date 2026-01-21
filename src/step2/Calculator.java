package step2;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<Integer> results = new ArrayList<>(); // 새리스트만들기

    public int result; // 계산기 결과 통일을 위한 속성

    public void addResults(int result){ //리스트에 결과 추가하기
        results.add(result);
    }

    public List<Integer> getResults() { // 리스트내용 가져오기
        return results;
    }
    public void removeResults(){ // 리스트의 첫번째값 삭제하기
        if(results.isEmpty()){
            System.out.println("결과가 비어있습니다.");
        } results.removeFirst();
    }

    // 계산기
    public int calculator(int num1, int num2, char oper) {
        switch (oper) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    throw new RuntimeException("나눗셈은 0으로 나눌수없습니다.");
                }result = num1 / num2;
            }default -> throw new RuntimeException("잘못된 부호 입력 : " + oper);
        } return result;
    }
}
