import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String DNA = br.readLine();
		// dp[start][end] : start~end까지의 부분 수열내의 KOI 유전자의 최장 길이
		int[][] dp = new int[DNA.length()][DNA.length() + 1];
		String sub = DNA.substring(0, 2);
		if(sub.equals("at") || sub.equals("gc")) dp[0][2] = 2;
		
		for (int end = 3; end < DNA.length(); end++) {
			int case1 = dp[0][end - 1];
			int case2 = dp[1][end - 1]; // : aXt,gXc
			int case3 = 0;
			for (int mid = 2; mid < DNA.length(); mid++) {
				case3 = Math.max(case3,  dp[0][mid] + dp[mid][end]); // XY
			}
			
			dp[0][end] = Math.max(case1, case2);
			dp[0][end] = Math.max(dp[0][end], case3);
		}
		
		System.out.println(dp[0][DNA.length()]);
	}
}