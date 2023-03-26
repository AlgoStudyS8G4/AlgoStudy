package boj.p2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * boj 2468 안전 영역
 * */
public class Main_2468 {
	static int N;
	static int[][] maps;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		result = Integer.MIN_VALUE;
		int maxHeight = Integer.MIN_VALUE;
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, maps[i][j]);
			}
		}
		
		for (int i = 0; i <= maxHeight; i++) {
			boolean[][] visited = new boolean[N][N];
			int cnt = 0;
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(maps[j][k] > i && !visited[j][k]) {//아직 방문하지 않은 안전한 영역이 있는 경우
						solve(j, k, i, visited);
						cnt++;
					}
				}				
			}
			result = Math.max(result, cnt);
			
		}
		System.out.println(result);
		
	}
	
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	/*
	 * 안전한 영역만 bfs로 탐색한다.
	 * */
	static void solve(int startX, int startY, int maxHeight, boolean[][] visited) {
		Queue<int[]> queue = new LinkedList<>();
		
		visited[startX][startY] = true;
		queue.add(new int[] {startX, startY});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int nowX = tmp[0];
			int nowY = tmp[1];
			
			for (int d = 0; d < deltas.length; d++) {
				int nextX = nowX + deltas[d][0];
				int nextY = nowY + deltas[d][1];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) continue;
				
				if(maps[nextX][nextY] <= maxHeight) continue;
				
				visited[nextX][nextY] = true;
				queue.add(new int[] {nextX, nextY});
			}
		}
	}
	
	
	
}
