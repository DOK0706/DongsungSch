package dongsung;

import java.util.Objects;
import java.util.Scanner;

public class Ant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 입력하기 위한 Scanner 클래스의 객체 생성
        System.out.println("베르베르 개미 수열");
        System.out.println("개미는 몇 마리까지 있나요?"); // 질문
        int input1 = sc.nextInt(); // 입력 값 input1에 저장
        String temp = "1"; // 맨 처음 배열은 1로 시작 -> 이 후 기준 배열이 됨
        for (int i = 1; i < input1; i++) { // 입력된 값만큼 반복
            int length = 1; // 길이는 1로 시작
            // charAt(): String으로 저장된 문자열 중에서 한 글자만 선택해서 char타입으로 변환
            char head = temp.charAt(0); // 문자열 첫 대가리
            String result = ""; // 새로운 수열이 될 놈


            for (int j = 1; j < temp.length(); j++) { // 하나의 개미 수열을 구하는 반복문
                if (temp.charAt(j) == head) { // j번째 숫자가 대가리와 같으면
                    length++; // 길이에 +1을 한다
                } else { // j번째 숫자가 대가리와 같지 않을 때
                    result = result + head; // String형 "result"에 char형 "head"추가
                    result = result + length; // String형 'result"에 char형 "length" 추가
                    length = 1; // 숫자 길이 초기화
                    head = temp.charAt(j); // 새로운 대가리
                }
            }

            result = result + head; // 반복문 횟수가 하나 모자람
            result = result + length; // 반복문 횟수가 하나 모자람
            temp = result;
        }
        System.out.println(temp); // 완성된 배열 출력

        // 정보 시험에 나왔던 개미 수열 문제는 앞자리와 끝자리의 합을 구하거나 특정 숫자의 개수를 구하는 문제였다,
        // 앞자리 끝자리 합은 출력되는 배열에 앞과 끝만 확인하면 되니 어려울 것이 없겠지만
        // 특정 숫자의 개수는 값이 길어질수록 구하기 힘들 수 있으므로
        // 이왕 만든김에 해당 기능도 추가했다.

        System.out.println("특정 숫자의 개수가 필요하신가요? (y/n)"); // 질문
        String Y = "y"; // 입력 값 비교를 위함
        String input2 = sc.next(); // 입력 값 input2에 저장
        if (Objects.equals(input2, Y)) { // get이 'y'라면
            System.out.println("특정 숫자를 입력해주세요"); // 답변 요구
            int input3 = sc.nextInt(); // 입력 값 input3에 저장
            int cnt = 0; // 횟수 0으로 선언
            for (int i = 0; i < temp.length(); i++) { // 최종 배열의 길이만큼 반복
                if (temp.charAt(i) == Character.forDigit(input3, 10)) { // i번째 숫자가 입력값과 같다면 (gets의 char형 변환)
                    cnt++; // cnt에 +1
                }
            }
            System.out.println(input3 + "의 갯수: " + cnt); // cnt 출력
        }

    }
}
