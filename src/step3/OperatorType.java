package step3;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum OperatorType {
    PULS ('+', (n1 ,n2) -> n1 + n2),
    MINUS ('-', (n1 ,n2) -> n1 - n2),
    MULTIPLY ('*', (n1 ,n2) -> n1 * n2),
    DIVIDE ('/', (n1 ,n2) -> {
        if (n2 == 0){
            throw new RuntimeException("나눗셈은 0으로 나눌수 없습니다.");
        } return  n1 / n2;
    });

    private final char symbol;
    private final BiFunction<Double,Double,Double> function;

    OperatorType(char symbol, BiFunction<Double, Double, Double> function) {
        this.symbol = symbol;
        this.function = function;
    }
 //연산부호 enum 에있는걸로 찾는 로직
    public static OperatorType fromSymbol(char op){
        return Arrays.stream(OperatorType.values())
                .filter(oper -> oper.symbol == op)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("잘못된 부호입력 : " + op));
    }

    //Bifunction과 세트 Bfunction에 담겨있는 enum 계산식을 실행해서 돌려받음
    public double calculate(double n1, double n2){
        return function.apply(n1, n2);
    }

}
