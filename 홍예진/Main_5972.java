import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Way>[] wayList;

	static class Way {
		int to;
		int cowCnt;

		public Way(int to, int cowCnt) {
			super();
			this.to = to;
			this.cowCnt = cowCnt;
		}

	}

	public static void solve() {

		int[] dijkstra = new int[N + 1]; // dijkstra[next] 출발지 1에서 next에 도착하는 가장 빠른 거리
		Arrays.fill(dijkstra, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1];
		dijkstra[1] = 0;
		
		PriorityQueue<Way> pq = new PriorityQueue<>((w1, w2) -> w1.cowCnt - w2.cowCnt);
		pq.add(new Way(1, 0));

		while (!pq.isEmpty()) {
			Way way = pq.poll();
			if (visited[way.to])
				continue;

			visited[way.to] = true;
			// 방문하지 않은 번호가 to인 헛간에 도착한다.
			// 현재 새로 추가된 노드(헛간)을 거쳐서 도착하는 모든 경로를 고려 대상에 추가한다.
			for (Way nextWay : wayList[way.to]) {
				if (dijkstra[way.to] + nextWay.cowCnt < dijkstra[nextWay.to]) {
					dijkstra[nextWay.to] = dijkstra[way.to] + nextWay.cowCnt;
					pq.add(new Way(nextWay.to, dijkstra[nextWay.to]));
				}
			}
		}

		System.out.println(dijkstra[N]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		wayList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++)
			wayList[i] = new ArrayList<Way>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cowCnt = Integer.parseInt(st.nextToken());
			wayList[from].add(new Way(to, cowCnt));
			wayList[to].add(new Way(from, cowCnt));
		}

		solve();
	}
}
