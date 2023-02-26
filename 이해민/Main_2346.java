package boj.p2346;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2346 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();//{종이에 적혀있는 값, 인덱스} 형태로 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int data = Integer.parseInt(st.nextToken());
			queue.add(new int[] {data, i});
		}

		StringBuilder sb = new StringBuilder();

		
		int[] tmp = queue.poll();
		int now = tmp[0];//종이에 적혀있는 값
		int idx = tmp[1];//현재 인덱스
		
		sb.append(String.format("%d ", idx));

		while(!queue.isEmpty()) {
			if(now > 0) {//양수가 적혀 있을 경우에는 오른쪽으로 이동
				for (int i = 0; i < now-1; i++) {
					int[] data = queue.pollFirst();
					queue.addLast(data);
				}
				tmp = queue.pollFirst();
				now = tmp[0];
				idx = tmp[1]; 
			}else {//음수가 적혀 있을 때는 왼쪽으로 이동
				for (int i = 0; i < Math.abs(now)-1; i++) {
					int[] data = queue.pollLast();
					queue.addFirst(data);
				}
				tmp = queue.pollLast();
				now = tmp[0];
				idx = tmp[1]; 
			}
			sb.append(idx + " ");//터진 풍선 인덱스 저장
			
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append("\n");
		System.out.println(sb);
	}
}
