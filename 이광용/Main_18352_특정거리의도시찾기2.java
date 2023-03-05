package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import Baekjoon.Main_1446_지름길2.Node;
//dijksta
public class Main_18352_특정거리의도시찾기2 {
	public static class Node implements Comparable<Node>{
		int to, w;
		public Node(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Node o){
			return this.w - o.w;
		}
		
	}
	private static int N, M, K, X;
	private static int[] dist;
	private static ArrayList<Node>[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//도시의 개수
		M = Integer.parseInt(st.nextToken());//도로의 개수
		K = Integer.parseInt(st.nextToken());//거리 정보
		X = Integer.parseInt(st.nextToken());//출발 도시의 번호
		
		arr = new ArrayList[N+1];
		for(int i = 0 ; i <= N ; i++) {
			arr[i] = new ArrayList<Node>();
		}
			
		for(int i = 0; i < M;i ++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = 1;
			arr[s].add(new Node(e, w));
		}
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijkstra(X);
		boolean isAns = false;
		for(int i = 0; i <= N; i ++) {
			if(dist[i] == K) {
				System.out.println(i);
				isAns= true;
			}
		}
		if(!isAns) System.out.println(-1);
	}
	private static void dijkstra(int start) {
		PriorityQueue<Node> pQ = new PriorityQueue<>();
		pQ.offer(new Node(start, 0));
		dist[start] = 0;
		boolean[] vis = new boolean[N+1];
		while (!pQ.isEmpty()) {
			//step1. 출발점에서 갈 수 있는 가장 짧은 거리의 노드를 꺼낸다 
			Node node = pQ.poll();
			int cur = node.to;
//			if(dist[cur] < node.w)
//				continue;
			//step2. 방문 체크
			if(vis[cur]) continue;
			else vis[cur]=true;
			//step3. 현재 정점을 고려했을 때, 최소 거리 갱신
			for(Node next : arr[cur]) {
				if(!vis[next.to] && dist[next.to] > dist[cur] + next.w) {
					dist[next.to] = dist[cur] + next.w;
					pQ.add(new Node(next.to, dist[next.to]));
				}
			}
		}
		
	}

}
