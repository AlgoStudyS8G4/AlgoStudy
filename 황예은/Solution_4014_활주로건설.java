import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {

	static int N, x; // 맵 크기, 경사로 길이
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int res = 0; // 정답
			// 0. 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// ============= 입력 끝

			// < 활주로가 가능한 경우 >
			// - 높이가 다 같을 때
			// - 경사로 가능1 : 높이가 1씩 차이남 && 낮은 쪽 길이가 x 이상
			// - 경사로 가능2 : 분지형인데 거리가 안될 때

//			System.out.println("가로");
			// 1. 가로로 확인
			for (int i = 0; i < map.length; i++) {
				ArrayList<Integer> difPoint = new ArrayList<>(); // 높이가 달라지는 시작 위치들
				boolean isImpossible = false;

				// 1-1. 높이차 확인
				for (int j = 0; j < map.length - 1; j++) {
					// a. 높이차가 없을 때 : 계속 진행
					if (Math.abs(map[i][j] - map[i][j + 1]) == 0) {
						continue;
					}
					// b. 높이가 1 차이일 때
					else if (Math.abs(map[i][j] - map[i][j + 1]) == 1) {
						if (difPoint.size() == 0)
							difPoint.add(0);
						difPoint.add(j + 1);
					}
					// c. 높이차가 1보다 클 때 : 경사로 설치 못함 -> 활주로 못함
					else if (Math.abs(map[i][j] - map[i][j + 1]) > 1) {
						isImpossible = true;
						break;
					}
				}

//				//[DEBUG] 리스트 요소들 출력
//				System.out.println(Arrays.toString(difPoint.toArray()));

				// 1-2. 경사로 설치하기
				// c. 높이차가 1보다 클 때 : 경사로 설치 못함 -> 활주로 불가능
				if (isImpossible)
					continue; // 다음 줄로 넘어가기
				else {
					boolean isPossible = false;
					if (difPoint.size() == 0)
						isPossible = true;
					// b. 높이가 1씩 차이날 때
					else if (difPoint.size() > 0) {
						// - 낮은 쪽 길이가 x 이상
						for (int p = 0; p < difPoint.size(); p++) {
							if (difPoint.get(p) - 1 >= 0) {
								if (p + 1 < difPoint.size()) {
									// - 올라가는 계단형
									if (map[i][difPoint.get(p) - 1] < map[i][difPoint.get(p + 1) - 1]) {
										// 경사로가 들어갈 길이라면
										if (Math.abs(difPoint.get(p) - difPoint.get(p + 1)) >= x)
											isPossible = true;
										else {
											isPossible = false;
											break;
										}
									} else if (map[i][difPoint.get(p) - 1] > map[i][difPoint.get(p + 1) - 1]) {
										if (p + 2 < difPoint.size()) {
											// - 분지형
											if (map[i][difPoint.get(p + 1) - 1] < map[i][difPoint.get(p + 2) - 1]) {
												// 경사로가 들어갈 길이라면(분지 안쪽 길이가 x의 2배여야함. Fig. 10)
												if (Math.abs(difPoint.get(p + 1) - difPoint.get(p + 2)) >= 2 * x)
													isPossible = true;
												else {
													isPossible = false;
													break;
												}
												p++;
											}
											// - 내려가는 계단형
											else {
												if (Math.abs(difPoint.get(p) - difPoint.get(p + 1)) >= x)
													isPossible = true;
												else {
													isPossible = false;
													break;
												}
											}
										}

									}
								}
							}
						}

					}
					if (isPossible)
						res++;
				}

			} // ============= 가로 끝
//			System.out.println("세로");
			// 2. 세로로 확인
			for (int j = 0; j < map.length; j++) {
				ArrayList<Integer> difPoint = new ArrayList<>(); // 높이가 달라지는 시작 위치들
				boolean isImpossible = false;

				// 2-1. 높이차 확인
				for (int i = 0; i < map.length - 1; i++) {
					// a. 높이차가 없을 때 : 계속 진행
					if (Math.abs(map[i][j] - map[i + 1][j]) == 0) {
						continue;
					}
					// b. 높이가 1 차이일 때
					else if (Math.abs(map[i][j] - map[i + 1][j]) == 1) {
						if (difPoint.size() == 0)
							difPoint.add(0);
						difPoint.add(i + 1);
					}
					// c. 높이차가 1보다 클 때 : 경사로 설치 못함 -> 활주로 못함
					else if (Math.abs(map[i][j] - map[i + 1][j]) > 1) {
						isImpossible = true;
						break;
					}
				}

//				//[DEBUG] 리스트 요소들 출력
//				System.out.println(Arrays.toString(difPoint.toArray()));

				// 2-2. 경사로 설치하기
				// c. 높이차가 1보다 클 때 : 경사로 설치 못함 -> 활주로 불가능
				if (isImpossible)
					continue; // 다음 줄로 넘어가기
				else {
					boolean isPossible = false;
					if (difPoint.size() == 0)
						isPossible = true;
					// b. 높이가 1씩 차이날 때
					else if (difPoint.size() > 0) {
						// - 낮은 쪽 길이가 x 이상
						for (int p = 0; p < difPoint.size(); p++) {
							if (difPoint.get(p) - 1 >= 0) {
								if (p + 1 < difPoint.size()) {
									// - 올라가는 계단형
									if (map[difPoint.get(p) - 1][j] < map[difPoint.get(p + 1) - 1][j]) {
										// 경사로가 들어갈 길이라면
										if (Math.abs(difPoint.get(p) - difPoint.get(p + 1)) >= x)
											isPossible = true;
										else {
											isPossible = false;
											break;
										}
									} else if (map[difPoint.get(p) - 1][j] > map[difPoint.get(p + 1) - 1][j]) {
										if (p + 2 < difPoint.size()) {
											// - 분지형
											if (map[difPoint.get(p + 1) - 1][j] < map[difPoint.get(p + 2) - 1][j]) {
												// 경사로가 들어갈 길이라면(분지 안쪽 길이가 x의 2배여야함. Fig. 10)
												if (Math.abs(difPoint.get(p + 1) - difPoint.get(p + 2)) >= 2 * x)
													isPossible = true;
												else {
													isPossible = false;
													break;
												}
												p++;
											}
											// - 내려가는 계단형
											else {
												if (Math.abs(difPoint.get(p) - difPoint.get(p + 1)) >= x)
													isPossible = true;
												else {
													isPossible = false;
													break;
												}
											}
										}

									}
								}
							}
						}

					}
					if (isPossible)
						res++;
				}
			} // ============= 세로 끝
			System.out.println("#" + t + " " + res);
		} // ============== 테케 끝
	}

}
