import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 2][M + 2];
		int[][][] dp = new int[3][N + 1][M + 2]; // dp[d][i][j] : d방향으로 i,j에 도착하는 연료의 최소 값,
		final int INF = 3600;

		dp[0][0][0] = INF;
		dp[1][0][0] = INF;
		dp[2][0][0] = INF;
		dp[0][0][M+1] = INF;
		dp[1][0][M+1] = INF;
		dp[2][0][M+1] = INF;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			dp[0][i][0] = INF;
			dp[1][i][0] = INF;
			dp[2][i][0] = INF;
			dp[0][i][M+1] = INF;
			dp[1][i][M+1] = INF;
			dp[2][i][M+1] = INF;

			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0 : {1, -1}, 1 : {1, 0}, 2 : {1, 1} 로 이동
		int ans = INF;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[0][i][j] = Math.min(dp[1][i - 1][j], dp[2][i - 1][j - 1]) + map[i][j];
				dp[1][i][j] = Math.min(dp[0][i - 1][j + 1], dp[2][i - 1][j - 1]) + map[i][j];
				dp[2][i][j] = Math.min(dp[0][i - 1][j + 1], dp[1][i - 1][j]) + map[i][j];
			}
			
		}

		for (int j = 1; j <= M; j++) {
			for(int d = 0; d < 3; d++) {
				ans = Math.min(ans, dp[d][N][j]);				
			}
		}

		System.out.println(ans);
	}
}