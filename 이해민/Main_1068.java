package boj.p1068;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1068 {
	private static LinkedList<LinkedList<Integer>> tree;
	private static int N;
	private static int result;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		result = 0;
		int removeNode = 0;
		int rootIdx = 0;
		
		tree = new LinkedList<LinkedList<Integer>>();
		
		for (int i = 0; i < N; i++) {
			tree.add(new LinkedList<Integer>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int data = Integer.parseInt(st.nextToken());
			if(data == -1) {
				rootIdx = i;
				continue;
			}
			tree.get(data).add(i);
		}
		
		
		removeNode = Integer.parseInt(br.readLine());
	
		
		if(removeNode != rootIdx) {//루트 노드를 삭제하므로 리프 노드는 전부 0
			remove(removeNode);
			getCnt(rootIdx);
		}
		
		
		System.out.println(result);		
		
		
	}

	static void remove(int root) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < tree.get(i).size(); j++) {
				if(tree.get(i).get(j) == root) {//노드를 지움
					tree.get(i).remove(tree.get(i).get(j));
				}
			}
		}
	}
	
	static void getCnt(int now) {
		int size = tree.get(now).size();
		if(size == 0) {
			result++;
			return;
		}
		
		for (int i = 0; i < size; i++) {
			getCnt(tree.get(now).get(i));
		}
	}
	
}
