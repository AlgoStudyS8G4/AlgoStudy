import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N, cards[], numbers[];
	static int min, max;

	public static int calculate(int num1, int num2, int op) {
		if (op == 0)
			return num1 + num2;
		if (op == 1)
			return num1 - num2;
		if (op == 2)
			return num1 * num2;
		// op == 3
		return num1 / num2;

	}

	public static void solve(int cnt, int result) {

		if (cnt == N) {
			min = Math.min(result, min);
			max = Math.max(result, max);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (cards[i] > 0) {
				cards[i]--;
				int rs = calculate(result, numbers[cnt], i);
				solve(cnt + 1, rs);
				cards[i]++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			cards = new int[4];
			numbers = new int[N];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				cards[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				numbers[i] = Integer.parseInt(st.nextToken());

			solve(1, numbers[0]);

			bw.write("#" + tc + " " + (max - min) + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}
}
