package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5972_택배배송 {
	static class Node implements Comparable<Node>{
		int to, weight;
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		public int compareTo(Node o){
			return this.weight - o.weight;
		}
	}

	private static int N, M;
	private static ArrayList<Node>[]  map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i ++) {
			map[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map[s].add(new Node(e, w));			
			map[e].add(new Node(s, w));//양방향
		}
		int[] dist = dijkstra(1);
		System.out.println(dist[N]);

	}

	private static int[] dijkstra(int start) {
		int[] dist = new int[N+1]; //갱신되는 거리 배열
		boolean[] vis = new boolean[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		PriorityQueue<Node> pQ = new PriorityQueue<>();
		pQ.add(new Node(start, 0));
		while (!pQ.isEmpty()) {
			//step1. 출발점에서 갈 수 있는 가장 가까운 거리를 가진 노드를 뽑음
			Node node = pQ.poll();
			int cur = node.to;
//			System.out.println("cur : " + cur);
			//step2. 방문체크
			if(vis[cur]) continue;
			else vis[cur]=true;
			//step3. cur 정점을 연결점으로 고려하여 갈 수 있는 정점들의 최소 거리를 갱신함
			for(Node next : map[cur]) {
				if(!vis[next.to] && dist[next.to] > dist[cur] + next.weight) {
					dist[next.to] = dist[cur] + next.weight;
					pQ.add(new Node(next.to, dist[next.to]));
				}
			}
		}
		return dist;
		
	}
}
