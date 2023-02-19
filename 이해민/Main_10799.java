package boj.p10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * boj 10799 쇠막대기
 *
 */
public class Main_10799 {
	public static void main(String[] args) throws IOException {
		Stack<Integer> stack = new Stack<>();
		int result = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				stack.add(i);
			} else {
				if(stack.pop() == i-1) result += stack.size();
				else result++;
			}
		}

		System.out.println(result);
	}

}
