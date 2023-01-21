import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, ans, map[][];
	static int si, sj;

	public static boolean inBoundary(int i, int j) {
		return 0 <= i && 0 <= j && i < N && j < N;
	}

	public static void dfs(int i, int j, int dir, boolean[] visited, int w, int h) {

		if (i == si && j == sj) {
			ans = Math.max(ans, 2 * (w + h));
			System.out.println();
			return;
		}
		visited[map[i][j]] = true;
		if (dir == 0) {
			// 한칸 더 전진하는 경우
			if (inBoundary(i + 1, j + 1) && !visited[map[i + 1][j + 1]]) {
				dfs(i + 1, j + 1, dir, visited.clone(), w + 1, h);
			}
			// 방향을 꺾는 경우
			if (inBoundary(i + 1, j - 1) && !visited[map[i + 1][j - 1]]) {
				dfs(i + 1, j - 1, dir + 1, visited.clone(), w, h + 1);
			}
		} else if (dir == 1) {
			// 한칸 더 전진
			if (inBoundary(i + 1, j - 1) && !visited[map[i + 1][j - 1]]) {
				dfs(i + 1, j - 1, dir, visited.clone(), w, h + 1);
			}
			// 꺾는다. 이때부터 움직여야할 거리는 w, h으로 고정되었기 때문에, 재귀가 아닌 반복문으로 끝까지 간다.
			int path = 2 * (w + h);
			while (w > 0) {
				if (inBoundary(i - 1, j - 1) && !visited[map[i - 1][j - 1]]) {
					i--;
					j--;
					visited[map[i][j]] = true;
				} else {
					break; // 중간에 범위 밖으로 나가거나, 방문한 디저트를 방문한 경우
				}
				w--;
			}
			if (w == 0) {
				while (h-- > 0) {
					if (inBoundary(i - 1, j + 1) && !visited[map[i - 1][j + 1]]) {
						i--;
						j++;
						visited[map[i][j]] = true;
					} else {
						break;
					}
				}
			}

			// 도착 직전!!
			if (i == si + 1 && j == sj - 1) {
				ans = Math.max(ans, path);
			}
		}
	}

	public static void solve() {
		// 중간 좌표가 범위 밖, 방문한 디저트이면 탐색 중지
		// 주의. 다른 시작점, 다른 시작 방향으로 같은 디저트 루트가 중복 계산될 수 있다.
		// 이문제는 방향이 중요하지 않고, 디저트를 많이 먹을 수 있는 경로를 요구하고 있기 때문에 각 시작점에서 한 방향으로만 이동하는 경로를
		// 찾도록 한다.
		for (int i = 0; i < N; i++) {
			si = i;
			for (int j = 0; j < N; j++) {
				sj = j;
				// (i,j)에서 출발했을 때 나올 수 있는 경로의 최대 값은 2*(N-1-i)
				boolean[] visited = new boolean[101];
				visited[map[i][j]] = true;
				if (inBoundary(i + 1, j + 1) && !visited[map[i + 1][j + 1]]) {
					dfs(i + 1, j + 1, 0, visited, 1, 0);
				}
			}
		}
	}

	// 임의의 한 카페에서 출발하여 대각선 방향으로 움직이고
	// 서로 다른 디저트를 먹으면서 사각형 모양을 그리며 다시 출발점으로 돌아오는 경우,
	// 디저트를 가장 많이 먹을 수 있는 경로
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			solve();

			System.out.println("#" + tc + " " + (ans == 0 ? -1 : ans));
		}

	}

}
