import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			Set<String> set = new HashSet();
			for(int i = 0;i < N; i++) {
				set.add(st.nextToken());
			}
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M ; i++) {
				if(set.contains(st.nextToken()))
					ans++;
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
}