import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N, K, arr[];
	static Set<Long>[] numbers;
	static long ans;

	public static void solve() {
		// 어떤 문자를 시작점으로 세개를 골랐을 때 나오는 수 중 K번째 수 찾기.
		// 앞자리가 클 수록 큰 것이 자명하기 때문에, 10번째 수가 나올 수 있는 앞자리 수를 찾는다.
		int count = 0;
		for (int i = 15; i >= 0; i--) {
			if (count + numbers[i].size() >= K) { // i를 앞자리로하는 수가 K번째수를 포함한다면
				PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
				pq.addAll(numbers[i]);
				while (!pq.isEmpty()) {
					long num = pq.poll();
					if (++count == K) {
						ans = num;
						return;
					}
				}
			} else {
				count += numbers[i].size();
			}
		}
	}

	public static int getHexToInt(char chr) {
		if (chr >= 'A' && chr <= 'F')
			return 10 + chr - 'A';
		return Character.getNumericValue(chr);
	}

	public static long getNum(int index) {
		long num = 0;
		long temp = (long) Math.pow(16, N / 4 - 1);
		while (temp > 0) {
			num += arr[(index++) % (N)] * temp;
			temp /= 16;
		}
		return num;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			char[] characters = br.readLine().toCharArray();
			arr = new int[N];

			numbers = new HashSet[16]; // 16진수는 16개의 수 0~9, A~F로 표현가능하다. indexs[i] : 맨 앞이 i로 시작하는 수
			for (int i = 0; i < 16; i++)
				numbers[i] = new HashSet<Long>();

			for (int index = 0; index < N; index++) {
				int num = getHexToInt(characters[index]);
				arr[index] = num;
			}
			

			for (int index = 0; index < N; index++) {
				numbers[arr[index]].add(getNum(index));
			}

			solve();

			System.out.println("#" + tc + " " + ans);
		}
		br.close();
		bw.flush();
		bw.close();
	}

}
