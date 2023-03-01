import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int A, B, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			while(K-- > 0) {
				if(A < B) {
					int min = A;
					A += min;
					B -= min;
				} else {
					int min = B;
					A -= min;
					B += min;
				}
			}
			
			System.out.println("#"+tc+" "+Math.min(A, B));
		}
	}
}