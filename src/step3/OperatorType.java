package step3;

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

    public double calculat(double n1, double n2){
        return function.apply(n1, n2);
    }

}
