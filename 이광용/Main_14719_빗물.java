package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719_빗물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //세로 높이 h
		int M = Integer.parseInt(st.nextToken()); // 가로 넓이 w
		int arr[] = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		//첫번째와 마지막 위치에는 물이 채워질 수 없으므로 뺀다.
		for(int i = 1; i < M-1; i ++) {
			//현재 위치 기준으로 왼쪽편에서 가장 높은 높이
			//오른편에서 가장 높은 높이
			int left = 0;
			int right =0;
			for(int j = 0; j < i; j++) {
				left = Math.max(left, arr[j]);
			}
			for(int j = i+1; j < M; j++) {
				right = Math.max(right, arr[j]);
			}
			//현재 위치의 높이가 left, right값보다 작다면
			//물을 채운다
			if(arr[i] < left && arr[i] < right) {
				ans += Math.min(left, right) - arr[i];
			}
		}
		
		System.out.println(ans);
	}//end of main
}
