import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Test {
	static int N, D;
	static ArrayList<Edge>[] list;

	static class Edge {
		int idx;
		int length;

		public Edge(int idx, int length) {
			super();
			this.idx = idx;
			this.length = length;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		list = new ArrayList[D];
		for (int i = 0; i < D; i++) {
			list[i] = new ArrayList<>();
			list[i].add(new Edge(i+1, 1));
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			if (end > D)
				continue;
			if (end - start <= length)
				continue;
			list[start].add(new Edge(end, length));
		}

		int[] dijkstra = new int[D + 1];
		Arrays.fill(dijkstra, Integer.MAX_VALUE);
		boolean[] visited = new boolean[D+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.length - e2.length);
		pq.add(new Edge(0, 0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(e.idx == D) {
				System.out.println(e.length);
				return;
			}
			if(visited[e.idx]) continue;
			
			visited[e.idx] = true;
			dijkstra[e.idx] = e.length;
			
			for(Edge next : list[e.idx]) {
				if(dijkstra[e.idx] + next.length < dijkstra[next.idx])
					pq.add(new Edge(next.idx, dijkstra[e.idx] + next.length));
			}
		}
		
		System.out.println(dijkstra[150]);
	}
}
