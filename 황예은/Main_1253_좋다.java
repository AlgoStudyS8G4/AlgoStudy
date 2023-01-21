package N1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253_좋다 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int res = 0;
		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			int findNumber = arr[i];
			int sum = 0;
			int s = 0;
			int e = N - 1;
			while (s < e) {
				sum = arr[s] + arr[e];
				if (sum == findNumber) {
					if(i == s) s++;
					else if(e== i) e--;
					else{
						res++;
						break;
					}
				}
				if(arr[s] + arr[e] > findNumber) e--;
				else if(arr[s] + arr[e] < findNumber) s++;
			}
		}
		System.out.println(res);
	}
}
