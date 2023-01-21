import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N, K;
	static int ans, delta[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int map[][];
	static ArrayList<Point> top;

	public static boolean OutBoundary(int i, int j) {
		return i < 0 || j < 0 || i >= N || j >= N;
	}

	// 현재 좌표가 최대 값에 영향을 미치는 경우 -> 봉우리에서 시작하는 흐름에 영향을 끼칠 때
	public static int dfs(Point p) {
		int max = 1;
		for (int d[] : delta) {
			int i = p.i + d[0];
			int j = p.j + d[1];
			if (OutBoundary(i, j) || map[p.i][p.j] == map[i][j])
				continue;
			if (map[p.i][p.j] > map[i][j]) {
				max = Math.max(max, dfs(new Point(i, j)) + 1);
			}
		}
		return max;
	}

	public static void solve() {
		// 기본 맵의 최대 경로를 찾는다.
		// 꼭대기에서 출발하는 내림차순 최장길이 탐색
		for (Point t : top) {
			ans = Math.max(ans, dfs(t));
		}

		// 가장 높은 자리에서 내림차, 딱 한 곳을 최대 k만큼 깎을 수 있다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Point p = new Point(i, j);
				int origin = map[i][j];
				map[i][j] = map[i][j] - K < 0 ? 0 : map[i][j] - K;
				while (map[i][j] < origin) {
					for (Point t : top) {
						ans = Math.max(ans, dfs(t));
					}
					map[i][j]++;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			top = new ArrayList<>();
			map = new int[N][N];
			ans = 0;
			int topNum = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					if (num > topNum) {
						top = new ArrayList<>();
						top.add(new Point(i, j));
						topNum = num;
					} else if (num == topNum) {
						top.add(new Point(i, j));
					}
				}
			}

			solve();
			System.out.print("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}
}

class Point {
	int i, j;

	public Point(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

}
