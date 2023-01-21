import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, L, map[][];
	static LinkedList<Point> q;
	static int startIdx, qSize;
	static int delta[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
	static int[] opposite = { 1, 0, 3, 2 };
	static String[] pipe = { "", "1111", "1100", "0011", "1001", "0101", "0110", "1010" };

	public static boolean inBoundary(int i, int j) {
		return 0 <= i && 0 <= j && i < N && j < M;
	}

	public static void solve() {
		int endIdx = startIdx + qSize;
		qSize = 0;
		for (int idx = startIdx; idx < endIdx; idx++) {
			Point p = q.get(idx);
			for (int t = 0; t < 4; t++) {
				if (pipe[p.num].charAt(t) == '1') {
					int i = p.i + delta[t][0];
					int j = p.j + delta[t][1];
					// 내가 가려고하는 방향(t)을 받을 수 있는 반대방향을 받을 수 있는 파이프여야한다.
					if (inBoundary(i, j) && map[i][j] != 0 && pipe[map[i][j]].charAt(opposite[t]) =='1') {
						q.add(new Point(i, j, map[i][j]));
						map[i][j] = 0;
						qSize++;
					}
				}
			}
		}
		startIdx = endIdx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			q = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			q.add(new Point(r, c, map[r][c]));
			map[r][c] = 0;
			L--;

			startIdx = 0;
			qSize = 1;

			while (L-- > 0 && qSize != 0)
				solve();

			System.out.println("#" + tc + " " + q.size());
		}
	}

}

class Point {
	int i, j, num;

	public Point(int i, int j, int num) {
		super();
		this.i = i;
		this.j = j;
		this.num = num;
	}

	@Override
	public String toString() {
		return "Point [i=" + i + ", j=" + j + ", num=" + num + "]";
	}

}
