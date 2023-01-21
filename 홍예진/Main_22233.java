import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Set<String> keyword;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		keyword = new HashSet<String>();
		
		// 메모장에 작성된 N개의 키워드.
		// 새로운 글을 작성하면 메모장의 키워드가 지워짐.
		for(int i = 0; i < N ; i++)
			keyword.add(br.readLine());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), ",");
			while(st.hasMoreTokens()) {
				String word = st.nextToken();
				if(keyword.contains(word)) {
					keyword.remove(word);
				}
			}
			System.out.println(keyword.size());
		}
		
	}
}