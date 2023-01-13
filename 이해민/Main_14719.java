package boj.p14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * boj 14719 빗물
 *
 */
public class Main_14719 {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());//세로
		int W = Integer.parseInt(st.nextToken());//가로
		
		
		int[] maps = new int[W];
		int result = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			maps[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int left = 0, right = 0;
		int min = 0;
		//양쪽에 현재 인덱스보다 높은 블록이 있어야 빗물이 고일 수 있다.
		for (int i = 1; i < W; i++) {
			left = maps[i];//현재 높이
			//왼쪽을 기준으로 현재 블록 높이보다 높은 높이 찾기
			for (int j = i-1; j >= 0; j--) {
				if(left < maps[j]) {
					left = maps[j];
				}
			}
			//오른쪽을 기준으로 현재 블록 높이보다 높은 높이 찾기
			right = maps[i];
			for (int j = i+1; j < W; j++) {
				if(right < maps[j]) {
					right = maps[j];
				}
			}
			min = Math.min(left, right);
			result += Math.abs(min-maps[i]);
		}
		System.out.println(result);
	}
	
	
}
