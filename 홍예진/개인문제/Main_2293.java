import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, k, value[], ans;

	static void solve() {
		// dp[i][sum] : i번째 동전까지만 고려하여 j만큼의 가치를 채우는 경우의 수
		// = dp[i-1][sum - value[i]] +
		int[][] dp = new int[n][k + 1];
		
		for (int sum = 0; sum <= k; sum++) {
			if(sum%value[0] == 0)
				dp[0][sum] = sum/value[0];
		}
		
		for (int i = 1; i < n; i++) {
			for (int sum = 0; sum <= k; sum++) {
				int max = sum / value[i];
				for (int j = 0; j <= max && 0 < sum - j * value[i] ; j++) {
					dp[i][j] += dp[i-1][sum - j * value[i]];
				}
			}
		}
		System.out.println(dp[n-1][k]);
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		value = new int[n];
		ans = 0;
		for (int i = 0; i < n; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(value);
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
}
