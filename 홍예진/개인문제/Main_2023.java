import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		final int MAX = (int) (Math.pow(10, N) - 1); // N자리수의 최대값.
		boolean[] isNotPrime = new boolean[(int) Math.sqrt(MAX) + 1]; // 최댓값의 제곱근까지 소수를 판별한다. 최대 만개.
		// N의 값은 최대 8 = 최대 여덟자리 = 10억보다 작은 수.
		// 어떤 수 K가 소수가 아니라면, Math.sqrt(K)보다 작거나 같은 수를 소인수로 갖는다.

		// 소수를 모아 넣는다.
		ArrayList<String> primeList = new ArrayList<>();

		isNotPrime[0] = true;
		isNotPrime[1] = true;
		for (int i = 2; i < isNotPrime.length; i++) {
			if (isNotPrime[i])
				continue;
			// i가 소수다. i의 배수는 NotPrime 표시한다.
			for (int j = 2 * i; j < isNotPrime.length; j += i) {
				isNotPrime[j] = true;
			}
			primeList.add(String.valueOf(i));
		}

		// 한자리 소수는 2, 3, 5, 7 네가지
		// 뒤에 올 수 있는 수는 홀수.
		Queue<Integer> q = new LinkedList<>();
		q.add(2);
		q.add(3);
		q.add(5);
		q.add(7);
		int qSize = q.size();
		int depth = 1;
		while (!q.isEmpty() && depth < N) {
			while (qSize-- > 0) {
				int num = q.poll();
				for (int i = 1; i < 10; i += 2) {
					int next = num * 10 + i;
					if (isPrime(next, isNotPrime, primeList))
						q.add(next);
				}
			}
			qSize = q.size();
			depth++;
		}

		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			sb.append(q.poll()).append("\n");
		}

		System.out.println(sb.toString().trim());
	}

	public static boolean isAmazingPrime(String num, boolean[] isNotPrime, ArrayList<String> primeList) {
		for (int i = 1; i <= num.length(); i++) {
			int part = Integer.parseInt(num.substring(0, i));
			// 하나라도 소수가 아니면 false
			if (!isPrime(part, isNotPrime, primeList))
				return false;
		}

		return true;
	}

	public static boolean isPrime(int part, boolean[] isNotPrime, ArrayList<String> primeList) {
		// 일부분이 이미 계산된 소수의 범위이며 그것이 소수가 아니라면
		if (part < isNotPrime.length) {
			if (isNotPrime[part])
				return false;
			else
				return true;
		} else {
			// 계산된 범위를 넘어가는 큰 수는 여태 구한 소수리스트로 소수인지를 판정한다.
			int sqrt = (int) Math.sqrt(part);
			// 검사 중인 수의 일부(part)가 part의 제곱근으로보다 작거나 같은 소수로 나눠지는 지 확인.
			for (String primeStr : primeList) {
				int prime = Integer.parseInt(primeStr);
				// 일부(part)의 제곱근보다 작거나 같은 수는 모두 확인했다. part는 소수다. 반복문을 종료한다.
				if (prime > sqrt)
					break;
				// 어떤 소수로 나눠진다. part는 소수가 아니다.
				if (part % prime == 0)
					return false;
			}
			return true;
		}
	}
}
