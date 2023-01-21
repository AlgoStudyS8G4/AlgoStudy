import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] schedule = new int[N + 1][2];
		int[] dp = new int[N + 1]; // dp[day] : day번째날부터 스케줄을 시작할때 최대 수익
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken()); // 상담시간
			schedule[i][1] = Integer.parseInt(st.nextToken()); // 금액
			if (N + 1 < i + schedule[i][0]) { // 퇴사 이후의 스케줄이라면
				schedule[i][1] = 0; // 얻을 수 있는 수익을 0으로 한다. (고려선상에서 제외한다.)
			}
		}
		dp[N] = schedule[N][1]; // 마지막날은 스케줄을 하는것이 이득이다. (퇴사일을 초과하지 않는 선에서)
		for (int day = N - 1; day > 0; day--) {
			int temp = schedule[day][1];
			int nextDay = day + schedule[day][0]; // day날에 상담을 진행하고 다음 상담을 진행할 수 있는 날짜 ex) 5일차에 2일짜리 상담 진행 => 7일차부터 상담
			if (nextDay <= N)
				temp += dp[nextDay];

			dp[day] = Math.max(temp, dp[day + 1]); // temp : day날의 상담을 포함하여 스케줄을 짰을 때 최대 수익. dp[day+1] : day+1번째날부터 고려한
													// 최대 수익 (=day날 고려X)
		}

		System.out.println(dp[1]);
	}
}
