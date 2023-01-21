import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
//		int[] dp = new int[N + 1]; // dp[i] : 출발지에서 i번째 도착지까지 가는 최적
		int[] dp = new int[D + 1];
		
		PriorityQueue<Byway> pq = new PriorityQueue<>((bw1, bw2) -> bw1.e - bw2.e);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Byway(s, e, c));
		}

		// 각 지점에 대해서
		for (int i = 1; i <= D; i++) {
			dp[i] = i; // 1) 출발지에서 걸어온 경우
			dp[i] = Math.min(dp[i], dp[i - 1] + 1); // 2) 어딘가에서 일반 길로 걸어오는 경우
			// 3) 지름길로 도착하는 경우
			while(!pq.isEmpty() && pq.peek().e == i) {
				Byway bw = pq.poll();
				dp[i] = Math.min(dp[i], dp[bw.s] + bw.c);
			}
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[D]);
	}
}

class Byway {
	int s, e, c;

	public Byway(int s, int e, int c) {
		super();
		this.s = s;
		this.e = e;
		this.c = c;
	}
}
