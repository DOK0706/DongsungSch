package nexon;

import java.util.Scanner;

public class nexon4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] A = new int[N];
        int[] XA = new int[N];
        int[] AA = new int[N];
        int[] TT = new int[N];
        int[] tt = new int[N];

        int w, m, n = 0, o;
        for (int i = 0; i < A.length; i++) {
            w = sc.nextInt();
            A[i] = w;
        }
        int max = A[0], Max;
        for (int i = 0; i < A.length; i++) {
            XA[i] = A[i];
        }
        TT[0] = A[0];

        for (int i = 1; i < N; i++) {
            TT[i] = Math.max(TT[i - 1] + A[i], A[i]);
            max = Math.max(max, TT[i]);
        }


        for (int M = 0; M != A.length; M++) {
            for (m = 1; m != A.length - M; m++) {
                o = A.length - m;
                for (int i = A.length - o, j = n; i >= 0; i--, j++) {
                    XA[j + M] = A[i + M];
                }
                System.arraycopy(A, 0, AA, 0, A.length);
                for (int i = 0; i < A.length; i++) {
                    if (XA[i] != AA[i]) {
                        AA[i] = XA[i];
                    }
                }

                Max = AA[0];
                tt[0] = AA[0];

                for (int i = 1; i < N; i++) {
                    tt[i] = Math.max(tt[i - 1] + AA[i], AA[i]);
                    Max = Math.max(Max, tt[i]);
                }

                if (Max > max) {
                    max = Max;
                }

                System.arraycopy(A, 0, AA, 0, A.length);
                for (int i = 0; i < A.length; i++) {
                    XA[i] = AA[i];
                }
            }
        }
        System.out.println(max);
    }
}