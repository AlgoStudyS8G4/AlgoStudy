package boj.p18352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * boj 18352 특정 거리의 도시 찾기
 * */
public class Main_18352 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer>[] citys = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			citys[i] = new ArrayList<Integer>();
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			citys[a].add(b);// 단방향
		}
		

		boolean[] visited = new boolean[N + 1];
		int[] distance = new int[N + 1];

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;// 출발 정점

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(X);
		
		while(!pq.isEmpty()) {
			int now = pq.poll();//현재 방문 정점
			
			for (int i = 0; i < citys[now].size(); i++) {//현재 정점을 경유지로 삼는 인접한 정점을 방문함
				if(distance[citys[now].get(i)] > distance[now] + 1) {
					distance[citys[now].get(i)] = distance[now] + 1;
					pq.add(citys[now].get(i));
				}
				
			}
			
		}
		
		//System.out.println(Arrays.toString(distance));
		
		for (int i = 1; i <= N; i++) {
			if(distance[i] == K) result.add(i);
		}
		
		if (result.size() == 0)
			System.out.println(-1);
		else {
			Collections.sort(result);
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		}

	}
}
