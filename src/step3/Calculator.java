package step3;

public class Calculator <T> {

    // 계산하는 로직 입력받은 연산부호 enum에 있는것과 비교해서 맞는걸 가져온뒤 숫자를 넣어서 계산실행후 결과돌려받기
    public static  <T extends Number> double calculator (T num1, T num2, char oper){
        OperatorType operater = OperatorType.fromSymbol(oper);
        return operater.calculate(num1.doubleValue(),num2.doubleValue());

    }




}
