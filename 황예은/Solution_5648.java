//_원자소멸시뮬레이션 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5648{

	// x = j(열), y = i(행)
	static int N; // 원자 수
	static int[][] map; // 원자들 위치
	static Atom[] allAtom;

	static class Atom {
		int n; // 몇 번째 원자인지
		int x, y; // 좌표(열-가로, 행-세로)
		int dir; // 방향
		int k; // 에너지

		Atom(int n, int x, int y, int dir, int k) {
			this.n = n;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	}

	static int[][] deltas = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int res = 0; // 정답 = 방출하는 에너지
			int N = Integer.parseInt(br.readLine()); // 원자 수
			allAtom = new Atom[N + 1]; // 1~N개 + 0번째

			StringTokenizer st = null;
			map = new int[2001][2001];
			int maxRange = Integer.MIN_VALUE;
			// 입력
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) + 1000; // 가로 j
				int y = Integer.parseInt(st.nextToken()) + 1000; // 세로 i
				int dir = Integer.parseInt(st.nextToken()); // 이동 방향
				int k = Integer.parseInt(st.nextToken()); // 에너지

				// 원자 만들어서 allAtom에 넣어주기
				// : 1부터 입력(index 1 = A원자, index 2 = B원자, ..)
				Atom atom = new Atom(n, x, y, dir, k);
				allAtom[n] = atom;

				// 맵에 원자가 존재한다고 표시해주기
				map[y][x] = n;

				// 반복문 돌릴 때 원자가 이동할 리 없는 범위는 최대한 컷
				int maxDis = 0; // atom이 이동할 수 있는 최대 거리
				switch (dir) {
				case 0: // 상
					maxDis = map.length - y;
					break;
				case 1: // 하
					maxDis = y;
					break;
				case 2: // 좌
					maxDis = x;
					break;
				case 3: // 우
					maxDis = map.length - x;
					break;
				}
				maxRange = Integer.max(maxDis, maxRange);

			} // end of input

			// 가장 많이 이동해야하는 원자 기준으로 반복
			while (maxRange >= 0) {
				maxRange--;

				Queue<Integer> bombAtomQ = new LinkedList<>();
				boolean[] isFirst = new boolean[N + 1]; // 해당 좌표에서 처음 폭발하는 원자인지
				for (int i = 1; i < allAtom.length; i++) {
					Atom atom = null;
					if (allAtom[i] != null)
						atom = allAtom[i];
					else
						continue;
					// 이동 후 좌표 : x가 열, y가 행
					int ny = atom.y + deltas[atom.dir][0];
					int nx = atom.x + deltas[atom.dir][1];

					if (ny >= map.length || nx >= map.length || ny < 0 || nx < 0) {
						// 범위를 넘어가면 충돌 없이 소멸
						allAtom[i] = null; // 원자 목록에서 없애기
						continue;
					} else {
						if (map[ny][nx] != 0) {
							if (!isFirst[map[ny][nx]]) {
								isFirst[map[ny][nx]] = true;
								int existN = map[ny][nx]; // 기존에 있던 원자 번호
								bombAtomQ.add(existN); // 기존에 있던 원자 번호를 소멸할 원자 목록에 넣기
							}
						}
						map[ny][nx] = atom.n; // 새 좌표에 원자 넣기
						bombAtomQ.add(atom.n); // 이번 턴의 원자 번호
					}

					// 이동 전 좌표 초기화
					map[atom.y][atom.x] = 0;

					// 원자 정보에 업데이트한 좌표 업데이트
					allAtom[i].x = nx;
					allAtom[i].y = ny;
				} // ----- 원자 다 돌았음

				// 소멸하는 원자들의 방출 에너지 합 구하기
				while (bombAtomQ.size() > 0) {
					int ba = bombAtomQ.peek();
					res += allAtom[ba].k;
					allAtom[ba] = null; // 원자 목록에서 없애기
				}
			}

			System.out.println("#" + t + " " + res);
		} // end of test case

	}
}
