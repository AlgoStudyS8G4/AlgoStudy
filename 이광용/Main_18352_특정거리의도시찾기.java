package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//bfs
public class Main_18352_특정거리의도시찾기 {

	private static int N, M, K, X;
	private static int[] dist;
	private static ArrayList<Integer>[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//도시의 개수
		M = Integer.parseInt(st.nextToken());//도로의 개수
		K = Integer.parseInt(st.nextToken());//거리 정보
		X = Integer.parseInt(st.nextToken());//출발 도시의 번호
		
		arr = new ArrayList[N+1];
		for(int i = 0 ; i <= N ; i++) {
			arr[i] = new ArrayList<Integer>();
		}
			
		for(int i = 0; i < M;i ++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[s].add(e);
		}
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Queue<Integer> q = new LinkedList<>();
		q.add(X);
		dist[X] = 0; 
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : arr[cur]) {
				if(dist[next] == Integer.MAX_VALUE) {
					dist[next] = dist[cur] + 1;
					q.offer(next);
				}
			}
		}
		boolean isAns = false;
		for(int i = 0; i <= N; i ++) {
			if(dist[i] == K) {
				System.out.println(i);
				isAns= true;
			}
		}
		if(!isAns) System.out.println(-1);
	}

}
