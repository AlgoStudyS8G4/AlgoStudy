package SWEA_AD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2382_미생물격리 {
	private static int N;
	private static int M;
	private static int K;
	private static int[] dx = {0, -1, 1, 0, 0}; //상 하 좌 우
	private static int[] dy = {0, 0, 0, -1, 1};
	private static PriorityQueue<Microbe> pQ;
	private static Microbe[][] map;
	
	public static class Microbe implements Comparable<Microbe>{
		int x;
		int y;
		int num;
		int dir;
		int depth; 
		public Microbe(int x, int y, int num, int dir, int depth) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.depth = depth;
		}
		@Override
		public int compareTo(Microbe o) {
			return o.num - this.num;
		}
		@Override
		public String toString() {
			return "Microbe [x=" + x + ", y=" + y + ", num=" + num + ", dir=" + dir + ", depth=" + depth + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken()); //셀의 개수
			M = Integer.parseInt(st.nextToken()); //격리기간
			K = Integer.parseInt(st.nextToken()); //미생물 군집의 개수 K
			
			map = new Microbe[N][N];
			pQ = new PriorityQueue<>();
			for(int i = 0 ; i<K; i++) {
				st = new StringTokenizer(input.readLine());
				Microbe m = new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
				pQ.add(m);
				map[m.x][m.y] = m; 
			}	
			bfs();
			int ans = 0;
			while(!pQ.isEmpty()) {
				ans += pQ.poll().num;
			}
			System.out.println("#" + tc + " " + ans);
		}//end of tc
	}

	private static void bfs() {
		while(M-- > 0) {
			int size = pQ.size();
			for(int i = 0 ; i < size; i++) {
				Microbe microbe = pQ.poll();
				move(microbe);
			}
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (map[i][j] != null) pQ.add(map[i][j]);
				}
			}
		}
	}

	private static void move(Microbe microbe) {
		//기존 위치의 map 원소가 자기 자신인지, 다른 원소가 이번에 옮겨진 건지 확인한 후 기존 위치 삭제 여부를 결정.
		if(microbe.depth == map[microbe.x][microbe.y].depth) {
			map[microbe.x][microbe.y] = null;
		}
		microbe.depth++;
		int nx = microbe.x + dx[microbe.dir];
		int ny = microbe.y + dy[microbe.dir];
		microbe.x = nx;
		microbe.y = ny;
		//방문 예정인 위치에 약품 구역이라면
		if(nx == 0 || nx == N-1 || ny == 0 || ny == N-1) {
			switch (microbe.dir) {
			case 1:
				microbe.dir = 2;
				break;
			case 2:
				microbe.dir = 1;
				break;
			case 3:
				microbe.dir = 4;
				break;
			case 4:
				microbe.dir = 3;
				break;
			default:
				break;
			}
			microbe.num /= 2;
			if(microbe.num == 0) return;
			map[nx][ny] = microbe;
		}
		//방문 예정인 위치에 다른 군집이 있다면 depth 비교
		else if(map[nx][ny] != null && microbe.depth == map[nx][ny].depth) {
			//그 군집이 이번에 이동한 군집인 경우
			map[nx][ny].num += microbe.num;
		}
		else { //그 군집이 그 전에 이동한 군집이거나 비어있는 위치라면
			map[nx][ny] = microbe;
		}
	}
}
