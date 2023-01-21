package etc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5650 {

    public static int t;
    public static int n;
    public static int[][] board;
    public static List<int[]>[] list;
    public static int[][][] worm;
    public static int mx;
    public static int sx;
    public static int sy;
    public static int cx;
    public static int cy;
    public static int cd;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] d = {{}, {2, 3, 1, 0}, {1, 3, 0, 2}, {3, 2, 0, 1}, {2, 0, 3, 1}, {2, 3, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            mx = 0;
            list = new ArrayList[11];
            for (int i = 6; i <= 10; i++) {
                list[i] = new ArrayList<>();
            }
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            worm = new int[n][n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] >= 6 && board[i][j] <= 10) {
                        list[board[i][j]].add(new int[]{i, j});
                    }
                }
            }
            for (int i = 6; i <= 10; i++) {
                if (list[i].size() == 0) {
                    continue;
                }
                int x1 = list[i].get(0)[0];
                int y1 = list[i].get(0)[1];
                int x2 = list[i].get(1)[0];
                int y2 = list[i].get(1)[1];
                worm[x1][y1][0] = x2;
                worm[x1][y1][1] = y2;
                worm[x2][y2][0] = x1;
                worm[x2][y2][1] = y1;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != 0) {
                        continue;
                    }
                    for (int k = 0; k < 4; k++) {
                        sx = i;
                        sy = j;
                        cx = i;
                        cy = j;
                        cd = k;
                        simulate();
                    }
                }
            }
            bw.write("#" + tc + " " + mx);
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void simulate() {
        int score = 0;
        while (true) {
            int nx = cx + dx[cd];
            int ny = cy + dy[cd];
            if (!inRange(nx, ny)) {
                score++;
                cd = (cd + 2) % 4;
                cx = nx;
                cy = ny;
            } else {
                if (board[nx][ny] == -1 || (nx == sx) && ny == sy) {
                    break;
                }
                if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
                    score++;
                    cx = nx;
                    cy = ny;
                    cd = d[board[nx][ny]][cd];
                } else if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
                    cx = worm[nx][ny][0];
                    cy = worm[nx][ny][1];
                } else {
                    cx = nx;
                    cy = ny;
                }
            }
        }
        mx = Math.max(mx, score);
    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }
}