package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_22233_가희와키워드 {

	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//키워드 수
		M = Integer.parseInt(st.nextToken());//블로그에 쓴 키워드 수
		//SET 자료구조를 이용해서 키워드를 담은 다음에 
		//블로그에 해당 키워드를 쓸 때, 그 키워드를 삭제한다
		Set<String> s = new HashSet<>();
		for(int i =0; i < N ; i++) {
			s.add(br.readLine());
		}
		for(int i = 0; i < M; i++) {
			String[] sArr = br.readLine().split(",");
			for(int j = 0; j < sArr.length; j++) {
				if(s.contains(sArr[j])) {
					N--;
					s.remove(sArr[j]);
				}
			}
			System.out.println(N);
		}

	}

}
