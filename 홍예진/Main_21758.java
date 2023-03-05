import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int middle = 0; // 벌통을 가운데 둘 때, 꿀의 양이 최대가 되려면 필요한 최대값.
		int left = 0; // 벌통을 왼쪽에 둘 때, 꿀의 양이 최대가 되는 값.
		int right = 0; // 벌통을 오른쪽에 둘 때, 꿀의 양이 최대가 되는 값.
		int[] sum = new int[N + 1]; // accumulateSum[i] : 0~i까지의 누적합
		sum[1] = sc.nextInt();
		// 양끝을 제외한 가운데 값.
		for (int i = 2; i < N; i++) {
			int honey = sc.nextInt();
			middle = Math.max(middle, honey);
			sum[i] = sum[i - 1] + honey;
			left = Math.max(sum[i] - 2 * honey, left);
			right = Math.max(-(sum[i] + honey), right);
		}
		sum[N] = sum[N - 1] + sc.nextInt();

		int max = 0;
		// case 1. 벌통을 가운데 둘 때. 벌은 양 옆에 두고 벌통은 최대값위치에 두는 것이 최선.
		max = Math.max(max, sum[N - 1] + middle - sum[1]);
		// case 2. 벝통을 왼쪽 끝에 둘 때,
		max = Math.max(max, sum[N - 1] - 2 * sum[1] + left);
		// cas2 3. 벌통을 오른쪽 끝에 둘 때,
		max = Math.max(max, 2 * sum[N] - sum[1] + right);

		System.out.println(max);
	}
}