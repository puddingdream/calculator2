package commerce;

import java.util.Scanner;

public class Keyboard {
    private Scanner sc = new Scanner(System.in);
    // 숫자받기
    public int inputInt (String message){
        while (true){
            System.out.println(message);
            String input = sc.nextLine().trim();
            try{
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println(" 정수만 입력 가능합니다.");
            }
        }
    }
    // 문자받기
    public String inputString(String message) {
        System.out.println(message);
        return sc.nextLine();
    } //영어검증
    public boolean isEnglish(String input) {
        // ^: 시작, [a-zA-Z ]: 영어 대소문자와 공백, +: 하나 이상, $: 끝
        return input.matches("^[a-zA-Z ]+$");
    } //한글검증
    public boolean isKorean(String input) {
        // 가-힣: 현대 한글 모든 글자 범위
        return input.matches("^[가-힣 ]+$");
    }

    // 스캐너닫기
    public void scClose(){
        sc.close();
    }


}
