import java.util.Scanner;

// not solved. 24점.
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int max = 0;
		int maxIdx = 0;
		int[] arr = new int[N + 1];
		int[] sum = new int[N + 1]; // accumulateSum[i] : 0~i까지의 누적합
		arr[1] = sc.nextInt();
		sum[1] = arr[1];
		for (int i = 2; i < N; i++) {
			arr[i] = sc.nextInt();
			sum[i] = sum[i - 1] + arr[i];
			if (max < arr[i]) {
				max = arr[i];
				maxIdx = i;
			}
		}
		arr[N] = sc.nextInt();
		sum[N] = sum[N - 1] + arr[N];

		int ans = sum[N - 1] - arr[1] + arr[maxIdx]; // 양 끝에 벌을, 가운데 중 가장 큰 값에 벌통을 놓은 경우.

		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				for (int r = j + 1; r <= N; r++) {
					ans = Math.max(ans, (sum[j-1] - sum[i - 1]) + (sum[r-1] - sum[i - 1] - arr[j]));
					ans = Math.max(ans, (sum[r] - sum[i] - arr[j]) + (sum[r] - sum[j]));
				}
			}
		}

		System.out.println(ans);
	}
}