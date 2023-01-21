import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1949 {
	private static int N, K;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {-1, 0, 1, 0};
	private static int[][] map;
	private static boolean[][] vis;
	private static ArrayList<int[]>  arr;
	private static int ans;
	private static int tmpMaxL;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //
			K = Integer.parseInt(st.nextToken()); //최대 공사 가능 깊이
			map = new int[N][N];
			arr = new ArrayList<>();
			int m = 0 ;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(m < map[i][j]) m = map[i][j];
				}
			}//입력 완료
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N ; j ++) {
					if(map[i][j] == m) arr.add(new int[] {i, j});
				}
			}
			ans = 0;
			
			for(int i = 0; i < arr.size(); i++) {
				tmpMaxL = 1;
				vis = new boolean[N][N];
				vis[arr.get(i)[0]][arr.get(i)[1]] = true;
				DFS(arr.get(i)[0],arr.get(i)[1], true);
			}
			System.out.println("#" + tc+ " " + ans);
		}	
	}

	private static void DFS(int x, int y, boolean chance) {
//		if() {
//			return;
//		}
		for(int i = 0; i < 4; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isIn(nx, ny) && !vis[nx][ny]) {
				if(map[nx][ny] < map[x][y]) {
					vis[nx][ny] = true;
					tmpMaxL++;
					DFS(nx, ny, chance);
					tmpMaxL--;
					vis[nx][ny] = false;
				}else if((map[nx][ny] - map[x][y] < K) && chance){
					int tmp = map[nx][ny];
					map[nx][ny] = map[x][y] -1 ;
					vis[nx][ny] = true;
					tmpMaxL++;
					DFS(nx, ny, false);
					tmpMaxL--;
					vis[nx][ny] = false;
					map[nx][ny] = tmp;
				}
			}
		}
		ans = Math.max(ans, tmpMaxL);
		return;
	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < N  && ny >= 0 && ny <N;
	}
}
