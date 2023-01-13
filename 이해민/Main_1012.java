package boj.p1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1012 {
	
	private static int[][] maps;
	private static int N;
	private static int M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			maps = new int[N][M];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				maps[y][x] = 1;
			}
			
			int result = 0;
			boolean[][] visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(!visited[i][j] && maps[i][j] == 1) {
						solve(visited, i, j);
						result++;
					}
				}
			}
			System.out.println(result);
		}
		
	}
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	
	static void solve(boolean[][] visited, int x, int y) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int nowX = tmp[0];
			int nowY = tmp[1];
			
			visited[nowX][nowY] = true;
			
			for (int i = 0; i < dir.length; i++) {
				int nextX = nowX + dir[i][0];
				int nextY = nowY + dir[i][1];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[nextX][nextY] || maps[nextX][nextY] == 0) continue;
				
				queue.add(new int[] {nextX, nextY});
			}
		}
	}
	
}
