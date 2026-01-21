package step3;

public class Calculator <T> {

    public static  <T extends Number> double calculator (T num1, T num2, char oper){
        OperatorType operater = OperatorType.fromSymbol(oper);
        return operater.calculat(num1.doubleValue(),num2.doubleValue());

    }




}
