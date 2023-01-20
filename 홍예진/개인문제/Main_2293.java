import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// not solved. 검토 필요.
public class Main {
	static int n, k, value[], ans;

	public static void solve() {
		int[][] dp = new int[n + 1][k + 1];

		// dp[idx][price] : 1~idx번째 동전까지만 사용하여 price 가격을 구성하는 방법의 수
//		dp[0][0] = 1;
		for(int i = value[1]; i <= k; i+= value[1])
			dp[1][i] = 1;
		
		for (int idx = 1; idx <= n; idx++) {
			for(int price = 0; price <= k; price += value[idx]) {
				dp[idx][k] += dp[idx-1][price];
			}
		}
		
		System.out.println(dp[n][k]);
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		value = new int[n + 1];
		ans = 0;
		for (int i = 1; i <= n; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(value);
	}

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(ans);
	}
}
