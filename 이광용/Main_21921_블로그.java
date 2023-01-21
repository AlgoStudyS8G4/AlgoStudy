package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_21921_블로그 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int lt = 0;
		int rt = lt + X - 1;
		int sum = 0;
		for(int i = 0 ; i <= rt; i ++) {
			sum += arr[i];
		}
		int max = sum;
		int cnt = 1;

		while(rt < N-1) {
			sum -= arr[lt++];
			sum += arr[++rt];
			if(max < sum) {
				cnt = 1;
				max = sum;
			}else if(max == sum) {
				cnt++;
			}
		}
		if (max == 0) {
			System.out.println("SAD");
		}else {
			System.out.println(max);
			System.out.println(cnt);
		}
		
	}
}
