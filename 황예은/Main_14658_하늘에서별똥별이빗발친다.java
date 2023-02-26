package N14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_14658_하늘에서별똥별이빗발친다 {
	static class Star {
		int x, y;

		public Star(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, L, K;
	static ArrayList<Star> stars;
	static int sx, sy, mx, my; // 별똥별의 가장 작은 위치, 가장 큰 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 구역의 가로 길이
		M = Integer.parseInt(st.nextToken()); // 세로 길이
		L = Integer.parseInt(st.nextToken()); // 트램펄린의 한 변의 길이
		K = Integer.parseInt(st.nextToken()); // 별똥별의 수

		stars = new ArrayList<>();

		// 별똥별이 떨어지는 위치 좌표(x, y)
		sx = Integer.MAX_VALUE;
		sy = Integer.MAX_VALUE;

		mx = Integer.MIN_VALUE;
		my = Integer.MIN_VALUE;

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stars.add(new Star(x, y));

			sx = Math.min(sx, x);
			sy = Math.min(sy, y);

			mx = Math.max(mx, x);
			my = Math.max(my, y);
		}

		int maxCnt = Integer.MIN_VALUE;
		for (int k = sy; k <= my - 4; k++) {
			for (int i = sx; i <= mx - 4; i++) {
				int cnt = 0; // 트램펄린에 떨어진 개수
				int tx = i + 4; // 트램펄린 가로 끝 길이
				int ty = k + 4; // 트램펄린 세로 끝 길이
				for (int j = 0; j < stars.size(); j++) {
					Star s = stars.get(j);
					if (s.x >= i && s.x <= tx) {
						if (s.y >= k && s.y <= ty) {
							cnt++;
						}
					}
				}
				if (maxCnt < cnt)
					maxCnt = cnt;
			}
		}

		System.out.println(K - maxCnt);
	}

}
