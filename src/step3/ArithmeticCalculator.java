package step3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 기능을 담는 클래스
public class ArithmeticCalculator <T extends Number>{
    private List<T> results = new ArrayList<>();

    public List<T> getResults() { // 게터
        return Collections.unmodifiableList(results);
    }

    public void addResult(T result){ //리스트 추가
         results.add(result);
        System.out.println("저장된 결과값 : " + getResults());
    }

    public void removeResult(T index){ // 배열 위치 지정해서 지우는기능
        try {
            if(results.isEmpty()){
                System.out.println("리스트가 비어있습니다.");
                return;
            }
            int in = index.intValue();
            results.remove(--in);
            System.out.println("저장된 결과값 : " + getResults());
        } catch (Exception e) {
            throw new RuntimeException("현재 있는 배열 총갯수보다 큰수또는 0보다 작은수는 불가능");
        }
    }

    public void clearResutl(){
        results.clear();
    } //배열 전체삭제

    public void filter(double num){ // 도전과제 3-3 보다큰값출력하기
        results.stream()
                .filter(result -> result.doubleValue() > num)
                .forEach(System.out::println);
    }





}
