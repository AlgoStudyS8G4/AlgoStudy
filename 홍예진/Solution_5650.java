import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, map[][], ans;
	static Point wormhall[];
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] nextDir = { { 0, 1, 2, 3 }, { 2, 0, 3, 1 }, { 2, 3, 1, 0 }, { 1, 3, 0, 2 }, { 3, 2, 0, 1 },
			{ 2, 3, 0, 1 } };

	public static Point getOppositePoint(int i, int j) {
		Point hall = wormhall[map[i][j] - 6];
		return new Point(hall.i - i, hall.j - j);
	}
  
	// i,j에서 출발했을 때 얻을 수 있는 점수
	public static int start(int i, int j, int dir) {
//		System.out.println("시작점 : " + i + ", " + j + " (" + dir + ")");
		int ni = i + delta[dir][0];
		int nj = j + delta[dir][1];
		int nDir = dir;
		int point = 0;

		while (true) {
			// 바깥벽에 튕겨 돌아오거나, 블록에 튕겨 방향이 바뀌는 경우
//			System.out.println(ni + ", " + nj + " (" + nDir + ")");

			if (map[ni][nj] == -1 || (ni == i && nj == j)) {
				return point;
			} else if (map[ni][nj] > 0 && map[ni][nj] < 6) {
				nDir = nextDir[map[ni][nj]][nDir]; // 기존 방향의 반대 방향
				ni += delta[nDir][0];
				nj += delta[nDir][1];
				point++;
			} else if (map[ni][nj] > 5) {
				Point p = getOppositePoint(ni, nj); // 반대편 좌표로 이동한다.
				ni = p.i + delta[nDir][0];
				nj = p.j + delta[nDir][1];
			} else if (map[ni][nj] == 0) { // 2. 일반 평지
				ni += delta[nDir][0];
				nj += delta[nDir][1];
			}
		}
	}

	public static void solve() {
		// 출발 위치와 방향을 임의로 선정가능할 때, 벽이나 블록에 부딪힌 횟수의 최대값
		// = 출발지값은 상관 없음. 블랙홀과 벽을 출발지로 하는 경로가 벽이나, 블랙홀을 만나기까지 가장 큰 값

		// 모든 좌표에 대하여 해당 방향으로 탐색을 시작한다. 해당 방향으로 이미 방문하였다면,좌표에 값을 저장하고 넘어간다.

		// 블록, 웜홀 또는 블랙홀이 있는 위치에서는 출발할 수 없다.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int dir = 0; dir < 4; dir++) {
					if (map[i][j] == 0) {
						ans = Math.max(start(i, j, dir), ans);

					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 2][N + 2];
			ans = 0;
			wormhall = new Point[5]; // 6~10의 웜홀
			for(int i = 0; i < 5; i++)
				wormhall[i] = new Point(0, 0);

			Arrays.fill(map[0], 5);
			Arrays.fill(map[N + 1], 5);

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				map[i][0] = 5;
				map[i][N + 1] = 5;
				for (int j = 1; j <= N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					if (num > 5) {
						
						wormhall[num - 6].i += i;
						wormhall[num - 6].j += j;
					}
				}
			}
			

			solve();

			System.out.print("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}
}

class Point {
	int i, j;
	int dir;

	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public Point(int i, int j, int dir) {
		this.i = i;
		this.j = j;
		this.dir = dir;
	}
  
	@Override
	public String toString() {
		return "Point [i=" + i + ", j=" + j + ", dir=" + dir + "]";
	}
	
}
