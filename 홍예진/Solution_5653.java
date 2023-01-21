import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K, ans, map[][];
	static int delta[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static PriorityQueue<Point> pq;
	static LinkedList<Point> next;

	public static void breed(int time) {
//		System.out.println(pq);
		while (!pq.isEmpty()) {
			if (pq.peek().activeTime != time) {
				pq.addAll(next);
				next.clear();
				return;
			}

			Point p = pq.poll();
			if (K < p.activeTime + p.X) {
				ans++;
			}

			for (int d[] : delta) {
				int i = p.i + d[0];
				int j = p.j + d[1];
				if (map[i][j] == 0) {
					map[i][j] = p.X;
					next.add(new Point(i, j, p.X, time + 1 + p.X));
				}
			}

		}

		pq.addAll(next);
		next.clear();

	}

	public static void solve() {
		int time = 1;
		while (true) {
			// 번식
			breed(time);

			// 시간 지남
			time++;
			if (time == K)
				break;

		}

		ans += pq.size();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// -300 ~ N+300 / M + 300
			map = new int[N + 600][M + 600];

			pq = new PriorityQueue<Point>();
			next = new LinkedList<>();
			ans = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int X = Integer.parseInt(st.nextToken());
					map[i + 300][j + 300] = X; // 음수 좌표를 고려하여 300+
					if (X > 0)
						pq.offer(new Point(i + 300, j + 300, X, X));
				}
			}

			solve();

			System.out.println("#" + tc + " " + ans);
		}
		br.close();
		bw.flush();
		bw.close();
	}
}

class Point implements Comparable<Point> {
	int i, j, X, activeTime;

	public Point(int i, int j, int X, int activeTime) {
		this.i = i;
		this.j = j;
		this.X = X;
		this.activeTime = activeTime;
	}

	public int compareTo(Point p) {
		if (this.activeTime == p.activeTime)
			return p.X - this.X;
		return this.activeTime - p.activeTime;
	}

	public String toString() {
		return "[" + activeTime + "] (" + (i - 300) + ", " + (j - 300) + ")";
	}
}
