package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1238_파티 {

	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo (Node o) {
			return this.weight - o.weight;
		}
	}
	private static int N, M, X;
	private static ArrayList<Node>[]  linked, reverseLinked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//마을의 개수
		M = Integer.parseInt(st.nextToken());//간선
		X = Integer.parseInt(st.nextToken());//파티 장소
		//연결리스트 : ArrayList를 원소로 하는 배열
		linked = new ArrayList[N+1];
		reverseLinked = new ArrayList[N+1];

		
		for(int i = 1; i <= N; i ++ ) {
			linked[i] = new ArrayList<>();
			reverseLinked[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i ++ ) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			linked[s].add(new Node(e,w));
			reverseLinked[e].add(new Node(s,w));
		}
		int[] dist1 = dijkstra(X, linked);
		int[] dist2 = dijkstra(X, reverseLinked);
		
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			ans = Math.max(ans, dist1[i] + dist2[i]);
		}
		System.out.println(ans);
	}
	private static int[] dijkstra(int start,  ArrayList<Node>[] list) {
		//"start"를 출발점으로 하는 각 정점의 최소 거리 배열
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X] = 0;
		boolean vis[] = new boolean[N+1];
		//"start"에서 to정점까지 가는 최소거리를 우선순위큐에 저장
		PriorityQueue<Node> pQ = new PriorityQueue<>(); 
		pQ.add(new Node(start, 0));
		
		while(!pQ.isEmpty()) {
			//step1. 출발점으로부터 거리가 가장 가까운 node 추출
			Node node = pQ.poll();
			int cur = node.to;
			//step2. 방문 체크
			if(vis[cur]) continue;
			else vis[cur] =true;
			//step3 : 뽑힌 점(cur)을 연결점으로 갈 수 있는 정점들의 거리 고려하기
			//새로 뽑은 cur 위치에서 연결된 다음 정점들을 체크
			for(Node next : list[cur]) {
				//cur과 연결된 정점들 고려해서 dis 배열 갱신하고 pQ에 넣기
				if(!vis[next.to] && dist[next.to] > dist[cur] + next.weight) {
					dist[next.to] = dist[cur] + next.weight;
					pQ.add(new Node(next.to, dist[next.to]));
				}
			}
		}
		return dist;
	}
}
