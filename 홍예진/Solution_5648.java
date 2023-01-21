import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static int N, delta[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	public static long ans;
	public static Atom[] atoms;

	public static double isCrashable(int i, int j) {

		Atom atom1 = atoms[i];
		int x1 = atom1.x;
		int y1 = atom1.y;
		int dx1 = delta[atom1.dir][0];
		int dy1 = delta[atom1.dir][1];
		Atom atom2 = atoms[j];
		int x2 = atom2.x;
		int y2 = atom2.y;
		int dx2 = delta[atom2.dir][0];
		int dy2 = delta[atom2.dir][1];

//		System.out.println(atom1);
//		System.out.println(atom2);

		double M = -1;
		if (y1 == y2 && dx1 * dx2 == -1) { // x축에 평행하게 부딪칠때
			M = Math.abs(x1 - x2) / (double) 2;
			if ((x1 + M * dx1 == x2 + M * dx2) && (y1 + M * dy1 == y2 + M * dy2)) { // x축이 일치하는 시간과 y축이 일치하는 시간이 같아야한다.
				return M;
			}
		} else if (x1 == x2 && dy1 * dy2 == -1) { // y축에 평행하게 부딪칠때
			M = Math.abs(y1 - y2) / (double) 2;
			if ((x1 + M * dx1 == x2 + M * dx2) && (y1 + M * dy1 == y2 + M * dy2)) { // x축이 일치하는 시간과 y축이 일치하는 시간이 같아야한다.
				return M;
			}
		} else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) { // 수직으로 만나서 부딪칠때
			 M = Math.abs(y1 - y2);
			if ((x1 + M * dx1 == x2 + M * dx2) && (y1 + M * dy1 == y2 + M * dy2)) { // x축이 일치하는 시간과 y축이 일치하는 시간이 같아야한다.
				return M;
			}
		}

		return -1;
	}

	public static void solve() {
		//
		//
		// 두 값이 모두 일치하는 0보다 큰 time이 존재할 때 언젠가 충돌한다.

		// 1. 모든 원자 두 쌍에 대해서 부딪치는 시간과 그때의 원자쌍을 저장한다.
		// 2. 작은 시간부터 시작하여 두 원자 모두 아직 깨어지지 않았다면 현재시간 깨어진 원자 리스트에 추가한다.
		// 3. 깨진 원자의 에너지를 합산하고 1-3을 반복한다.

		PriorityQueue<CrashPoint> pq = new PriorityQueue<>(((o1, o2) -> o1.M > o2.M ? 1 : -1));
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double M = isCrashable(i, j); // 충돌 가능한 경우 충돌하는 거리를 반환
				if (M > 0) {
					pq.add(new CrashPoint(i, j, M));
				}
			}
		}

		while (!pq.isEmpty()) {
			double time = pq.peek().M;

			// 현재 시간에 터지는 원자 리스트 찾기
			List<Integer> list = new ArrayList<>();
			while (!pq.isEmpty() && pq.peek().M == time) {
				CrashPoint cp = pq.poll();
				if (atoms[cp.i] != null && atoms[cp.j] != null) {
					list.add(cp.i);
					list.add(cp.j);
				}
			}

			for (int idx : list) {
				if (atoms[idx] != null) {
					ans += atoms[idx].energy;
					atoms[idx] = null;
				}
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;
			atoms = new Atom[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int K = Integer.parseInt(st.nextToken());
				atoms[i] = new Atom(x, y, dir, K);
			}

			solve();

			System.out.println("#" + tc + " " + ans);
		}
	}
}

class Atom implements Comparable<Atom> {
	int x, y, dir, energy;

	public Atom(int x, int y, int dir, int energy) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.energy = energy;
	}

	public int compareTo(Atom a) {
		return this.x == a.x ? this.y - a.y : this.y - a.y;
	}

	@Override
	public String toString() {
		return "Atom [x=" + x + ", y=" + y + ", dir=" + dir + ", energy=" + energy + "]";
	}

}

class CrashPoint {
	int i, j;
	double M;

	public CrashPoint(int i, int j, double m) {
		super();
		this.i = i;
		this.j = j;
		M = m;
	}

	@Override
	public String toString() {
		return "CrashPoint [i=" + i + ", j=" + j + ", M=" + M + "]";
	}

}
