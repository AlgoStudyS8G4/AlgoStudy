package boj.p14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * boj 14888 연산자 끼워넣기
 * */
public class Main_14888 {
	static int max,min;
	static int[] nums;
	static int N;
	static int[] operation;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		operation = new int[4];//덧셈, 뺄셈, 곱셈, 나눗셈 개수
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operation[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0, new int[N-1]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	/*
	 * 연산자 조합을 만든다
	 * 
	 * */
	static void combination(int cnt, int[] output) {
		if(cnt == N-1) {
			int tmp = solve(output);
			
			max = Math.max(max, tmp);
			min = Math.min(min, tmp);
			return;
		}
		
		for (int i = 0; i < operation.length; i++) {
			if(operation[i] > 0) {
				operation[i]--;
				output[cnt] = i;
				combination(cnt+1, output);
				operation[i]++;	
			}
		}
	}
	

	static int solve(int[] output) {
		int sum = nums[0];
		
		for (int i = 0; i < output.length; i++) {
			if(output[i] == 0) {//0은 덧셈(+)
				sum += nums[i+1];
			}else if(output[i] == 1) {//1은 뺄셈(-)
				sum -= nums[i+1];
			}else if(output[i] == 2) {//2는 곱셈(×)
				sum *= nums[i+1];
			}else {//3은 나눗셈(÷)
				sum /= nums[i+1];
			}
		}
		
		return sum;
	}
	
	
}
