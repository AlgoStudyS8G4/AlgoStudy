package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4963_섬의개수 {

	private static int[][] map;
	private static boolean[][] vis;
	private static int r;
	private static int c;
	private static int[] dx = {0, 1, -1, 0, -1, 1, 1, -1};
	private static int[] dy = {1, 0, 0, -1, 1, 1, -1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			map = new int[r][c];
			if(r == 0 && c == 0) break;
			for(int i= 0 ; i < r; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < c; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			vis = new boolean[r][c];
			int ans = 0;
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					if(map[i][j] == 1 && !vis[i][j]) {
						vis[i][j] = true;
						ans++;
						bfs(i , j );
					}
				}
			}
			System.out.println(ans);
		}

	}

	private static void bfs(int i , int j) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{i, j});
		while(!q.isEmpty()) {
			int[] point= q.poll();
			int x = point[0];
			int y = point[1];
			for(int d = 0; d < 8; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(isIn(nx,ny) && map[nx][ny] == 1 && !vis[nx][ny]) {
					vis[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
	}

	private static boolean isIn(int nx, int ny) {
		// TODO Auto-generated method stub
		return nx >= 0 && nx < r && ny >= 0 && ny < c;
	}

}
