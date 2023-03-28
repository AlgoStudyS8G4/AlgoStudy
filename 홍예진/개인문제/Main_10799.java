import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int total = 0;
		int count = 0;
		for (int i = 0; i < input.length();) {
			if (input.charAt(i) == '(') {
				if (input.charAt(i + 1) == ')') {
					total += count;
					i += 2;
				} else {
					count++;
					i++;
				}
			} else {
				total++;
				count--;
				i++;
			}
		}

		System.out.println(total);
	}
}
