import java.util.Scanner;

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

        long ans = sum[N - 1] - arr[1] + arr[maxIdx]; // 양 끝에 벌을, 가운데 중 가장 큰 값에 벌통을 놓은 경우.

        for(int i = 2; i < N; i++){
            // 꿀통을 왼쪽 끝에 놓는 경우 = 벌 하나를 오른쪽 끝에 놓는 경우
            // 남은 벌 하나를 i에 놓는 경우
            ans = Math.max(ans, (long)(sum[N] - arr[N] - arr[i]) + (sum[i-1]));
            // 꿀통을 오른쪽 끝에 놓는 경우 = 벌 하나를 왼쪽 끝에 놓는 경우
            // 남은 벌 하나를 i에 놓는 경우
            ans = Math.max(ans, (long)(sum[N] - arr[1] - arr[i]) + (sum[N] - sum[i]));
        }

        System.out.println(ans);
    }
}
