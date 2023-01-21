package etc;

import java.io.*;
import java.util.*;

public class Solution_2382 {

    public static int t;
    public static int n, m, k;
    public static List<int[]> list;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            sum = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                switch (d) {
                    case 1:
                        d = 0;
                        break;
                    case 2:
                        d = 2;
                        break;
                    case 3:
                        d = 3;
                        break;
                    case 4:
                        d = 1;
                        break;
                }
                list.add(new int[]{x, y, c, d});
            }
            while (m-- > 0) {
                List<Integer>[][] tmp = new ArrayList[n][n];
                List<int[]> next = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        tmp[i][j] = new ArrayList<>();
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    int cx = list.get(i)[0];
                    int cy = list.get(i)[1];
                    int cc = list.get(i)[2];
                    int cd = list.get(i)[3];
                    int nx = cx + dx[cd];
                    int ny = cy + dy[cd];
                    if (nx == 0 || nx == n - 1 || ny == 0 || ny == n - 1) {
                        list.get(i)[2] = cc / 2;
                        list.get(i)[3] = (cd + 2) % 4;
                    }
                    list.get(i)[0] = nx;
                    list.get(i)[1] = ny;
                    if (list.get(i)[2] == 0) {
                        continue;
                    }
                    tmp[nx][ny].add(i + 1);
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int total = 0;
                        if (tmp[i][j].size() != 0) {
                            for (int k = 0; k < tmp[i][j].size(); k++) {
                                total += list.get(tmp[i][j].get(k) - 1)[2];
                            }
                            Collections.sort(tmp[i][j], (o1, o2) -> list.get(o2 - 1)[2] - list.get(o1 - 1)[2]);
                            int idx = tmp[i][j].get(0) - 1;
                            next.add(new int[]{list.get(idx)[0], list.get(idx)[1], total, list.get(idx)[3]});
                        }
                    }
                }
                list = new ArrayList<>(next);
            }
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i)[2];
            }
            bw.write("#" + tc + " " + sum);
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}