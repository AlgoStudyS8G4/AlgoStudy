import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static String input;
	static int max;

	public static int calculate(int num1, char op, int num2) {
		if (op == '+')
			return num1 + num2;
		if (op == '-')
			return num1 - num2;
		// op == '*'
		return num1 * num2;
	}

	public static int getBracketValue(int idx) {
		// idx번째 숫자와 idx+2번째 숫자의 연산으로 나온 결과를 반환
		int num1 = Character.getNumericValue(input.charAt(idx));
		char op = input.charAt(idx + 1);
		int num2 = Character.getNumericValue(input.charAt(idx + 2));
		return calculate(num1, op, num2);
	}

	public static void solve(int idx, int num1, char op) {

		if (idx >= n) {
			max = Math.max(max, num1);
			return;
		}

		int num2 = Character.getNumericValue(input.charAt(idx));
		char next_op = input.charAt(idx + 1);
		solve(idx + 2, calculate(num1, op, num2), next_op);

		if (idx == n - 1)
			return; // 마직막 숫자라면 탐색 중지.

		next_op = input.charAt(idx + 3);
		num2 = getBracketValue(idx);
		solve(idx + 4, calculate(num1, op, num2), next_op);
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = br.readLine();

		max = Integer.MIN_VALUE;
		input += "$";
		solve(0, 0, '+');

		System.out.println(max);
	}
}