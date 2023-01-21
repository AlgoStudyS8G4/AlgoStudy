package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1253_좋다 {

	private static int ans = 0;
	private static int n;
	private static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr=  new int[n];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			arr[i] = tmp;
		}
		Arrays.sort(arr);
		for(int i = 0; i < n; i++) {
			twopointer(i);
		}

		System.out.println(ans);
	}

	private static void twopointer(int i) {
		int lt =0;
		int rt = n-1;
		while(lt < rt) {
			if(lt == i) {
				lt ++ ;
				continue;
			}
			if(rt == i ) {
				rt --; 
				continue;
			}
			int tmp = arr[lt]+ arr[rt];
			if(tmp == arr[i]) {
				ans++;
				break;
			}
			else if(tmp > arr[i]) {
				rt--;
			} else {
				lt++;
			}
		}
	}

}
