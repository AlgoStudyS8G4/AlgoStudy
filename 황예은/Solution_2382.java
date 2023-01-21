package SWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2382 {

	static int N, M, K; // 셀 개수, 격리 시간, 군집 개수
	static int[][] map; // 군집 위치
	static int[] micNum; // 군집당 미생물 수
	static eDir[] dir; // 군집당 방향

	static enum eDir {
		up, down, left, right
	};

	static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int res = 0; // 정답 = M시간 후 남아있는 미생물의 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			micNum = new int[K + 1];
			dir = new eDir[K + 1];

			for (int k = 1; k <= K; k++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()); // 세로 위치
				int j = Integer.parseInt(st.nextToken()); // 가로 위치
				int num = Integer.parseInt(st.nextToken()); // 미생물 수
				int direc = Integer.parseInt(st.nextToken()); // 이동 방향

				map[i][j] = k; // 군집의 위치 할당
				micNum[k] = num; // k번째 군집의 미생물 수 할당
				// k 번째 군집의 이동 방향 할당
				switch (direc) {
				case 1:
					dir[k] = eDir.up;
					break;
				case 2:
					dir[k] = eDir.down;
					break;
				case 3:
					dir[k] = eDir.left;
					break;
				case 4:
					dir[k] = eDir.right;
					break;
				} // end of switch : dir
			} // end of 입력

			// 타이머
			while (M > 0) {
				M--; // 타이머 돌리기

				int[][] newMap = new int[N][N]; // 시간마다 새로 옮길 군집 위치

				// 1시간마다 여러 군집이 한 셀에 모일 때
				int[] tmpMicSum = new int[K + 1]; // 미생물의 합 임시 저장(map 배열 전체 돌고 나서 micNum에 옮겨줄 거임)
				int[][] maxNumK = new int[N][N]; // 대표 군집 번호(미생물 최대수인 군집)

				// map 돌기
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						int k = map[i][j]; // 이주할 군집 번호 (k==0 : 빈 공간)
						if (k > 0) {
							// 2번 조건 : 1시간마다 이동방향으로 이동
							int ni = i, nj = j; // 군집이 이동할 맵 위치
							switch (dir[k]) {
							case up:
								ni = i - 1;
								break;
							case down:
								ni = i + 1;
								break;
							case left:
								nj = j - 1;
								break;
							case right:
								nj = j + 1;
								break;
							default:
								break;
							}

							// 3번 조건 : 빨간 셀 도착시
							if (ni == 0 || nj == 0 || ni == N - 1 || nj == N - 1) {
								// 빨간색에 여러 군집이 모일일 없으므로 걍 옮기기
								micNum[k] /= 2; // 절반 전멸
								// 미생물이 아직 있으면
								if (micNum[k] > 0) {
									newMap[ni][nj] = k;
									RedCellDir(k);
									tmpMicSum[k] = micNum[k]; // 합친거 없어도 배열 돌면 한꺼번에 넘겨줄거라서 여기에도 tmp가 있어야함
								}
								// micNum[k] == 0 : 전멸
							}
							// 그 외 셀 도착 시
							else {
								// 아직 아무도 안 모인 곳이면 그냥 이동
								if (newMap[ni][nj] == 0) {
									maxNumK[ni][nj] = newMap[ni][nj] = k; // 대표 군집 번호도 같이 설정
									tmpMicSum[k] = micNum[k]; // 현재까지 이 셀의 최대 미생물 수
								}
								// 이미 이주한 군집이 있으면 4번 조건
								else if (newMap[ni][nj] > 0) {
									int preMaxK = maxNumK[ni][nj]; // 이전 군집번호
									// 4번 조건 :
									// - 미생물 수가 더 많은 군집을 대표로 삼고
									if (micNum[preMaxK] < micNum[k]) {
										newMap[ni][nj] = k;
										maxNumK[ni][nj] = k; // 새 대표 군집 번호 할당
										tmpMicSum[k] = tmpMicSum[preMaxK]; // 이전 최대 미생물 합들 옮기기
										tmpMicSum[preMaxK] = 0; // 옮겨진 이전의 합친 미생물 수는 리셋
									}
									tmpMicSum[maxNumK[ni][nj]] += micNum[k]; // 새로 이주한 미생물 수 더해줌
								}
							}

						} // end of 군집이라면(k>0)
					}
				} // end of Arrays

				// newMap의 내용 => map으로 옮기기
				for (int i = 0; i < map.length; i++) {
					System.arraycopy(newMap[i], 0, map[i], 0, map[i].length);
				}

				// 합친 미생물수 옮기기
				for (int k = 1; k <= K; k++) {
					micNum[k] = tmpMicSum[k];

				}

//				// [DEBUG]
//				for (int i = 0; i < map.length; i++) {
//					for (int j = 0; j < map.length; j++) {
//						System.out.print(dir[map[i][j]] + " ");
//					}
//					System.out.println();
//				}

			} // end of timer

			// 남아있는 미생물 수 합치기
			for (int k = 1; k <= K; k++) {
				res += micNum[k];
			}

			System.out.println("#" + t + " " + res);
		} // end of Test Case
	}

	// 빨간 셀 도착시 : 미생물 수 반타작, 방향 전환
	static void RedCellDir(int k) {
		// 이동 방향 반대로
		switch (dir[k]) {
		case up:
			dir[k] = eDir.down;
			break;
		case down:
			dir[k] = eDir.up;
			break;
		case left:
			dir[k] = eDir.right;
			break;
		case right:
			dir[k] = eDir.left;
			break;
		default:
			break;
		}
	}
}
