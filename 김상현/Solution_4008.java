package etc;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_4008 {

    public static int t;
    public static int n;
    public static int[] cnt;
    public static int[] operand;
    public static int[] array;
    public static int mx;
    public static int mn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            mx = Integer.MIN_VALUE;
            mn = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            operand = new int[n];
            array = new int[n - 1];
            cnt = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                cnt[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                operand[i] = Integer.parseInt(st.nextToken());
            }
            recur(0);
            bw.write("#" + tc + " " + (mx - mn));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void recur(int cur) {
        if (cur == n - 1) {
            int result = operand[0];
            for (int i = 0; i < n - 1; i++) {
                result = calc(result, array[i], operand[i + 1]);
            }
            mn = Math.min(mn, result);
            mx = Math.max(mx, result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (cnt[i] == 0) {
                continue;
            }
            cnt[i]--;
            array[cur] = i;
            recur(cur + 1);
            cnt[i]++;
        }
    }

    private static int calc(int left, int option, int right) {
        if (option == 0) {
            return left + right;
        } else if (option == 1) {
            return left - right;
        } else if (option == 2) {
            return left * right;
        } else if (option == 3) {
            return left / right;
        }
        return 0;
    }
}