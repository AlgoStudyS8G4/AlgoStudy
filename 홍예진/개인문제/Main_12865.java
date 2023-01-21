import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 22/12/27 학습. 복습 필요.
 * Fraction Knapsack problem
 */
public class Main {
	static Integer[][] dp;
	static int[] W;
	static int[] V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		W = new int[N];
		V = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		dp = new Integer[N][K + 1];

		System.out.println(knapsack(N - 1, K));
	}

	static int knapsack(int i, int k) {
		// i가 범위 밖
		if (i < 0)
			return 0;

		// 탐색하지 않은 위치
		if (dp[i][k] == null) {

			// 현재 물건을 못담는다. = 이전까지에서 최선을 찾는다.
			if (W[i] > k) {
				dp[i][k] = knapsack(i - 1, k);
			} else {
				// 현재 물건을 담는 경우의 수 vs 담지 않는 경우의 수
				dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
			}
		}

		return dp[i][k];
	}
}
