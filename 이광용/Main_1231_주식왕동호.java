package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1231_주식왕동호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken()); //주식의 개수 
		int D = Integer.parseInt(st.nextToken());//주식 매입 및 매각을 할 기간
		int M = Integer.parseInt(st.nextToken());//초기 자금
		
		int map[][] = new int[C][D];
		for(int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < D; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		
		//dp[k]: 가능한 자본이 k원일 때, 최대 가치값 => (남은 돈 + 주식 가치 차액) 
		//남은 돈 == 자본금 - 직전 날 주식 가격 => 남은돈으로 얻을 수 있는 최대 가치를 구해야함
		
		int dp[] = new int[5000010];
		for(int i = 1; i < D; i ++) {
			//초기화
			Arrays.fill(dp, 0);
			for(int j = 0; j < C; j ++) {
				for(int k =1; k <= M; k++) {
					if(k - map[j][i-1] < 0) continue;
					dp[k] = Math.max(dp[k], dp[k - map[j][i-1]]+
							map[j][i] - map[j][i-1] );
				}
			}
			M+=dp[M];
		}
		System.out.println(M);
	}

}
