package boj.p7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * boj 7569 토마토
 * */
public class Main_7569 {
	
	static int result;
	static int M,H,N;
	static int[][] deltas = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, -1}, {0, 0, 1}};
	static int[][][] maps;
	static int[][][] dayCount;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		maps = new int[H][N][M];
		dayCount = new int[H][N][M];
		Queue<int[]> queue = new LinkedList<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					maps[i][j][k] = Integer.parseInt(st.nextToken());
					if(maps[i][j][k] == 1) {
						queue.add(new int[] {i, j, k});
					}
				}
			}
		}
		
		
		if(check()) {
			result = 0;
		}else {
			if(queue.isEmpty()) result = -1;
			else {
				solve(queue);
				if(!check()) result = -1;
				else maxDay();
			}
		}
			
		System.out.println(result);
	}
	
	
	static void solve(Queue<int[]> queue) {
	
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int nowHeight = tmp[0];
			int nowX = tmp[1];
			int nowY = tmp[2];
			
			if(check()) break;
			
			for (int dir = 0; dir < 6; dir++) {
				int nextX = nowX + deltas[dir][1];
				int nextY = nowY + deltas[dir][2];
				int nextHeight = nowHeight + deltas[dir][0];
				
				if(nextHeight < 0 || nextHeight >= H || nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || maps[nextHeight][nextX][nextY] == 1) continue;
				
				if(maps[nextHeight][nextX][nextY] == 0) {
					maps[nextHeight][nextX][nextY] = 1;
					dayCount[nextHeight][nextX][nextY] = dayCount[nowHeight][nowX][nowY] + 1;
					queue.add(new int[] {nextHeight, nextX, nextY});
				}
			}
		}
	}
	
	/*
	 * 토마토가 익은 날짜 구함
	 * */
	static void maxDay() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					result = Math.max(result, dayCount[i][j][k]);
				}
			}
		}
	}
	
	/*
	 * 토마토가 익어있는지 확인
	 * */
	static boolean check() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(maps[i][j][k] == 0) return false;
				}
			}
		}
		//전부 익어있는 상태
		return true;
	}
	
}
