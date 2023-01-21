package etc;

import java.io.*;
import java.util.*;

public class Solution_5648 {

    public static int t;
    public static int total;
    public static int n;
    public static int[][] board = new int[4001][4001];;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};
    public static Queue<int[]> atom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            total = 0;
            atom = new LinkedList<>();
            n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atom.add(new int[]{x, y, d, k});
                board[x][y] = k;
            }
            while (!atom.isEmpty()) {
                Queue<int[]> tmp = new LinkedList<>();
                while (!atom.isEmpty()) {
                    int[] cur = atom.poll();
                    int cx = cur[0];
                    int cy = cur[1];
                    int cd = cur[2];
                    int ck = cur[3];
                    int nx = cx + dx[cd];
                    int ny = cy + dy[cd];
                    board[cx][cy] -= ck;
                    if (!inRange(nx, ny)) {
                        continue;
                    }
                    tmp.add(new int[]{nx, ny, cd, ck});
                    board[nx][ny] += ck;
                }
                while (!tmp.isEmpty()) {
                    int[] cur = tmp.poll();
                    int cx = cur[0];
                    int cy = cur[1];
                    int cd = cur[2];
                    int ck = cur[3];
                    if (board[cx][cy] != ck) {
                        total += board[cx][cy];
                        board[cx][cy] = 0;
                    } else {
                        atom.add(cur);
                    }
                }
            }
            bw.write("#" + tc + " " + total);
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < 4001 && ny >= 0 && ny < 4001;
    }
}