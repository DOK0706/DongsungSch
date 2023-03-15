package dongsung;

import java.util.Scanner;

public class gyul {
    public static void say(int num) {

        if (num % 1000 / 100 == 1) System.out.print("빨강색");
        else if (num % 1000 / 100 == 2) System.out.print("노랑색");
        else if (num % 1000 / 100 == 3) System.out.print("파랑색");

        if (num % 100 / 10 == 1) System.out.print(" 삼각형에");
        else if (num % 100 / 10 == 2) System.out.print(" 사각형에");
        else if (num % 100 / 10 == 3) System.out.print(" 원에");

        if (num % 10 == 1) System.out.print(" 흰색배경");
        else if (num % 10 == 2) System.out.print(" 회색배경");
        else if (num % 10 == 3) System.out.print(" 검정배경");
    }

    public static int cal(int a, int b) {
        int result = 0;
        if (a / 100 == b / 100) {
            result = (a / 100) * 100 + result;
        } else result = (6 - (a / 100) - (b / 100)) * 100 + result;

        if ((a / 10) % 10 == (b / 10) % 10) {
            result = ((a / 10) % 10) * 10 + result;
        } else result = (6 - ((a / 10) % 10) - ((b / 10) % 10)) * 10 + result;

        if (a % 10 == b % 10) {
            result = (a % 10) + result;
        } else result = 6 - (a % 10) - (b % 10) + result;

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[27];
        int i, j, k, stac = 0;

        System.out.println(9 + "개의 카드에 넣을 조합을 각각 입력");
        System.out.println("100의 자리=도형색: 빨강=1/노랑=2/파랑=3");
        System.out.println("10의 자리=도형: 삼각형=1/사각형=2/원=3");
        System.out.println("1의 자리=배경색: 흰색=1/회색=2/검정=3");
        for (i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
            say(arr[i]);
            System.out.println("을 " + (i+1) + "에 입력 완료");
        }
        System.out.println("");
        System.out.println("가능한 합:");
        for (i = 0; i < 9 - 2; i++) {
            for (j = i + 1; j < 9 - 1; j++) {
                for (k = j + 1; k < 9; k++) {
                    if (cal(arr[i], arr[j]) == arr[k]) {
                        System.out.printf("%d/%d/%d \n", (i + 1), (j + 1), (k + 1));
                        stac++;
                        break;
                    }
                }
            }
        }
        System.out.println("총 합의 갯수: " + stac);
        System.out.println("결!");
    }
}

