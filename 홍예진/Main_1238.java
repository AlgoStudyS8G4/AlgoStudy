import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X;
	static int[][] time;
	static int[][] cost; // cost[i][0] : i번 학생의 가는데 소요되는 비용, cost[i][1] i번 학생의 오는데 소요된는 비용
	static int ans;

	// from에서 to로 가는 최단 거리를 찾는다.
	// isForward - true : X에 도착하는 방향. false : X에서 출발하는 방향
	public static int[] getDijkstra(boolean isForward) {
		int[] dijkstra = new int[N];
		Arrays.fill(dijkstra, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N];
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.time - n2.time);
		pq.add(new Node(X-1, 0));

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visited[n.num])
				continue;
			visited[n.num] = true;
			dijkstra[n.num] = n.time;
			
//			System.out.println(Arrays.toString(dijkstra));
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			for (int next = 0; next < N; next++) {
				int t = time[n.num][next];
				if(!isForward) t = time[next][n.num];
				
				if (visited[next] || t == Integer.MAX_VALUE)
					continue;
				pq.add(new Node(next, dijkstra[n.num] + t));
			}
		}
		
		return dijkstra;
	}

	// 단방향. N명의 학생들 중 오고가는데 가장 많은 시간을 소비하는 학생?
	// N명의 학생이 각각 N개의 마을에서 X마을까지 이동하고 돌아오는 최단거리
	// 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개
	public static void solve() {
		ans = 0;
		int[] dijkstra = getDijkstra(true);
		int[] reverseDijkstra = getDijkstra(false);
		
		for(int i = 0; i < N; i++)
			ans = Math.max(ans, dijkstra[i] + reverseDijkstra[i]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		cost = new int[N][2];
		time = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(time[i], Integer.MAX_VALUE);
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());
			time[from][to] = t;
		}

		solve();

		System.out.println(ans);
	}

	static class Node {
		int num, time;

		public Node(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}

	}

}
