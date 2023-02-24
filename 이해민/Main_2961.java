package boj.p2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * boj 2961 도영이가 만든 맛있는 음식
 * */
public class Main_2961 {
	static int result;
	private static int N;
	private static int[] s;
	private static int[] b;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		s = new int[N];//신맛
		b = new int[N];//쓴맛
		result = Integer.MAX_VALUE;
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int data1 = Integer.parseInt(st.nextToken());
			int data2 = Integer.parseInt(st.nextToken());
			
			s[i] = data1;
			b[i] = data2;
		}
		
		for (int i = 1; i <= N; i++) {
			solve(new int[i], 0, 0, i);
		}
		
		System.out.println(result);
	}
	
	/**
	 * @param output 고른 재료의 인덱스
	 * @param start 현재 시작 위치
	 * @param cnt 현재 고른 재료 카운트
	 * @param R 총 고를 재료 개수
	 */
	static void solve(int[] output, int start, int cnt, int R) {
		if(cnt == R) {
			int sour = 1;
			int bit = 0;
			for (int i = 0; i < R; i++) {
				sour *= s[output[i]];
				bit += b[output[i]];
			}
			result = Math.min(result, Math.abs(sour - bit));			
			return;
		}
		
		for (int i = start; i < N; i++) {
			output[cnt] = i;
			solve(output, i+1, cnt+1, R);
		}
	}
	
	
}
