package etc;

import java.io.*;
import java.util.*;

public class Solution_5653 {

    public static int t;
    public static int n, m, k;
    public static int[][] board;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static PriorityQueue<int[]> pq;
    public static PriorityQueue<int[]> tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            Comparator<int[]> comparator = (o1, o2) -> o2[2] - o1[2];
            pq = new PriorityQueue<>(comparator);
            tmp = new PriorityQueue<>(comparator);
            board = new int[1000][1000];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    board[i + 500][j + 500] = Integer.parseInt(st.nextToken());
                    if (board[i + 500][j + 500] == 0) {
                        continue;
                    }
                    pq.add(new int[]{i + 500, j + 500, board[i + 500][j + 500], 0});
                }
            }
            while (k-- > 0) {
                tmp.clear();
                while (!pq.isEmpty()) {
                    int cx = pq.peek()[0];
                    int cy = pq.peek()[1];
                    int cl = pq.peek()[2];
                    int ct = pq.peek()[3];
                    pq.poll();
                    if (ct < cl) {
                        tmp.add(new int[]{cx, cy, cl, ct + 1});
                    } else if (ct == cl) {
                        for (int i = 0; i < 4; i++) {
                            int nx = cx + dx[i];
                            int ny = cy + dy[i];
                            if (board[nx][ny] == 0) {
                                tmp.add(new int[]{nx, ny, cl, 0});
                                board[nx][ny] = cl;
                            }
                        }
                        if (ct + 1 != cl * 2) {
                            tmp.add(new int[]{cx, cy, cl, ct + 1});
                        }
                    } else if (ct > cl && ct < cl * 2) {
                        if (ct + 1 != cl * 2) {
                            tmp.add(new int[]{cx, cy, cl, ct + 1});
                        }
                    }

                }
                pq = new PriorityQueue<>(tmp);
            }
            bw.write("#" + tc + " " + pq.size());
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
