import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static String S, T;
	public static String reverse(String now) {
		StringBuilder sb = new StringBuilder(now+"B");
		return sb.reverse().toString();
	}
	
	public static boolean isPossible(String str) {
		if(str.length() >= T.length()) return false;
		
		if(T.contains(str)) return true;
		
		if(T.contains(new StringBuilder(str).reverse().toString())) return true;
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		int ans = 0;
		
		Queue<String> q = new LinkedList<>();
		if(isPossible(S))
			q.add(S);
		while(!q.isEmpty()) {
			String now = q.poll();
			String str1 = now+"A";
			String str2 = reverse(now);
			if(T.equals(str1) || T.equals(str2)){
				ans = 1;
				break;
			}
			if(isPossible(str1))
				q.add(str1);
			if(isPossible(str2))
				q.add(str2);
		}
		
		System.out.println(ans);
	}
}