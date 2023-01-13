package boj.p1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * boj 1238
 * 출력 : N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력
 * 문제 접근 : 다익스트라
 */
public class Main_1238 {
	private static int N;
	private static int M;//도로 개수
	private static int X;

	static class Node implements Comparable<Node>{
		int vertex;
		int time;
		
		public Node(int vertex, int time) {
			this.vertex = vertex;
			this.time = time;
		}		
		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>();
		ArrayList<ArrayList<Node>> adjReverseList = new ArrayList<ArrayList<Node>>();

		int[] memo = new int[N+1];
		int[] reverseMemo = new int[N+1];
		
		//인접리스트 초기화
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Node>());
			adjReverseList.add(new ArrayList<Node>());			
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adjList.get(s).add(new Node(e, t));
			adjReverseList.get(e).add(new Node(s, t));
		}

		//각각 다른 마을에서 X까지 가는 거리 구함(이때, X를 시작점으로 잡기 위해 간선을 뒤집음)
		solve(X, adjReverseList, reverseMemo);
	
		//X에서 모든 마을까지의 거리 구함
		solve(X, adjList, memo);
		
		//System.out.println(Arrays.toString(memo));
		//System.out.println(Arrays.toString(reverseMemo));
		
		int result = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, memo[i] + reverseMemo[i]);
		}
		
		System.out.println(result);
	}
	
	
	
	static void solve(int start, ArrayList<ArrayList<Node>> adjList, int[] dist) {
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[start] = 0;//시작 정점 방문 처리
		
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int nowVertex = node.vertex;
			
			if(visited[nowVertex]) continue;
			visited[nowVertex] = true;
			
			for (Node n : adjList.get(nowVertex)) {
				if(dist[n.vertex] > dist[nowVertex] + n.time) {
					dist[n.vertex] = dist[nowVertex] + n.time;
					pq.add(new Node(n.vertex, dist[n.vertex]));
				}
			}
		}
		
	}
	
}
