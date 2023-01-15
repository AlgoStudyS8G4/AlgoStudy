import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[] getTree(int n, int now, boolean[][] edge) {
		// 임의의 도시 now에서 출발하여 도착할 수 있는 지점을 모두 저장한다.
		int[] tree = new int[n + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(now);
		boolean[] visited = new boolean[n + 1];
		visited[now] = true;

		while (!q.isEmpty()) {
			int city = q.poll();
			tree[city] = 1;
			for (int i = 1; i <= n; i++) {
				if (edge[city][i] && !visited[i]) {
					visited[i] = true;
					q.add(i);
				}
			}

		}
		return tree;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		boolean[][] edge = new boolean[n + 1][n + 1];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int c = Integer.parseInt(st.nextToken());
				if (c == 1)
					edge[i + 1][j + 1] = true;
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int now = Integer.parseInt(st.nextToken());
		// 임의의 도시에서 출발하여 도착할 수 있는 지점 저장
		int[] tree = getTree(n, now, edge);

		for (int i = 1; i < m; i++) {
			int next = Integer.parseInt(st.nextToken());
			if(tree[next] == 0) {
				System.out.println("NO");
				return;
			}
			now = next;
		}
		System.out.println("YES");
	}

}