package nexon;

import java.util.Scanner;

public class nexon1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] data = new int[M][3];
        int w;
        int[] USER = new int[N];

        // 0 = t, 1 = i 2 = s,
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                w = sc.nextInt();
                data[i][j] = w;
            }
        }
        boolean F = false;
        for (int i = 0; i < USER.length; i++) {
            USER[i] = data[i][1];
        }
        for (int i = 0; i < data.length; i++) {
            for (int k = 0; k < USER.length; k++) {
                for (int a = 1; a < data.length; a++) {
                    if (data[a][1] == USER[k]) {
                        if (data[i][2] == 0 && data[a][2] == 1 && data[i][0] + 60 <= data[a][0]) {
                            F = true;
                            System.out.println(k + "번째 유저" + F);
                        } else {
                            F = false;
                            System.out.println(k+ "번째 유저" + F);
                            break;
                        }
                    }
                }
            }
        }
        if (F == true) System.out.println("YES");
        else System.out.println("NO");
    }
}