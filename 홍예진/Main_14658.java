import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L, K;
	static ArrayList<Point> list;
	static int ans;

	public static void solve() {
		// 임의의 한 점을 왼쪽 모서리 좌표로 한다.
		// 이때 이보다 y좌표가 같거나 작은 좌표를 위쪽 모서리 좌표로 할 때
		// 해당 범위에 포함되는 좌표의 수
		for (Point p1 : list) {
			for (Point p2 : list) {
				int minRow = Math.min(p1.i, p2.i);
				int minCol = Math.min(p1.j, p2.j);

				int count = 0;
				for (Point p : list) {
					if (minRow <= p.i && p.i <= minRow + L && minCol <= p.j && p.j <= minCol + L) {
						count++;
					}
				}
				ans = Math.min(ans, K - count);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		ans = K;

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			list.add(new Point(i, j));
		}

		solve();

		System.out.println(ans);
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
}
