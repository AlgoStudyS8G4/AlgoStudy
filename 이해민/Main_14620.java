package boj.p14620;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * boj 꽃길
 * 출력 : 꽃을 심기 위한 최소 비용                                                             
 * */
public class Main_14620 {
	private static int[][] maps;
	private static int N;
	static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		maps = new int[N][N];
		result = Integer.MAX_VALUE;
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(new int[3][3], 0, new boolean[N][N]);
		
		System.out.println(result);
	}
	
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	//조합으로 꽃 위치 3개 정하기
	static void comb(int[][] output, int cnt, boolean[][] visited) {
		if(cnt == 3) {	
			solve(output);
			return;
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if(!visited[i][j]) {
					output[cnt] = new int[] {i, j};
					visited[i][j] = true;
					comb(output, cnt+1, visited);
					visited[i][j] = false;
				}
			}
		}
		
	}
	
	
	//이 꽃 위치에 대하여 탐색해서 겹치지 않는지 확인하기
	static void solve(int[][] output) {
		boolean[][] checked = new boolean[N][N];
		int sum = 0;
		
		
		for (int i = 0; i < 3; i++) {
			int row = output[i][0];
			int col = output[i][1];
			
			sum += maps[row][col];
			 
			checked[row][col] = true;
			
			for (int d = 0; d < deltas.length; d++) {
				int nextX = row + deltas[d][0];
				int nextY = col + deltas[d][1];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || checked[nextX][nextY]) return;
				
				sum += maps[nextX][nextY];
				checked[nextX][nextY] = true;
			}
		}

		result = Math.min(result, sum);
	}
	
	
}
