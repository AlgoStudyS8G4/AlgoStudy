package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1446_지름길 {
	public static class Point{
		int s;
		int v;
		public Point(int s, int v) {
			this.s = s;
			this.v = v;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int dp[] = new int[D+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		ArrayList<ArrayList<Point>> arr = new ArrayList<>();
		for(int i = 0; i <= D; i++) {
			arr.add(new ArrayList<Point>());
		}
		for(int i = 0; i < N ; i ++  ) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(e - s <= v) continue; 
			if(e > D) continue;
			arr.get(e).add(new Point(s, v));
		}
		dp[0] = 0;
		for(int i = 1; i <= D; i++) {
			if(arr.get(i).size() == 0) dp[i] = dp[i-1] + 1;
			else {
				for(Point p : arr.get(i)) {
					dp[i] = Math.min(dp[i], Math.min(dp[i-1]+1, dp[p.s] + p.v));
				}
			}
		}
		System.out.println(dp[D]);
	}

}
