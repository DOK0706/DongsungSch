package nexon;

import java.util.Scanner;

public class nexon2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] map = new int[N + 1][N + 1];
        int w;
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map.length; j++) {
                w = sc.nextInt();
                map[i][j] = w;
            }
        }

        int Bplus = 0, Sumplus, Bcross = 0, Sumcross;
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map.length; j++) {
                int l = 1, Nplus = 0, Splus = 0, Eplus = 0, Wplus = 0;
                while (true) {
                    try {
                        if (l > M) break;
                        Eplus = map[i][j + l] + Eplus;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                l = 1;
                while (true) {
                    try {
                        if (l > M) break;
                        Wplus = map[i][j - l] + Wplus;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                l = 1;
                while (true) {
                    try {
                        if (l > M) break;
                        Nplus = map[i - l][j] + Nplus;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                l = 1;
                while (true) {
                    try {
                        if (l > M) break;
                        Splus = map[i + l][j] + Splus;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                Sumplus = Nplus + Splus + Wplus + Eplus + map[i][j];
                if (Sumplus > Bplus) {
                    Bplus = Sumplus;
                }

                l = 1;


                int Oncross = 0, Twcross = 0, Thcross = 0, Focross = 0;
                while (true) {
                    try {
                        if (l > M) break;
                        Oncross = map[i - l][j + l] + Oncross;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                l = 1;
                while (true) {
                    try {
                        if (l > M) break;
                        Twcross = map[i + l][j + l] + Twcross;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                l = 1;
                while (true) {
                    try {
                        if (l > M) break;
                        Thcross = map[i + l][j - l] + Thcross;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                l = 1;
                while (true) {
                    try {
                        if (l > M) break;
                        Focross = map[i - l][j - l] + Focross;
                        l++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
                Sumcross = Oncross + Twcross + Thcross + Focross + map[i][j];
                if (Sumcross > Bcross) {
                    Bcross = Sumcross;
                }
            }
        }
        if (Bcross > Bplus) System.out.println(Bcross);
        else if (Bplus > Bcross) System.out.println(Bplus);
        else {
            int Best = 0;
            for (int[] ints : map) {
                for (int j = 0; j < map.length; j++) {
                    if (ints[j] > Best) Best = ints[j];
                }
            }
            System.out.println(Best);
        }
    }
}
