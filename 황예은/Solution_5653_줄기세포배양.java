package SWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5653_줄기세포배양 {

	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static int N, M, K; // 세로크기, 가로크기, 배양 시간
	static int[][] map;
	static int[][] timer;
	static eStatus[][] status;

	// 초기세팅, 세포가 새로 생긴 자리인지, 비활성화, 활성화, 죽은 세포
	static enum eStatus {
		idle, newSepo, notActivate, activate, die
	};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int res = 0; // 살아있는 줄기세포 개수 = 비활성상태+활성상태

			// 0. 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// <POINT!> K 시간 후까지만 알면 되니까
			map = new int[N + K * 2][M + K * 2];
			timer = new int[N + K * 2][M + K * 2];
			status = new eStatus[N + K * 2][M + K * 2];

			for (int i = 0; i < status.length; i++) {
				for (int j = 0; j < status[i].length; j++) {
					status[i][j] = eStatus.idle;
				}
			}
			// <POINT!> 입력 크기만큼만 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					// 현재 존재하는 세포 위치
					int sepoI = i + map.length / 2 - 1;
					int sepoJ = j + map[i].length / 2 - 1;

					// <POINT!> 가운데부터 세포증식
					map[sepoI][sepoJ] = Integer.parseInt(st.nextToken());
					timer[sepoI][sepoJ] = map[sepoI][sepoJ];
					if (map[sepoI][sepoJ] != 0) {
						status[sepoI][sepoJ] = eStatus.notActivate;
						res++;
					}
				}
			}
			// ===== 입력 끝

//			// [DEBUG] 맵
//			for (int i = 0; i < map.length; i++) {
//				for (int j = 0; j < map[i].length; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			// [LOGIC]
			// <POINT!> k시간 후 .. K시간 후까지
			for (int k = 1; k <= K; k++) {
//				// [DEBUG] 시간체크
//				System.out.println(k + "시간 뒤:");
				// 맵 돌기
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						int hp = map[i][j]; // 생명력 수치

						switch (status[i][j]) {
						case notActivate: // 활성화 아직 X
							// activate 타이머 돌리기
							timer[i][j]--;
							if (timer[i][j] == 0) {
								status[i][j] = eStatus.activate;
								timer[i][j] = map[i][j]; // die 타이머 활성화
							}
							break;
						case activate: // 활성화 O
							// die 타이머 돌리기
							timer[i][j]--;
							// die 타이머 종료시 사망
							if (timer[i][j] == 0) {
								// 죽었으면 살아있는 줄기세포수 감소
								status[i][j] = eStatus.die;
								res--;
							}
							// 활성화 O && 타이머가 1시간 돌았을 때 => 주변으로 번식
							if (timer[i][j] == map[i][j] - 1) {
								for (int d = 0; d < deltas.length; d++) {
									int ni = i + deltas[d][0];
									int nj = j + deltas[d][1];
									if (ni >= 0 && nj >= 0 && ni < map.length && nj < map[i].length) {
										// 빈 공간일 때 번식
										if (status[ni][nj] == eStatus.idle) {
											map[ni][nj] = hp;
											res++;
											status[ni][nj] = eStatus.newSepo;
										}
										// <POINT!> 이번 시간대에 새로 번식한 곳 => 근데 hp가 더 큰 값이 있으면 그걸로 번식
										else if (status[ni][nj] == eStatus.newSepo) {
											// 큰놈으로 덧씌움
											if (map[ni][nj] < hp) {
												map[ni][nj] = hp;
											}
										}
									}
								}
							}
							break;
						} // end of switch
					}
				}

				// 신입 세포 -> notActivate로 전환
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						if (status[i][j] == eStatus.newSepo) {
							status[i][j] = eStatus.notActivate;
							timer[i][j] = map[i][j]; // activate 타이머 활성화
						}
					}
				}
//				// [DEBUG] 맵
//				for (int i = 0; i < map.length; i++) {
//					for (int j = 0; j < map[i].length; j++) {
//						int stat = 0;
//						switch (status[i][j]) {
//						case idle:
//							stat = 0;
//							break;
//						case newSepo:
//							stat = 1;
//							break;
//						case notActivate:
//							stat = 2;
//							break;
//						case activate:
//							stat = 3;
//							break;
//						case die:
//							stat = 4;
//							break;
//						}
//						System.out.print(map[i][j] + "(" + stat + ") ");
//					}
//					System.out.println();
//				} // end of [DEBUG]
			} // end of K timer
			System.out.println("#" + t + " " + res);
		} // end of test case
	}
}