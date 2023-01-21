/**
 * 현재 시점의 큐 기준으로  한 시간에 큐의 모든 cell들 각각 과정을 진행해야하므로 
 * 한 시간마다 큐의 사이즈를 구해서 그 사이즈만큼 큐에서 poll한다.  
 */
package SWEA_AD;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class  Solution_5653_줄기세포배양 {
	private static int N;
	private static int M;
	private static int K;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {1, 0, -1, 0};
	
	private static Queue<Cell> q;
	private static Cell[][] map;
	
	public static class Cell{
		int x, y, life, duration, state;

		public Cell(int x, int y, int life, int state) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.duration = life; //duration이 0이라면 상태 변화
			this.state = state;//1: 비활성 상태 2: 활성상태 3: 죽은상태
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new Cell[800][800];
			q = new LinkedList<>();
			for(int i = 0; i < N; i ++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0 ; j < M; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 0) continue;
					Cell tmpCell = new Cell(i+400, j+400, tmp, 1);
					map[i+400][j+400]= tmpCell; 
					q.add(tmpCell);
				}
			}//입력완료
			BFS();
 			System.out.println("#" + tc + " " + q.size());
		}//end of tc
	}

	private static void BFS() {
		while(!q.isEmpty()) {
			if (K-- == 0) break;
			
			int tmpSize = q.size();
			ArrayList<Cell> list = new ArrayList<>();
			for(int i = 0; i < tmpSize; i++) {
				Cell cell = q.poll();

				//활성 상태이면서 활성 상태가 된 후 첫 1시간
				if(cell.state == 2 && cell.duration == cell.life) {
					search(cell, list);
				}
				
				cell.duration--;
				
				if(cell.duration == 0) { //상태 변화할 시기
					++cell.state;
					if(cell.state == 3) { //죽은 세포가 되면 큐에 안넣고 넘긴다.
						continue;
					}
					//활성상태(cell.state == 2)가 됐으므로 일단 큐에 다시 넣고
					//duration은 다시 life로 초기화 
					
					cell.duration = cell.life;
					q.add(cell);
					
				}
				else { //상태가 변화할 시기가 아닌 비활성 or 활성 세포
					q.add(cell);
				}
			}//end of tmpSize
			for(Cell c : list) q.add(c);	
		}
	}
	private static void search(Cell cell,ArrayList<Cell> list) {
//		System.out.println("들어감?");
		for(int dir = 0; dir < 4; dir++) {
			int nx = cell.x + dx[dir];
			int ny = cell.y + dy[dir];
			if(map[nx][ny] == null) {
				Cell newCell = new Cell(nx, ny, cell.life, 1);
				map[nx][ny] = newCell;
				list.add(newCell);
			}
			//null은 아닌데 이번 턴에서 새롭게 들어간  cell의 위치라면 생명력 비교해서 갱신 삽입
			else if ( list.contains(map[nx][ny]) && (map[nx][ny].life < cell.life) ) {
				Cell newCell = new Cell(nx, ny, cell.life, 1);
				list.remove(map[nx][ny]);//갱신 전 기존의 값은 리스트에서 삭제
				map[nx][ny] = newCell;
				list.add(newCell);
			}
		}//end of 4방 탐색
	}
}