package etc;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_4014_김상현 {

    public static int t;
    public static int n, x;
    public static int[][] board;
    public static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            total = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < n; i++) {
                int[] tmp = new int[n];
                for (int j = 0; j < n; j++) {
                    tmp[j] = board[i][j];
                }
                total += check(tmp);
            }
            for (int j = 0; j < n; j++) {
                int[] tmp = new int[n];
                for (int i = 0; i < n; i++) {
                    tmp[i] = board[i][j];
                }
                total += check(tmp);
            }
            bw.write("#" + tc + " " + total);
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int check(int[] tmp) {
        int cnt = 0;
        boolean[] visited = new boolean[n];
        int left = tmp[0];
        for (int i = 1; i < n; i++) {
            int right = tmp[i];
            if (right == left) {
                left = right;
            } else {
                if (Math.abs(left - right) != 1) {
                    return 0;
                } else {
                    if (right > left) {
                        for (int j = 0; j < x; j++) {
                            if (i - 1 - j < 0 || tmp[i - 1 - j] != right - 1 || visited[i - 1 - j]) {
                                return 0;
                            }
                        }
                        for (int j = 0; j < x; j++) {
                            visited[i - 1 - j] = true;
                        }
                        left = right;
                    } else {
                        for (int j = 0; j < x; j++) {
                            if (i + j >= n || tmp[i + j] != left - 1 || visited[i + j]) {
                                return 0;
                            }
                        }
                        for (int j = 0; j < x; j++) {
                            visited[i + j] = true;
                        }
                        left = right;
                    }
                }
            }
        }
        return 1;
    }
}