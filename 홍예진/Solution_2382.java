import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static int delta[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int[] oppositeDir = { 1, 0, 3, 2 };
	public static List<Point> points;
	public static int n, m, k;

	// 가장자리 => 미생물 절반이 죽고 이동방향이 반대로
	// 두개 이상의 군집이 한 셀에 모이는 경우 군집이 합쳐진다.
	// M시간 후 남아있는 미생물 수의 총합?

	public static boolean isBoundary(int i, int j) {
		return i == 0 || i == n-1 || j == 0 || j == n-1;
	}

	public static void print() {
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (idx < points.size() && points.get(idx).i == i && points.get(idx).j == j) {
					System.out.print(points.get(idx).count+"(" + "상하좌우".charAt(points.get(idx++).dir) + ")\t");
				} else {
					System.out.print(0 + "\t");
				}
			}
			System.out.println();
		}

		System.out.println();
	}

	public static void solve() {
		// m시간동안 관찰
		while (m-- > 0) {
			int qSize = points.size();
			PriorityQueue<Point> next = new PriorityQueue<Point>();
			
			Collections.sort(points);
//			print();
			// 1. 이동한다.
			while (qSize-- > 0) {
				Point p = points.get(0);
				points.remove(0);

				p.i += delta[p.dir][0];
				p.j += delta[p.dir][1];
				if (isBoundary(p.i, p.j)) {
					p.count = p.count / 2; // 약품 셀은 절반이 죽고 이동방향이 반대로 바뀐다.
					p.dir = oppositeDir[p.dir];
				}
				if (p.count != 0)
					next.add(p);
			}

			// 2. 같은 좌표는 합산을 반복한다.
			while (!next.isEmpty()) {
				Point p = next.poll();
				int max = p.count;
				while (!next.isEmpty() && next.peek().i == p.i && next.peek().j == p.j) {
					Point o = next.poll();
					if (max < o.count) { // 크기가 가장 큰 군집의 방향으로 개신
						max = o.count;
						p.dir = o.dir;
					}
					p.count += o.count; // 군집 합체
				}
				points.add(p);
			}
//			print();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			points = new LinkedList<>();

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				points.add(new Point(row, col, count, dir - 1));
			}

			solve();
			int ans = 0;
			for (Point p : points) {
				ans += p.count;
			}

			System.out.println("#" + tc + " " + ans);
		}
	}
}

class Point implements Comparable<Point> {
	int i, j, count, dir;

	public Point(int i, int j, int count, int dir) {
		super();
		this.i = i;
		this.j = j;
		this.count = count;
		this.dir = dir;
	}

	public int compareTo(Point p) {
		return this.i == p.i ? this.j - p.j : this.i - p.i;
	}

	@Override
	public String toString() {
		return "Point [i=" + i + ", j=" + j + ", count=" + count + ", dir=" + dir + "]";
	}

}
