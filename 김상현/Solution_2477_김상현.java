import java.io.*;
import java.util.*;
 
public class Solution_2477_김상현 {
 
    public static int t;
    public static int n, m, k, a, b;
    public static List<int[]>[] A;
    public static List<int[]>[] B;
    public static int[] tA;
    public static int[] tB;
    public static List<int[]> person;
    public static PriorityQueue<Integer> readyA = new PriorityQueue<>();
    public static Queue<Integer> readyB = new LinkedList<>();
    public static int[][] result;
    public static int answer;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            A = new ArrayList[n + 1];
            B = new ArrayList[m + 1];
            for (int i = 0; i <= n; i++) {
                A[i] = new ArrayList<>();
            }
            for (int i = 0; i <= m; i++) {
                B[i] = new ArrayList<>();
            }
            tA = new int[n + 1];
            tB = new int[m + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                tA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++) {
                tB[i] = Integer.parseInt(st.nextToken());
            }
            person = new ArrayList<>();
            person.add(new int[]{});
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                int t = Integer.parseInt(st.nextToken());
                person.add(new int[]{i, t});
            }
            result = new int[k + 1][2];
            int t = 0;
            while (true) {
                if (isOver(t)) {
                    break;
                }
                for (int i = 1; i <= k; i++) {
                    if (person.get(i)[1] == t) {
                        readyA.add(i);
                    }
                }
                for (int i = 1; i <= n; i++) {
                    if (!A[i].isEmpty()) {
                        A[i].get(0)[1]--;
                        if (A[i].get(0)[1] == 0) {
                            readyB.add(A[i].get(0)[0]);
                            A[i].clear();
                        }
                    }
                }
                for (int i = 1; i <= n; i++) {
                    if (A[i].isEmpty()) {
                        if (!readyA.isEmpty()) {
                            int num = readyA.poll();
                            A[i].add(new int[]{num, tA[i]});
                            result[num][0] = i;
                        }
                    }
                }
                for (int i = 1; i <= m; i++) {
                    if (!B[i].isEmpty()) {
                        B[i].get(0)[1]--;
                        if (B[i].get(0)[1] == 0) {
                            B[i].clear();
                        }
                    }
                }
                for (int i = 1; i <= m; i++) {
                    if (B[i].isEmpty()) {
                        if (!readyB.isEmpty()) {
                            int num = readyB.poll();
                            B[i].add(new int[]{num, tB[i]});
                            result[num][1] = i;
                        }
                    }
                }
                t++;
            }
            for (int i = 1; i <= k; i++) {
                if (result[i][0] == a && result[i][1] == b) {
                    answer += i;
                }
            }
            if (answer == 0) {
                answer = -1;
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
 
    public static boolean isOver(int t) {
        int mx = 0;
        for (int i = 1; i <= k; i++) {
            mx = Math.max(mx, person.get(i)[1]);
        }
        boolean flag1 = false;
        if (t > mx) {
            flag1 = true;
        }
        boolean flag2 = true;
        for (int i = 1; i <= n; i++) {
            if (!A[i].isEmpty()) {
                flag2 = false;
                break;
            }
        }
        boolean flag3 = true;
        for (int i = 1; i <= m; i++) {
            if (!B[i].isEmpty()) {
                flag3 = false;
                break;
            }
        }
        return flag1 && flag2 && flag3 && readyA.isEmpty() && readyB.isEmpty();
    }
}
