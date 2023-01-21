import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//  메모리초과
public class Main {
	static int R, C;
	static char[][] lake;
	static Point[] swans;
	static Queue<Point> q;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<Point> nearList;

	public static boolean inBoundary(int i, int j) {
		return 0 <= i && 0 <= j && i < R && j < C;
	}

	// 백조 0의 호수에 인접한 좌표를 모두 @으로 바꾼다.
	// 이 과정에서 백조1를 만난다면 true 반환
	public static boolean isConnect() {

		while (!q.isEmpty()) {
			Point p = q.poll();
			lake[p.i][p.j] = '@';

			if (p.i == swans[1].i && p.j == swans[1].j)
				return true;

			for (int d[] : delta) {
				int ni = p.i + d[0];
				int nj = p.j + d[1];
				if (inBoundary(ni, nj) && lake[ni][nj] == '.')
					q.add(new Point(ni, nj));
			}
		}

		return false;
	}

	// 호수에 닿아있는 빙판을 녹인다.
	public static void melt() {
		int qSize = nearList.size();

		while (qSize-- > 0) {
			Point p = nearList.poll();
			lake[p.i][p.j] = '.';

			if (isNearTo(p.i, p.j, '@'))
				q.add(p);

			for (int d[] : delta) {
				int ni = p.i + d[0];
				int nj = p.j + d[1];
				if (inBoundary(ni, nj) && lake[ni][nj] == 'X') {
					nearList.add(new Point(ni, nj));
					lake[ni][nj] = 'T'; // 다음 녹을 빙하. 중복방지를 위해 임시로 T로 표기한다.
				}
			}

		}
	}

	public static void solve() {
		int count = 0;
		while (!isConnect()) {
			count++;
			// 빙판이 녹는다. 이때 인근의 빙판(X)이 다음 녹을 리스트로 추가된다.
			melt();

		}

		System.out.println(count);
	}

	public static void getNearList() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (lake[i][j] == 'X' && isNearTo(i, j, '.'))
					nearList.add(new Point(i, j));
			}
		}
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		public String toString() {
			return "(" + i + ", " + j + ")";
		}
	}

	public static boolean isNearTo(int i, int j, char target) {
		for (int d[] : delta) {
			int ni = i + d[0];
			int nj = j + d[1];
			if (inBoundary(ni, nj) && lake[ni][nj] == target)
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lake = new char[R][C];
		swans = new Point[2];
		int sIdx = 0;
		q = new LinkedList<>();
		nearList = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			lake[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (lake[i][j] == 'L') {
					swans[sIdx++] = new Point(i, j);
					lake[i][j] = '.';
				}
			}
		}
		getNearList();
		q.add(swans[0]);
		solve();
	}
}