import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] chrArr = br.readLine().toCharArray();
		int red = 0, blue = 0;
		for (char chr : chrArr) {
			if (chr == 'R')
				red++;
			else
				blue++;
		}

		char left = chrArr[0];
		int leftCount = 0;

		char right = chrArr[chrArr.length - 1];
		int rightCount = 0;

		for (char chr : chrArr) {
			if (left != chr)
				break;
			leftCount++;
		}
		for (int i = chrArr.length - 1; i >= 0; i--) {
			if (right != chrArr[i])
				break;
			rightCount++;
		}

		int ans = Integer.MAX_VALUE;
		// 1. 볼을 왼쪽으로 옮길 때
		if (left == 'R') {
			// 왼쪽 끝에 빨강공이 있고, 빨간 공을 옮긴다.
			ans = Math.min(ans, red - leftCount);
			// 왼쪽 끝에 빨간 공이 있고, 파란 공을 옮긴다.
			ans = Math.min(ans, blue);
		} else {
			// 왼쪽 끝에 파란공이 있고, 파란공을 옮긴다.
			ans = Math.min(ans, blue - leftCount);
			// 왼쪽 끝에 파란 공이 있고, 빨간 공을 옮긴다.
			ans = Math.min(ans, red);
		}

		// 2. 볼을 오른쪽으로 옮길 때
		if (right == 'R') {
			ans = Math.min(ans, red - rightCount);
			ans = Math.min(ans, blue);
		} else {
			ans = Math.min(ans, blue - rightCount);
			ans = Math.min(ans, red);
		}
		
		
		System.out.println(ans);

	}
}
