package SWEA_AD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페 {

	private static int[][] map;
	private static int N, ans, si, sj, tmpCnt;
	private static boolean[][] vis;
	private static boolean[] kind;
	private static int[] dx = {-1, 1, -1, 1 } ;
	private static int[] dy = {1, 1, -1, -1} ;
	private static boolean[] dirCheck;

	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc ++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i = 0; i<N ; i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for( int j = 0 ; j < N; j++ ) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} //입력 완료
			ans = 0; //디저트 종류
			
			for(int i = 0 ; i < N ; i ++ ) {
				//각 시작점마다 dfs 돌리기
				for( int j = 0 ; j < N; j++ ) {
					tmpCnt = 0;
					dirCheck = new boolean[4];
					vis = new boolean[N][N];
					kind = new boolean[101];
					si = i;
					sj = j;
					vis[i][j] = true;
					kind[map[i][j]] = true;
					tmpCnt++;
					dfs(i , j, 0, 0);
				}
			}
			if(ans == 0) System.out.println("#"+ tc + " " + -1);
			else System.out.println("#"+ tc + " " + ans);
		}// end of tc
		
	}

	private static void dfs(int i , int j, int depth, int nowDir) {
//		if(depth > 3 && i == si && j == sj) {
//			//한 사이클 완성 
//			// ?? 
//			//ans 갱신
//			
//			return;
//		}
		for(int dir = 0; dir < 4; dir ++) {
			if(dirCheck[dir]) continue;
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			if(depth >= 3 && nx == si && ny == sj && ans < tmpCnt) {
				ans = tmpCnt;
//				if(ans >= 10) {
//					for(int k = 0 ; k < N ; k ++ ) {
//						for( int l = 0 ; l < N; l++ ) {
//							if(vis[k][l] == true) {
//								System.out.print("("+k + "," + l+")" + "[" +map[k][l]+ "]");
//							}
//						}
//						System.out.println();
//					}
//				}
				return;
			}
			if(isin(nx, ny) && !kind[map[nx][ny]] && !vis[nx][ny]) {
				if(depth == 0) nowDir = dir;
				kind[map[nx][ny]] = true;
				tmpCnt++;
				vis[nx][ny] = true;
				if(dir != nowDir) { //방향이 바꼈다면
					dirCheck[nowDir] = true;
				}
 				dfs(nx, ny, depth+1, dir);
 				dirCheck[nowDir] = false;
				kind[map[nx][ny]] = false;
				tmpCnt--;
				vis[nx][ny] = false;
			}
		}
	}

	private static boolean isin(int nx, int ny) {
		
		return nx >= 0 && nx < N  && ny >= 0 && ny < N;
	}	

}
