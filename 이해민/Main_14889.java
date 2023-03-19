package boj.p14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * boj 14889 스타트와 링크
 * */
public class Main_14889 {
	static int N;
	static int result;
	static int[][] maps;

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
		
		combination(0, 0, new boolean[N]);
	
		System.out.println(result);
	}
	
	static void combination(int now, int cnt, boolean[] visited) {
		if(cnt == N/2) {
			int[] team1 = new int[N/2];
			int[] team2 = new int[N/2];
			int cnt1 = 0, cnt2 = 0;
			
			for (int i = 0; i < N; i++) {
				if(visited[i]) team1[cnt1++] = i;
				else team2[cnt2++] = i;
			}
			
			int team1Sum = solve(team1);
			int team2Sum = solve(team2);
			
			result = Math.min(result, Math.abs(team2Sum - team1Sum));
			
			return;
		}
		
		for (int i = now; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			combination(i+1, cnt+1, visited);
			visited[i] = false;
		}
		
	}
	
	static int solve(int[] team) {
		int sum = 0;
		for (int i = 0; i < N/2; i++) {
			for (int j = i+1; j < N/2; j++) {
				sum += maps[team[i]][team[j]] + maps[team[j]][team[i]];
			}
		}
		
		return sum;
	}
	
}
