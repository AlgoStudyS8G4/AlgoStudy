import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static String ans;
	static int delta[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static char[][] map;
	static Queue<Point> run, darkness;

	public static void solve() {
		int count = 1;
		int rSize = run.size();
		while (!run.isEmpty()) {

			int dSize = darkness.size();
			while (dSize-- > 0) {
				Point devil = darkness.poll();
				for (int[] d : delta) {
					int i = devil.i + d[0];
					int j = devil.j + d[1];

					if (map[i][j] == '.' || map[i][j] == '+') {
						map[i][j] = 'X';
						darkness.add(new Point(i, j));
					}
				}
			}
			dSize = darkness.size();

			while (rSize-- > 0) {
				Point s = run.poll();
				for (int[] d : delta) {
					int i = s.i + d[0];
					int j = s.j + d[1];

					if (map[i][j] == 'D') {
						ans = String.valueOf(count);
						return;
					} else if (map[i][j] == '.') {
						map[i][j] = '+';
						run.add(new Point(i, j));
					}
				}
			}
			count++;
			rSize = run.size();

			
		}

		ans = "GAME OVER";
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N + 2][M + 2];
			run = new LinkedList<>();
			darkness = new LinkedList<>();
			ans = null;

			Arrays.fill(map[0], 'X');
			Arrays.fill(map[N + 1], 'X');

			for (int i = 1; i <= N; i++) {
				String line = br.readLine();
				for (int j = 1; j <= M; j++) {
					map[i][j] = line.charAt(j - 1);
					if (map[i][j] == 'S') {
						run.add(new Point(i, j));
						map[i][j] = '.';
					} else if (map[i][j] == '*') {
						darkness.add(new Point(i, j));
						map[i][j] = 'X';
					}
				}
			}

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
