package boj.p2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2504 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		Stack<Integer> stack = new Stack<>();//인덱스를 저장
		int result = 0, tmp = 1;
		
		//분배 법칙을 통해 계산함
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				stack.push(i);
				tmp *= 2;
			}else if(str.charAt(i) == '[') {
				stack.push(i);
				tmp *= 3;
			}else if(str.charAt(i) == ')') {
				if(stack.isEmpty() || str.charAt(stack.peek()) != '(') {
					result = 0;
					break;
				}else if(str.charAt(i-1) == '(') {//인접한 괄호가 여는괄호인 경우
					result += tmp;//현재까지 누적된 값을 결과값에 더해줌
				}
				tmp /= 2;
				stack.pop();
			}else {//']'인 경우
				if(stack.isEmpty()|| str.charAt(stack.peek()) != '[') {
					result = 0;
					break;
				}else if(str.charAt(i-1) == '[') {
					result += tmp;
				}
				tmp /= 3;
				stack.pop();
			}
			
		}
		
		if(!stack.isEmpty()) result = 0;
		
		System.out.println(result);
	}
}
