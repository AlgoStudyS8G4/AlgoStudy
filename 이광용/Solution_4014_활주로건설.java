package SWEA_AD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {

	private static int[][] map;
	private static int N;
	private static int X;
	private static int ANS;
	private static int[][] newMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc =1; tc<= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());//경사로 길이
			map = new int[N][N];
			newMap = new int[N][N];
			for(int i =0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0 ; j <N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//end of 입력
			ANS = 0;
			rowCopy();
			search();
			colCopy();
			search();
			System.out.println("#" + tc + " " + ANS);
		}//end of tc
	}
	private static void search() {
		for(int i =0; i < N; i++) {
			boolean flag = true;
			int cnt = 1;
			for(int j =1; j <N; j++) {
				int cur = newMap[i][j-1];
				int next = newMap[i][j];
				if(cur != next) {
					int diff = cur-next;
					 if(Math.abs(diff) > 1) {
						flag = false;
						break;
					 }else if (diff == -1){
						 if(cnt < X) {//연속적인 같은 높이 갯수가 경사로 길이보다 작다면
							 flag = false;
							 break;
						 }
						 cnt = 1;
					 } else if (diff == 1) {
						 cnt = 0;
						 int k = 0;
						 for(k = j; k < j+X; k++) { // j == 2 / k == 3 // k < 5
							 if(!isIn(i, k)) {
								 flag = false;
								 break;
							 }
							 if(newMap[i][k] != next) {
								 flag = false;
								 break;
							 }
						 }
						 if(!flag) break;
						 j = k-1; //4
					 }
				}else { //연속적인 같은 값
					cnt++;
				}
			}

			if(flag) {
				ANS++;
			}
		}//end of search
		
	}// end of search

	private static boolean isIn(int i, int k) {
		return i >= 0 && i < N && k >= 0 && k < N;
	}
	private static void rowCopy() {
		for(int i =0; i < N; i++) {
			for(int j =0 ; j <N ; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
	private static void colCopy() {
		for(int i =0; i < N; i++) {
			for(int j =0 ; j <N ; j++) {
				newMap[i][j] = map[j][i];
			}
		}
	}

}
