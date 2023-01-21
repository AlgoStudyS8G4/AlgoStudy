package SWEA_AD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution_5648_원자소멸시뮬레이션{
	static class Atom{
		int c, r, energy, dir;
		public Atom(int c, int r, int dir,int energy) {
			this.c = c;
			this.r = r;
			this.dir = dir;
			this.energy = energy;
		}
	}
	private static Queue<Atom> q = new LinkedList<>();
	private static int[][] map=new int[4001][4001];; 
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static int ans;
	private static int mC;
	private static int mR;
	private static int mLength;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(input.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(input.readLine()); 
			ans = 0;
			mC = Integer.MIN_VALUE;
			mR =Integer.MIN_VALUE;
			for(int i= 0; i < N;i++) {
				StringTokenizer st = new StringTokenizer(input.readLine());
				int c = Integer.parseInt(st.nextToken())*2+2000; //x좌표값
				int r = Integer.parseInt(st.nextToken())*2+2000; //y좌표값
				r = Math.abs(r-4000);
				mC = Math.max(mC, c);
				mR = Math.max(mR, r);
				Atom atom = new Atom(c, r, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				map[atom.r][atom.c] = atom.energy;
				q.add(atom);
			}
			mLength = Math.max(mC, mR);
			bfs();
			System.out.println("#" + tc + " " + ans);
		}//end of tc
	}

	private static void bfs() {
		while(mLength-- > 0) {
			int size = q.size();
			for(int i = 0; i < size; i++) {
				Atom atom = q.poll();
				if(map[atom.r][atom.c] != atom.energy) {
					map[atom.r][atom.c] = 0;
					ans += atom.energy;
					continue;
				}
				int nr = atom.r + dr[atom.dir];
				int nc = atom.c + dc[atom.dir];
				move(nr, nc, atom);
				
			}//end of depth
		}
	}

	private static void move(int nr, int nc, Atom atom) {
		map[atom.r][atom.c] -= atom.energy;
		if(isIn(nr, nc)) {
			map[nr][nc] += atom.energy; 
			atom.r = nr;
			atom.c = nc; 
			q.add(atom);
		}
	}

	private static boolean isIn(int nr, int nc) {
		if(nr < 0 || nr > mR || nc < 0 || nc > mC) return false;
		return true;
	}
}
