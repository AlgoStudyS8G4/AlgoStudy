import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		int[] visit = new int[N];
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < X; i++) {
			visit[i] = Integer.parseInt(st.nextToken());
			sum += visit[i];
		}
		int ans = sum;
		int cnt = 1;

		for (int i = X; i < N; i++) {
			visit[i] = Integer.parseInt(st.nextToken());
			sum += visit[i];
			sum -= visit[i-X];
			if (ans < sum) {
				cnt = 1;
				ans = sum;
			} else if (ans == sum) {
				cnt++;
			}
		}
		if (ans == 0)
			System.out.println("SAD");
		else {
			System.out.println(ans);
			System.out.println(cnt);
		}

	}

}
