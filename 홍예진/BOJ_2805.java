import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static long getSum(int[] tree, int height) { // 나무를 height높이로 자를 때 나오는 양
		long sum = 0;
		for (int i = 0; i < tree.length; i++) {
			sum += tree[i] > height ? tree[i] - height :0 ;
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] tree = new int[n];
		int highest = 0;
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			highest = tree[i] > highest ? tree[i] : highest;
		}

		int low = 0, high = highest;
		int ans = 0;
		while (1 < high - low) {
			int middle = (low + high) / 2;
			
			long sum = getSum(tree, middle);
			if(sum > m) {
				// 너무 많이 잘랐다. => 높이를 높여본다.
				low = middle;
				ans = middle; // 정답 후보군
			} else if(sum < m) {
				// 아직 부족하다 => 높이를 낮춰본다.
				high = middle;
			} else { // 완벽하게 m길이만 가져간다.
				ans = middle;
				System.out.println(ans);
				return;
			}
		}
		
		System.out.println(ans);
		
	}
}
