import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void solve() {
		int[][] dp = new int[N][M];// dp[i][j] : i번째 app까지만 고려하여 j메모리량을 비우는 경우의 최소 비용
	}

	static class App {
		int memory, cost;

		public App(int memory, int cost) {
			super();
			this.memory = memory;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] memory = new int[N];
		int[] cost = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			memory[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			cost[i] = Integer.parseInt(st.nextToken());
		
		
		solve();
		
	}
}