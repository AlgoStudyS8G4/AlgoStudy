import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, X, ans;
	static int[][] map;

	public static boolean inBoundary(int i, int j) {
		return 0 <= i && 0 <= j && i < N && j < N;
	}

	// i,j에서 방향 d로 이동할때, X-1회만큼 기준높이 map[i][j]와 높이가 같은지 확인
	public static boolean isSameHeight(int i, int j, int di, int dj, boolean[][] installed) {
		installed[i][j] = true;
		int ni = i + di;
		int nj = j + dj;
		int count = 0;
		while (count++ < X - 1) { 
			if (!inBoundary(ni, nj) || map[i][j] != map[ni][nj] || installed[ni][nj])
				return false;
			installed[ni][nj] = true;
			ni += di;
			nj += dj;
		}
		return true;
	}

	public static boolean isAble(int i, int j, int di, int dj) {
		int ni = i + di;
		int nj = j + dj;
		int h = map[i][j];
		boolean[][] installed = new boolean[N][N];
		while (inBoundary(ni, nj)) {
			if (h == map[ni][nj]) {
				ni += di;
				nj += dj;
				continue;
			}

			if (h - map[ni][nj] == 1) {
				// 한 칸 낮아지면, 낮아진 좌표에서부터 진행방향으로 확인한다.
				if (isSameHeight(ni, nj, di, dj, installed)) {
					h = map[ni][nj];
					ni += (X - 1) * di;
					nj += (X - 1) * dj;
				} else {
//					System.out.println("경사로의 충분한 공간이 없다. 진행 방향");
					return false; // 높이차가 있지만 경사로를 둘 수 없다.
				}
			} else if (map[ni][nj] - h == 1) {
				// 한 칸 높아지면, 직전 좌표에서부터 진행반대방향으로 확인한다.
				if (isSameHeight(ni - di, nj - dj, -1 * di, -1 * dj, installed)) {
					h = map[ni][nj];
					ni += di;
					nj += dj;
				} else {
//					System.out.println("경사로의 충분한 공간이 없다. 진행 반대 방향");
					return false;
				}
			} else {
//				System.out.println("높이차가 1이상 난다.");
				return false;
			}
		}
		return true;
	}

	public static void solve() {

		for (int i = 0; i < N; i++) {
			// 가로로 활주로 건설이 가능한지 확인
			if (isAble(i, 0, 0, 1)) {
//				System.out.println(i + "번째 행에서 활주로 건설가능");
				ans++;
			}
			if (isAble(0, i, 1, 0)) {
//				System.out.println(i + "번째 열에서 활주로 건설가능");
				ans++;
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ans = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			solve();

			System.out.println("#" + tc + " " + ans);
		}
	}
}
