package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18808_스티커붙이기 {

	private static int[][] map, cMap, stickerMap;
	private static int N, M, K, R, C,r , c;
	private static int tmpK;
	private static int[][] newStickerMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); //스티커 개수
		map = new int[N][M];
		//각 스티커 입력 받으면서 바로 map에 가능한 공간이 있는지 확인
		//그리고 90도 몇번 돌렸는지도 확인해야함
		//각 순번에 따른 다음 모양이 달라짐
		for(int k = 0; k < K; k++) { //스티커 개수
//			tmpK = k;
//			if(tmpK > 1) continue;
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			stickerMap = new int [R][C];
			for(int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < C; j++) {
					stickerMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//해당 스티커 map[][]을 노트북map에 붙일 수 있는지 가능한지 확인
			
//			boolean flag = true;
			r = R;
			c = C;
//			boolean flag = check(0);
			for(int d = 0; d < 4; d++) {
				boolean flag = check(d);
				if(flag) break;
			}
		}
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j= 0; j < M; j++) {
				if(map[i][j] == 1) ans ++;
			}
		}
		System.out.println(ans);
	}

	private static boolean check(int d) {
		switch (d) {
		case 0: //스티컷 맨 처음 모양
			//isIn에서 우선 해당 R C가 노트북에 들어가는지 확인
			//전체 맵을 (위쪽 -> 왼쪽) 우선 순서로 탐색하면서 해당 자리부터 스티커의 (0,0)으로 삼고  
			//tracking()를 호출해서 스티커 크기 만큼 체크를 했을 때, 1을 만나지 않고
			//전체를 체크 한다면 true를 준다.
			newStickerMap = new int[r][c];
			for(int i = 0; i < r; i++) newStickerMap[i] = Arrays.copyOf(stickerMap[i], c);
			return caseCheck(newStickerMap);
		case 1: //90도 회전
			change(r, c); // => r == 5 , c == 2 
			newStickerMap = new int[r][c];
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					newStickerMap[j][R-1-i]= stickerMap[i][j];
				}
			}
//			System.out.println("90도 회전한거 확인하자");
//			for(int i = 0; i < r; i ++) {
//				for(int j = 0; j < c; j ++) {
//					System.out.print(newStickerMap[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			return caseCheck(newStickerMap);
		case 2: //180도 회전
			change(r, c);
			newStickerMap = new int[r][c];
//			System.out.println("r: " + r + " " + c);
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
//					System.out.println("한점 " + (R-1-i) + " " + (C-1-j));
					newStickerMap[R-1-i][C-1-j]= stickerMap[i][j];
				}
			}
			return caseCheck(newStickerMap);
		case 3: //270도 회전
			change(r, c);
			newStickerMap = new int[r][c];
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					newStickerMap[C-1-j][i]= stickerMap[i][j];
				}
			}
			return caseCheck(newStickerMap);
		}
		return false;
	}
	private static boolean caseCheck(int[][] newStickerMap) {
//		System.out.println("시도 "  + tmpK);
		if(isIn()) {
//			System.out.println("isin "  + tmpK);
			for(int i = 0; i < N; i++) {
				for(int j= 0; j < M; j++) {
//					if(map[i][j] == 0) {
						cMap = new int[N][M];
						cMap = copy(map, cMap); //복사 map
						if(tracking(i, j, newStickerMap)) { //i, j가 스티커의 (0,0)에 해당하는 자리 일 때, 가능한지 체크
							map = copy(cMap, map);//거꾸로 map 갱신 -> 해당 스티커 붙임 완료
//							System.out.println(tmpK + "번 스티커");
//							System.out.println("성공했을 때  i , j " + i + " " + j);
//							System.out.println("성공 " + tmpK);
							return true;
						}
//					}
				}
			}
			return false;
		}
		else return false;
		
	}

	private static void change(int tr, int tc) {
		int tmp = tr;
		tr = tc;
		tc = tmp;
		r = tr;
		c = tc;
//		System.out.println("change 함수 r : " + r  + " c : " + c);
	}

	private static int[][] copy(int[][] m1, int[][] m2) {
		for(int i = 0; i < N; i++) {
			m2[i] = Arrays.copyOf(m1[i], M);
		}
		return m2;
	}

	private static boolean tracking(int si, int sj, int[][] tmpSticker) {
		for(int i = 0 ; i < r ; i++) {
			for (int j = 0; j < c; j ++) {
				if(tmpSticker[i][j] == 1) {
					if(isRange(si + i, sj + j)) {
						if(cMap[si + i][sj + j] == 1) {
							//해당 자리에 이미 다른 스티커가 붙여져있는 경우
							return false;
						}else {
							cMap[si + i][sj + j] = 1;
						}
					}
					else
						return false;
				}
			}
		}
		return true;
	}

	private static boolean isRange(int i, int j) {
		return i >= 0  && i < N && j >= 0 && j < M;
	}

	private static boolean isIn() {
		//r과 c 길이가 가능한지 확인
		return r <= N && c <= M;
	}
}
