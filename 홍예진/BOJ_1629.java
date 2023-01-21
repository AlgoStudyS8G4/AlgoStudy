import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());

		System.out.println(getPow(a, b, c));
	}

	public static long getPow(long a, long b, long c) {
		if (b == 0) {
			return 1;
		}
		if (b % 2 == 0) {
			long half = getPow(a, b/2, c);
			return half*half%c;
		} else {
			return getPow(a, (b - 1), c) * (a % c) % c;
		}
	}
}
