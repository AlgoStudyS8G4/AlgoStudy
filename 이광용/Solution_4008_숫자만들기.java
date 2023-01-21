package SWEA_AD;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기 {
	private static int N;
	private static int[] arr;
	private static int[] operations;
	private static boolean[] vis;
	private static int[] permArr;
	private static int max;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			
			operations = new int[N-1];//1 2 3 4 순으로 + - * /
			vis = new boolean[N];
			permArr= new int[N-1];
			int idx = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 4; i++) {
				int size = Integer.parseInt(st.nextToken());
				for(int j =0 ; j < size ; j++) {
					operations[idx++] = i+1;
				}
			}
			st = new StringTokenizer(br.readLine(), " ");
			for(int i =0 ; i < N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			perm(0);
			System.out.println("#" + tc + " " + (max - min));
		}//end of testCase
	}

	private static void perm(int cnt) {
		if(cnt == N-1) {
			int tmpRes = check();
			if(tmpRes > max) {
				max = tmpRes;
			}
			if(tmpRes < min) {
				min = tmpRes;
			}
			return;
		}
		int duplication = 0;
		for(int i = 0; i < N-1; i++) {
			if(!vis[i]) {
				if(duplication == operations[i]) continue;
				vis[i]= true;
				permArr[cnt] = operations[i];
				perm(cnt+1);
				vis[i] = false;//원상복귀
				duplication = operations[i]; //해당 cnt위치에서 같은 값이 중복되서 뽑히는 것을 방지하기 위해
				//변수에 저장해두고 조건으로 가지치기
			}
		}
		
	}

	private static int check() {
		int tmpRes = arr[0];
		for(int i = 0; i < N -1; i++) {
			int tmpNum = arr[i+1];
			int tmpOper = permArr[i];
			switch (tmpOper) {
			case 1:
				tmpRes += tmpNum;
				break;
			case 2:
				tmpRes -= tmpNum;
				break;
			case 3:
				tmpRes *= tmpNum;
				break;
			case 4:
				tmpRes /= tmpNum;
				break;
			default:
				break;
			}
		}
		return tmpRes;
				
		
		
	}
}
