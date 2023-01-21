import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static long A, B;
	static ArrayList<Integer> primeList;
	static int MAX_LENGTH = 2000000;
	static boolean[] isNotPrime;

	public static boolean isPrime(long num) {
		if(num <= MAX_LENGTH) return !isNotPrime[(int) num];
		for (int prime : primeList) {
			if (num % ((long) prime) == 0)
				return false;
		}
		return true;
	}

	public static void findPrime() {
		isNotPrime = new boolean[MAX_LENGTH + 1];
		isNotPrime[0] = isNotPrime[1] = true;
		for (int i = 2; i <= MAX_LENGTH; i++) {
			if (isNotPrime[i])
				continue;
			
			primeList.add(i);

			for (int j = i + i; j <= MAX_LENGTH; j += i) {
				isNotPrime[j] = true;
			}
		}
	}

	public static boolean isDestiny() {
		long sum = A + B;
		if(sum == 2) return false;
		if (sum % 2 == 0)
			return true; // 골드바흐의 추측.

		sum -= 2;
		return isPrime(sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		primeList = new ArrayList<>();
		findPrime();
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());

			if (isDestiny())
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		System.out.println();
	}
}