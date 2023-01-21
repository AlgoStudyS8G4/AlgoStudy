package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		long[] arr = new long[N];
		 st = new StringTokenizer(br.readLine());
		for(int i =0; i < N ; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
		long lt = 0;
		long rt = arr[N-1];
		long ans = Long.MAX_VALUE;
		long diff = Long.MAX_VALUE;
		while(lt <= rt) {
			long mid = (lt + rt ) / 2;
//			System.out.println("mid : " + mid);
			long tmp = 0;
			for(int i = 0 ; i < N; i++) {
				if(arr[i] >= mid) {
					tmp += arr[i] - mid; 
				}
			}
//			System.out.println("tmp : " + tmp);
			if(tmp - M >= 0 && tmp - M <= diff) {
				//자른 나무의 총합이 필요한 최소나무길이(M)보다 크고
				//그 둘의 차이가 기존의 최소 차이보다 작다면 ans 갱신함.  
				diff = tmp - M ;
				ans=mid;
			}
			if(tmp > M) { //나무를 너무 많이 잘랐을 경우 -> 전기톱의 위치를 높여야함
				lt = mid + 1;
//				System.out.println("lt  : " + lt);
			}else if(tmp < M) { 
				rt = mid - 1;
//				System.out.println("rt  : " + rt);
			}else { //자른 나무의 총합 == 필요한 최소나무길이(M)
//				System.out.println("들어감");
				break;
			}
		}
		System.out.println(ans);
		
	}

}
