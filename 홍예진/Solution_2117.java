
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, map[][], ans, delta[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int homeCount;

	public static boolean inBoundary(int i, int j) {
		return 0 <= i && 0 <= j && i < N && j < N;
	}

	// 현재 영역의 크기가 흑자 영역내에 있는 지.
	public static boolean isBlack(int k, int profit) {
		return k * k + (k - 1) * (k - 1) <= profit;
	}

	// 너비가 k일때 운영 비용 = k*k + (k-1)*(k-1)
	// 손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들에 제공하는 서비스 영역?
	public static void solve() {
		if (homeCount == 0) {
			ans = 0;
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int k = 2;
				int profit = map[i][j] == 1 ? M : 0;
				int count = map[i][j];
				boolean[][] visited = new boolean[N][N];
				Queue<Point> q = new LinkedList<>();
				q.add(new Point(i, j));
				visited[i][j] = true;
				while (isBlack(k, homeCount * M)) {

					int qSize = q.size();
					while (qSize-- > 0) {
						Point p = q.poll();
						for (int d[] : delta) {
							int ni = p.i + d[0];
							int nj = p.j + d[1];
							if (inBoundary(ni, nj) && !visited[ni][nj]) {
								visited[ni][nj] = true;
								q.add(new Point(ni, nj));
								if (map[ni][nj] == 1) {
									profit += M;
									count++;
								}
							}
						}
					}

					if (isBlack(k, profit))
						ans = Math.max(ans, count);
					k++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					homeCount += map[i][j];
				}
			}
			ans = 1;
			solve();

			System.out.println("#" + tc + " " + ans);
		}
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
