import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int n, w, h, ans;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void fallDown(int[][] map, int[] topHeight) {
		for (int j = 0; j < w; j++) {
			if (topHeight[j] == h)
				continue;

			Queue<Integer> block = new LinkedList<>();
			for (int i = h - 1; i >= 0; i--) {
				if (map[i][j] != 0)
					block.add(map[i][j]);
				map[i][j] = 0;
			}

			topHeight[j] = h - block.size();

			for (int i = h - 1; i >= 0 && !block.isEmpty(); i--) {
				map[i][j] = block.poll();
			}

		}
	}

	public static boolean inBoundary(int i, int j) {
		return 0 <= i && 0 <= j && i < h && j < w;
	}

	public static int[][] getClone(int[][] origin) {
		int[][] clone = new int[origin.length][origin[0].length];
		for (int i = 0; i < origin.length; i++) {
			clone[i] = origin[i].clone();
		}
		return clone;
	}

	public static void bomb(int row, int col, int[][] map, int[][] clone) {
		
		clone[row][col] = 0;
		Point bomb = new Point(row, col);
		Queue<Point> q = new LinkedList<Point>();
		q.add(bomb);

		while (!q.isEmpty()) {
			bomb = q.poll();
			int length = map[bomb.i][bomb.j] - 1;
			for (int d[] : delta) {
				int ni = bomb.i + d[0];
				int nj = bomb.j + d[1];
				int l = length;
				while (inBoundary(ni, nj) && l > 0) {
					if (clone[ni][nj] > 1)
						q.offer(new Point(ni, nj));
					clone[ni][nj] = 0;
					ni += d[0];
					nj += d[1];
					l--;
				}
			}

		}
	}

	public static void solve(int cnt, int[][] map, int[] topHeight) {
		int count = 0;
		for (int j = 0; j < w; j++) {
			count += h - topHeight[j];
		}
		if (count < ans) {
			ans = count;
//			System.out.println("최소값 갱신["+cnt+"] - "+count);
//			print(map);
		}

		if (cnt == n || count == 0) {
			return;
		}

		for (int col = 0; col < w; col++) {
			int row = topHeight[col];
			if (row != h) {
				int[][] clone = getClone(map);
				bomb(row, col, map, clone);
				
				int[] topClone = topHeight.clone();
				// 벽돌 떨어트리기
				fallDown(clone, topClone);
				
				// print(clone);
				solve(cnt + 1, clone, topClone);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int[][] map = new int[15][12];
			int[] topHeight = new int[15];
			Arrays.fill(topHeight, h);
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;

					if (topHeight[j] == h && num != 0)
						topHeight[j] = i;
				}
			}
			solve(0, map, topHeight);
			System.out.print("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

	static void print(int[][] map) {
		for (int[] mm : map) {
			for (int m : mm)
				System.out.print(m + " ");
			System.out.println();
		}
		System.out.println();
	}
}

class Point {
	int i, j;

	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
