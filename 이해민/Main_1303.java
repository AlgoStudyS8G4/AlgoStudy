package boj.p1303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1303 전쟁 - 전투
 * 출력 : 당신의 병사의 위력의 합과 적국의 병사의 위력의 합
 */
public class Main_1303 {
	
	static int N;
	static int M;
	private static char[][] maps;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maps = new char[M][N];
		
		for (int i = 0; i < M; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				maps[i][j] = tmp.charAt(j);
			}
		}
		
		boolean[][] blueVisited = new boolean[M][N];
		boolean[][] whiteVisited = new boolean[M][N];
		
		int blueResult = 0;//파란색 병사 위력의 합
		int whiteResult = 0;//하얀색 병사 위력의 합

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(maps[i][j] == 'B' && !blueVisited[i][j]) {
					int cnt = solve(blueVisited, i, j, 'B');
					blueResult += cnt * cnt;
				}else if(maps[i][j] == 'W' && !whiteVisited[i][j]) {
					int cnt = solve(whiteVisited, i, j, 'W');
					whiteResult += cnt * cnt;
				}
			}
		}
		
		System.out.println(whiteResult + " " + blueResult);
	}
	
	static int[][] dir = {{-1, 0},{1, 0},{0, -1},{0, 1}};
	
	
	/**
	 * @param visited
	 * @param startX
	 * @param startY
	 * @param checkSolider B인지 W인지 확인
	 */
	static int solve(boolean[][] visited, int startX, int startY, char checkSolider) {
		int cnt = 1;
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {startX, startY});
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int nowX = tmp[0];
			int nowY = tmp[1];
						
			for (int i = 0; i < dir.length; i++) {
				int nextX = nowX + dir[i][0];
				int nextY = nowY + dir[i][1];
				
				if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || visited[nextX][nextY]) continue;
				
				if(checkSolider == maps[nextX][nextY]) {
					cnt++;
					visited[nextX][nextY] = true;
					queue.add(new int[] {nextX, nextY});
				}
			}
		}
		return cnt;
	}
	
}
