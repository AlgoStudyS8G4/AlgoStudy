import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 내 코드 X. 이후 복습 필요.
public class Main {
	static int H, W;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new int[H][W];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			int n = Integer.parseInt(st.nextToken());
			for(int j = 0; j < n; j++) {
				arr[j][i] =1;
			}
		}
		
		int sum = 0;
		for(int i = 0; i < H; i++) {
			int startIdx = -1; // 벽이 시작하는 지점
			for(int j = 0; j < W; j++) {
				if(arr[i][j] == 1 && startIdx < 0) {
					startIdx = j;
				}
				else if(arr[i][j] == 1 && startIdx >= 0) {
					sum += j - startIdx -1;
					startIdx = j;
				}
			}
		}

		System.out.println(sum);
	}
}
