import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static class Pair {
		int i, j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];
		// 0 1 2 3 에서 좋은 수는 3
		// 0 1 1 2 에서 좋은 수는 1, 1, 2

		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, List<Pair>> map = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if (A[i] == 0)
				continue;
			for (int j = 1; j < i; j++) {
				int sum = A[i] + A[j];
				List<Pair> list = map.getOrDefault(sum, new ArrayList<Pair>());
				list.add(new Pair(i, j));
				map.put(sum, list);
			}
		}

		int ans = 0;
		for(int a : A) {
			ans += map.get(a) == null ? 0 : 1;
		}

		System.out.println(ans);
	}
}