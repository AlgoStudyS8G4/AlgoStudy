import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K, W;
	static int[][] map;
	static ArrayList<Point> checkList;
	static boolean[][][] walls;
	static int ans;
	static ArrayList<Point>[] machines;

	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	public static boolean checkTemperature() {
		for (Point check : checkList) {
			if (map[check.i][check.j] < K)
				return false;
		}
		return true;
	}

	public static int[][] getClone() {
		int[][] clone = new int[R][C];
		for (int i = 0; i < R; i++)
			clone[i] = map[i].clone();
		return clone;
	}

	// 오른쪽으로 온풍기가 불때 row, col위치가 벽에 가로막혔는지 확인.
	public static boolean checkRightWalls(int[][] tempMap, int row, int col) {

		// 바로 왼쪽에서 바람이 올때
		if (inBoundary(row, col - 1) && tempMap[row][col - 1] > 0 && !walls[1][row][col - 1])
			return true;

		// 좌측하단에서 바람이 올때
		if (inBoundary(row + 1, col - 1) && tempMap[row + 1][col - 1] > 0 && !walls[0][row + 1][col - 1]
				&& !walls[1][row][col - 1])
			return true;

		// 좌측 상단에서 바람이 올때
		if (inBoundary(row - 1, col - 1) && tempMap[row - 1][col - 1] > 0 && !walls[0][row][col - 1]
				&& !walls[1][row][col - 1])
			return true;

		return false;

	}

	// 왼쪽으로 온풍기가 불때 row, col 위치가 벽에 가로막혔는지 확인
	public static boolean checkLeftWalls(int[][] tempMap, int row, int col) {
		// 왼쪽방향으로 부는 바람이 row, col에 영향을 주려면 공통적으로 오른쪽의 칸 사이에 벽이 없어야한다.
		if (walls[1][row][col])
			return false;

		// 바로 오른쪽에서
		if (inBoundary(row, col + 1) && tempMap[row][col + 1] > 0) {
			System.out.println("오른쪽 벽이 뚫려 있어요.");
			return true;

		}
		// 우측 하단에서 바람이 올때
		if (inBoundary(row + 1, col + 1) && tempMap[row + 1][col + 1] > 0 && !walls[0][row + 1][col + 1]) {
			System.out.println("오른쪽, 오른쪽 칸의 아래 벽이 뚫려있어요");
			return true;
		}
		// 우측 상단에서 바람이 올때
		if (inBoundary(row - 1, col + 1) && tempMap[row - 1][col + 1] > 0 && !walls[0][row][col + 1]) {
			System.out.println("오른쪽, 오른쪽 칸의 위 벽이 뚫려있어요");
			return true;
		}

		return false;

	}

	public static boolean checkUpWalls(int[][] tempMap, int row, int col) {
		if (inBoundary(row + 1, col) && walls[0][row + 1][col])
			return false;

		// 바로 아래에서
		if (tempMap[row + 1][col] > 0)
			return true;

		// 좌측 하단에서 바람이 올때
		if (inBoundary(row + 1, col - 1) && tempMap[row + 1][col - 1] > 0 && !walls[1][row + 1][col - 1])
			return true;

		// 우측 하단에서 바람이 올때
		if (inBoundary(row + 1, col + 1) && tempMap[row + 1][col + 1] > 0 && !walls[1][row + 1][col])
			return true;

		return false;

	}
	
	public static boolean checkDownWalls(int[][] tempMap, int row, int col) {
		if(!inBoundary(row-1, col) || walls[0][row][col]) return false;
		
		// 바로 위에서
		if(tempMap[row-1][col] > 0)
			return true;
		
		// 좌측 상단에서
		if(inBoundary(row-1, col-1) && tempMap[row-1][col-1] > 0 && !walls[1][row -1][col-1])
			return true;
		
		// 우측 상단에서
		if(inBoundary(row-1, col+1) && tempMap[row-1][col+1] > 0 && !walls[1][row-1][col])
			return true;
		
		return false;
	}

	public static void addTemp(int[][] temp) {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] += temp[i][j];
			}
		}
	}

	public static void blow() {
		// 온풍기에서 오른쪽으로 바람이 한 번 나온다.
		for (Point right : machines[1]) {
			int[][] temp = new int[R + 1][C + 1];
			System.out.println("right : " + right.i + " " + right.j);
			if (inBoundary(right.i, right.j + 1))
				temp[right.i][right.j + 1] = 5;
			int[][][] delta = { { { -1, 2 }, { 0, 2 }, { 1, 2 } },
					{ { -2, 3 }, { -1, 3 }, { 0, 3 }, { 1, 3 }, { 2, 3 } },
					{ { -3, 4 }, { -2, 4 }, { -1, 4 }, { 0, 4 }, { 1, 4 }, { 2, 4 }, { 3, 4 } },
					{ { -4, 5 }, { -3, 5 }, { -2, 5 }, { -1, 5 }, { 0, 5 }, { 1, 5 }, { 2, 5 }, { 3, 5 }, { 4, 5 } } };

			for (int warm = 4; warm > 0; warm--) {
				for (int[] d : delta[4 - warm]) {
					int row = right.i + d[0];
					int col = right.j + d[1];
//					System.out.println(row+" "+col);
					if (!inBoundary(row, col))
						continue;

					if (checkRightWalls(temp, row, col)) {
						temp[row][col] = warm;
					}
				}
			}
			addTemp(temp);
		}

		for (Point left : machines[2]) {
			int[][] temp = new int[R + 1][C + 1];
			if (inBoundary(left.i, left.j - 1))
				temp[left.i][left.j - 1] = 5;
			int[][][] delta = { { { -1, -2 }, { 0, -2 }, { 1, -2 } },
					{ { -2, -3 }, { -1, -3 }, { 0, -3 }, { 1, -3 }, { 2, -3 } },
					{ { -3, -4 }, { -2, -4 }, { -1, -4 }, { 0, -4 }, { 1, -4 }, { 2, -4 }, { 3, -4 } },
					{ { -4, -5 }, { -3, -5 }, { -2, -5 }, { -1, -5 }, { 0, -5 }, { 1, -5 }, { 2, -5 }, { 3, -5 },
							{ 4, -5 } } };

			for (int warm = 4; warm > 0; warm--) {
				for (int[] d : delta[4 - warm]) {
					int row = left.i + d[0];
					int col = left.j + d[1];
//					System.out.println(row+" "+col);
					if (!inBoundary(row, col))
						continue;

					if (checkLeftWalls(temp, row, col)) {
						temp[row][col] = warm;
						for (int ti = 1; ti <= R; ti++) {
							for (int tj = 1; tj <= C; tj++) {
								System.out.print(temp[ti][tj] + " ");
							}
							System.out.println();
						}
						System.out.println(ans);

					}

				}
			}
			addTemp(temp);

			System.out.println("=====[Map]=====");
			for (int ti = 1; ti <= R; ti++) {
				for (int tj = 1; tj <= C; tj++) {
					System.out.print(map[ti][tj] + " ");
				}
				System.out.println();
			}
			System.out.println(ans);

		}

		for (Point up : machines[3]) {
			int[][] temp = new int[R + 1][C + 1];
			if (inBoundary(up.i - 1, up.j))
				temp[up.i - 1][up.j] = 5;
			int[][][] delta = { { { -2, -1 }, { -2, 0 }, { -2, 1 } },
					{ { -3, -2 }, { -3, -1 }, { -3, 0 }, { -3, 1 }, { -3, 2 } },
					{ { -4, -3 }, { -4, -2 }, { -4, -1 }, { -4, 0 }, { -4, 1 }, { -4, 2 }, { -4, 3 } },
					{ { -5, -4 }, { -5, -3 }, { -5, -2 }, { -5, -1 }, { -5, 0 }, { -5, 1 }, { -5, 2 }, { -5, 3 },
							{ -5, 4 } } };

			for (int warm = 4; warm > 0; warm--) {
				for (int[] d : delta[4 - warm]) {
					int row = up.i + d[0];
					int col = up.j + d[1];
					if (!inBoundary(row, col))
						continue;

					if (checkUpWalls(temp, row, col)) {
						temp[row][col] = warm;
					}
				}
			}
			addTemp(temp);
		}

		for (Point down : machines[4]) {
			int[][] temp = new int[R + 1][C + 1];
			if (inBoundary(down.i - 1, down.j))
				temp[down.i - 1][down.j] = 5;
			int[][][] delta = { { { 2, -1 }, { 2, 0 }, { 2, 1 } },
					{ { 3, -2 }, { 3, -1 }, { 3, 0 }, { 3, 1 }, { 3, 2 } },
					{ { 4, -3 }, { 4, -2 }, { 4, -1 }, { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 } },
					{ { 5, -4 }, { 5, -3 }, { 5, -2 }, { 5, -1 }, { 5, 0 }, { 5, 1 }, { 5, 2 }, { 5, 3 },
							{ -5, 4 } } };

			for (int warm = 4; warm > 0; warm--) {
				for (int[] d : delta[4 - warm]) {
					int row = down.i + d[0];
					int col = down.j + d[1];
					if (!inBoundary(row, col))
						continue;

					if (checkUpWalls(temp, row, col)) {
						temp[row][col] = warm;
					}
				}
			}
			addTemp(temp);
		}
	}

	public static boolean inBoundary(int i, int j) {
		return 0 < i && 0 < j && i <= R && j <= C;
	}

        public static void adjust(){}

        public static void decrease(){}
	
	public static void eatChocolate(){}
	
	public static void solve() {
		blow();
		// adjust();
		// decrease();
		// eatChocolate();
		if (checkTemperature())
			return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		checkList = new ArrayList<>();
		machines = new ArrayList[5];

		for (int i = 1; i < 5; i++)
			machines[i] = new ArrayList();

		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 5) {
					checkList.add(new Point(i, j));
					map[i][j] = 0;
				} else if (num > 0) {
					map[i][j] = 0;
					machines[num].add(new Point(i, j));
				}
			}
		}

		W = Integer.parseInt(br.readLine());
		walls = new boolean[2][R + 1][C + 1];
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			walls[t][x][y] = true;
		}

		ans = 0;
		solve();
		for (int ti = 1; ti <= R; ti++) {
			for (int tj = 1; tj <= C; tj++) {
				System.out.print(map[ti][tj] + " ");
			}
			System.out.println();
		}
		System.out.println(ans);
	}
}
