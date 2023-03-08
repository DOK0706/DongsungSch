package nexon;

import java.util.Scanner;

public class nexon3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int w;
        int[][] scan = new int[T][3];
        for (int i = 0; i < scan.length; i++) {
            for (int j = 0; j < scan[0].length; j++) {
                w = sc.nextInt();
                scan[i][j] = w;
            }
        }
        for (int[] ints : scan) {
            int N = ints[0];
            int A = ints[1];
            int B = ints[2];
            int[][] snail = new int[N][N];

            int print = N;
            int k = 1;
            int right = -1;
            int bottom = 0;
            int top = 1;
            int iA = 0, jA = 0, iB = 0, jB = 0;
            for (int l = N; l > 0; l--) {

                for (int j = 0; j < print; j++) {
                    right += top;
                    snail[bottom][right] = k;
                    k++;
                }

                print--;

                for (int j = 0; j < print; j++) {
                    bottom += top;
                    snail[bottom][right] = k;
                    k++;
                }

                top = top * (-1);
            }

            for (int e = 0; e < snail.length; e++) {
                for (int f = 0; f < snail[e].length; f++) {
                    if (snail[e][f] == A) {
                        iA = e;
                        jA = f;
                    }
                    if (snail[e][f] == B) {
                        iB = e;
                        jB = f;
                    }
                }
            }

            int X, Y;

            if (iA > iB) {
                if (jA > jB) {
                    X = jA - jB;
                    Y = iA - iB;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
                if (jB > jA) {
                    X = jB - jA;
                    Y = iA - iB;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
                if (jA == jB) {
                    X = jB - jA;
                    Y = iA - iB;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
            }
            if (iB > iA) {
                if (jA > jB) {
                    X = jA - jB;
                    Y = iB - iA;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
                if (jB > jA) {
                    X = jB - jA;
                    Y = iB - iA;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
                if (jA == jB) {
                    X = jB - jA;
                    Y = iB - iA;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
            }

            if (iB == iA) {
                if (jA > jB) {
                    X = jA - jB;
                    Y = iB - iA;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
                if (jB > jA) {
                    X = jB - jA;
                    Y = iB - iA;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
                if (jA == jB) {
                    X = jB - jA;
                    Y = iB - iA;
                    if (X == Y) System.out.println("YES");
                    else System.out.println("NO");
                }
            }
        }
    }
}