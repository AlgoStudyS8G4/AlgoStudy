import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] dp = new int[10001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		int idx = 4;
		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			if (dp[n] != 0) {
				System.out.println(dp[n]);
				continue;
			}
			//dp[1] = 1 --> 1 = 1
			//dp[2] = 2 --> 2 = 1+1 = 2
			//dp[3] = 3 --> 3 = 1+1+1 = 1+2 = 3
			//dp[4] = 4 --> 4 = 1+1+1+1 = 1+1+2 = 1+3 = 2+2
			//dp[5] = 5 --> 5 = 1+1+1+1+1 = 1+1+1+2 = 1+1+3 = 1+2+2 = 2+3
			while (idx <= n) {
				dp[idx] = dp[idx-1]; // 마지막에 1을 더한 조합의 수.
				dp[idx] += dp[idx-2] - dp[idx-3]; // 1이 없는 구성에 2를 더해서 완성한 조합의 수
				if(idx%3 == 0) dp[idx]++; // 3으로만 구성되는 조합의 수
				idx++;
 			}
			System.out.println(dp[n]);
		}
	}
}
