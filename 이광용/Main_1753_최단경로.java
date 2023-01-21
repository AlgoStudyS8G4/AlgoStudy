package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
	static class Node implements Comparable<Node>{
		int to;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight; //오름차순
		}
	}

	private static ArrayList<Node>[]  list;
	private static int V, E, K;
	private static int[] dis;
	static final int INF = Integer.MAX_VALUE; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());//정점 수
		E = Integer.parseInt(st.nextToken());//간선 수
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());//시작 정점
		
		//연결리스트 : ArrayList를 원소로하는 배열 생성
		list = new ArrayList[V+1];
		for(int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		dis = new int[V+1];//최소 거리 배열
		Arrays.fill(dis, INF);//INF로 초기화
		
		
		for(int i =0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end, w));//start정점에 연결된 정점을 각 정점의 ArrayList에 삽입한다.
		}
		
		dijkstra(K);
		
		
		for(int i = 1; i <= V; i++) {
			if(dis[i] == INF) System.out.println("INF");
			else System.out.println(dis[i]);
		}
		
	}

	private static void dijkstra(int start) {
		dis[start] = 0;
		boolean vis[] = new boolean[V+1];
//		주의: vis[start] = true; => 여기서 시작점을 먼저 체크하면 안됨
		//pQ는 Node를 원소로 하는데 list에서의 Node와 달리 여기서 Node는
		//"start"에서 to정점까지 가는 weight 비용을 의미한다. 
		PriorityQueue<Node> pQ = new PriorityQueue<>();
		pQ.add(new Node(start, 0));
		
		while(!pQ.isEmpty()) {
			//step1. 출발점으로 부터 가장 거리가 짧은 정점 구하기
			//"start"에서 to정점까지 가는 weight비용이 가장 작은 node를 꺼낸다. 
			Node node = pQ.poll();
			int cur = node.to;

			//step2. 방문 체크 
			if(vis[cur]) continue; //이미 방문했던 정점이라면 pass
			vis[cur] = true;//방문체크

			//step3. 뽑힌 점을 연결점으로 갈 수 있는 정점들의 거리 고려하기
			for(Node tmp : list[cur]) {
				//해당 정점과 연결된 정점들 고려해서 dis배열 갱신하기
				//start에서 tmp 정점까지 가는 기존의 거리 > cur정점까지 오는 거리 + cur에서 tmp 정점으로 가는  비용
				if(!vis[tmp.to] && dis[tmp.to] > dis[cur] + tmp.weight) {
					dis[tmp.to] = dis[cur] + tmp.weight;
					pQ.add(new Node(tmp.to, dis[tmp.to]));
				}
			}
			
		}
	}
}
