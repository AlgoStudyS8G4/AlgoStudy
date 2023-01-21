package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_14658_하늘에서별똥별이빗발친다 {

	private static int N, M, L, K;
	private static ArrayList<int[]> arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//가로 길이
		M= Integer.parseInt(st.nextToken()); // 세로 길이
		L= Integer.parseInt(st.nextToken());//트램펄린 한변의 길이
		K = Integer.parseInt(st.nextToken()); //별똥별 개수
		arr = new ArrayList<>();
		for(int i = 0; i <K; i ++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());//가로 길이
			int y = Integer.parseInt(st.nextToken());//세로 길이
			arr.add(new int[] {x, y});
		}
		//별똥별이 트램펄린 안에 최대한 많이 들어올 땐,
		//별똥별이 트램펄린 가장자리에 위치해 있는 경우다.
		//따라서 3중 for문을 돌린다. 먼저 별똥별 두 개를 뽑아서
		//각각의 x, y로 트램펄린의 '왼쪽 위' 위치로 설정한다.
		//그리고 해당 트램펄린 범위 안에 몇개의 별똥별이 위치하는지 카운트한다.
		int ans = 0;
		int cnt = 0;
		
		for(int i = 0; i < K ; i++) {
			for(int j = 0; j <K; j ++ ) {
				cnt = 0;
				int x = arr.get(i)[0];
				int y = arr.get(j)[1];
				//두 점에서 직선을 이었을 때, 만나는 한점을 트램펄린 왼쪽 위로 설정함.
				for(int k = 0; k < K; k++ ) {
					if(x <= arr.get(k)[0] && x + L >= arr.get(k)[0] &&
							y <= arr.get(k)[1] && y + L >= arr.get(k)[1])
						cnt++;
				}
				ans = Math.max(ans, cnt);
			}
		}
		System.out.println(K - ans);

	}

}
