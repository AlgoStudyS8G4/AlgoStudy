```java
package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_23309_철도공사 {
	public static int[] preNodes = new int[1000001]; //인덱스를 "해당 역의 고유번호"로 하고 값으로 "직전 역의 고유번호"를 가진다.   
	public static int[] postNodes = new int[1000001]; ; //인덱스를 "해당 역의 고유번호"로 하고 값으로 "직후 역의 고유번호"를 가진다.
	public static void add(int target, int node) {
		if(target == -1) {
			preNodes[node]= postNodes[node] = node;
			return;
		}
		preNodes[node] = target;//새로 추가된 node의 직전 노드는 target으로 설정함
		postNodes[node] = postNodes[target]; //target이 가리키던 노드를 새로 추가된 node의 직후 노드로 설정함 
		preNodes[postNodes[target]] = node;//target이 가리키고 있던 노드의 직전 노드를 새로 추가된 node로 설정
		postNodes[target] = node; //target이 가리키는 노드는 새로 추가된 노드로 설정함
	}
	public static void delete(int targetNode) {
		//target의 직전 노드가 가리키는 노드를 target이 가리키는 노드로 설정해야함
		postNodes[preNodes[targetNode]] = postNodes[targetNode];
		//target의 직후 노드의 직전 노드는 target의 직전 노드로 설정해야함
		preNodes[postNodes[targetNode]] = preNodes[targetNode];
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int target = -1;
		for(int i = 0; i < n; i ++) {
			int node = Integer.parseInt(st.nextToken());
			add(target, node);
			target = node;
		}
		for(int w = 0; w < m; w++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();

			int targetNode = Integer.parseInt(st.nextToken());
			int newNode = 0;
			if(st.hasMoreTokens()) {
				newNode = Integer.parseInt(st.nextToken());
			}
			
			switch (type) {
			case "BN":
				//고유 번호 i를 가진 역의 다음 역의 고유번호를 출력하고 그 사이에 고육번호 j를 설립한다.
				System.out.println(postNodes[targetNode]);
				add(targetNode, newNode);
				break;
			case "BP":
				System.out.println(preNodes[targetNode]);
				add(preNodes[targetNode], newNode);
				break;
			case "CN":
				//고유 번호 i를 가진 역의 다음 역을 폐쇄하고 그 역의 고유 번호를 출력한다.
				System.out.println(postNodes[targetNode]);
				delete(postNodes[targetNode]);
				break;
			case "CP":
				//고유 번호 i를 가진 역의 이전 역을 폐쇄하고 그 역의 고유 번호를 출력한다.
				System.out.println(preNodes[targetNode]);
				delete(preNodes[targetNode]);
				break;
			}
		}
	}
	
}
