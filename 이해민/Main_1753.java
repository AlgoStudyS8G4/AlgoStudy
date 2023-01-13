package boj.p1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * boj 1753 최단경로
 * 다익스트라
 */
public class Main_1753 {
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	private static int V;
	private static ArrayList<ArrayList<Node>> adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList<ArrayList<Node>>();
		
		//list initialize
		for (int i = 0; i <= V; i++) {
			adjList.add(new ArrayList<Node>());
		}
		
		int[] distance = new int[V+1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList.get(u).add(new Node(v, w));
		}
		
		solve(K, distance);
		
		//System.out.println(Arrays.toString(distance));
		for (int i = 1; i <= V; i++) {
			if(K == i) System.out.println(0);
			else if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(distance[i]);
		}
		
	}
	
	static void solve(int start, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, 0));
				
		boolean[] visited = new boolean[V+1];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int nowVertex = n.vertex;
			
			if(visited[nowVertex]) continue;
			visited[nowVertex] = true;
			
			for (Node node : adjList.get(nowVertex)) {
				if(dist[node.vertex] > dist[nowVertex] + node.weight) {
					dist[node.vertex] = dist[nowVertex] + node.weight;
					pq.add(new Node(node.vertex, dist[node.vertex]));
				}
			}
		}
	}
}
