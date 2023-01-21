package SWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 4008. 숫자만들기
// 연산자의 우선 순위는 고려하지 않고 왼쪽에서 오른쪽으로 차례대로 계산
// 결과 = 최대 - 최소
// 완탐

public class Solution_4008_숫자만들기 {

	static int N; // 숫자의 개수
	static int[] opersN; // 수식(+, -, *, /)의 각 개수
	static char[] opers; // +, -, *, /
	static int[] nums; // 계산할 N개의 숫자들
	static boolean[] isSelected;
	static int min;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int res = 0; // 정답
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			// 0. 입력
			N = Integer.parseInt(br.readLine());
			opersN = new int[4];
			nums = new int[N];
			isSelected = new boolean[4];
			int cntOpers = 0; // 연산자의 총 개수
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 0-1. 연산자들의 개수 입력
			for (int i = 0; i < opersN.length; i++) {
				opersN[i] = Integer.parseInt(st.nextToken());
				cntOpers += opersN[i];
			}

			// 0-2. 수식 순서대로 하나씩 배열에 할당
			opers = new char[cntOpers];
			int index = 0;
			for (int i = 0; i < opersN.length; i++) {
				// 수식(+, -, *, /)의 각 개수만큼 반복
				for (int j = 0; j < opersN[i]; j++) {
					switch (i) {
					case 0:
						opers[index] = '+';
						break;
					case 1:
						opers[index] = '-';
						break;
					case 2:
						opers[index] = '*';
						break;
					case 3:
						opers[index] = '/';
						break;
					}
					index++;
				}
			}

//			// [DEBUG]
//			for (int i = 0; i < opers.length; i++) {
//				System.out.print(opers[i] + " ");
//			}
//			if (cntOpers == index) {
//				System.out.println("[DEBUG] 수식 : 문자형으로 할당 완료");
//			}

			st = new StringTokenizer(br.readLine());
			// 0-3. 수식의 사용될 숫자들 입력
			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			// ----- 입력 끝

//			// [DEBUG]
//			System.out.print(nums[0]);
			dfs(0, nums[0], 1);

			// 결과 출력
//			System.out.println(max + " - " + min);
			res = max - min;
			System.out.println("#" + t + " " + res);
		} // ----- 테스트케이스 끝
	}

	static void dfs(int cnt, int cal, int index) {
		if (cnt == opers.length) {
			// [DEBUG]
			System.out.println(" = " + cal);
			if (max < cal)
				max = cal;
			if (min > cal)
				min = cal;
			return;
		}
		for (int i = 0; i < opers.length; i++) {
			if (isSelected[i])
				continue;

			System.out.print(" " + opers[i] + " ");
			switch (opers[i]) {
			case '+':
				cal += nums[index];
				break;
			case '-':
				cal -= nums[index];
				break;
			case '*':
				cal *= nums[index];
				break;
			case '/':
				cal /= nums[index];
				break;
			}
			System.out.print(nums[index]);
			isSelected[i] = true;
			dfs(cnt + 1, cal, index + 1);
			isSelected[i] = false;
		}
	}

}
