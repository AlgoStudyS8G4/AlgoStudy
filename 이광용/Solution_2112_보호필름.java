import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2112_보호필름 {

	private static int[][] map;
	private static int[][] cMap;
	private static int D;
	private static int W;
	private static int K;
	private static boolean[] sub1;
	private static boolean[] sub2;
	private static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); //보호 필름의 두께
			W = Integer.parseInt(st.nextToken()); //가로 크기
			K = Integer.parseInt(st.nextToken()); //합격 기준
			map = new int[D][W];
			for(int i = 0; i< D ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] =Integer.parseInt(st.nextToken()); 
				}
			}//입력 완료
			ans = Integer.MAX_VALUE; //약품 최소 투입 횟수
			if (check(map) == false) {
				sub1 = new boolean[D];
				subset(0);				
				System.out.println("#" + tc + " " + ans);
			}else {
				System.out.println("#" + tc + " " + 0);
			}
		}//end of tc

	}

	private static void subset(int cnt) {
		if(cnt == D) {
			ArrayList<Integer> sel = new ArrayList<>();
			for (int i = 0; i < sub1.length; i ++) {
				if (sub1[i] == true) {
					sel.add(i);
				}
			}
			if(sel.size() > ans) return;
			sub2 = new boolean[sel.size()];
			subset2(0, sel);
			return;
		}
		sub1[cnt] = false;
		subset(cnt+1);
		sub1[cnt] = true;
		subset(cnt+1);
	}

	private static void subset2(int cnt ,ArrayList<Integer> sel) {
		if(cnt == sel.size()) {
			copyMap(map);
			for(int i = 0; i < sel.size(); i++) {
				if (sub2[i] == true) { //true라면 A 적용
					int selRow = sel.get(i);
					for(int j = 0; j < W; j++) {
						cMap[selRow][j] = 0;
					}
				}else {
					int selRow = sel.get(i);
					for(int j = 0; j < W; j++) {
						cMap[selRow][j] = 1;
					}
				}
			}
			if(check(cMap)) {
				ans = Math.min(ans, sel.size());
			}
			return;
		}
		sub2[cnt] = true;
		subset2(cnt+1, sel);
		sub2[cnt] = false;
		subset2(cnt+1, sel);
		
		
	}

	private static void copyMap(int[][] map) {
		cMap = new int[D][W];
		for(int i = 0; i < D ; i ++) {
			for(int j = 0 ; j < W ; j ++) {
				cMap[i][j] = map[i][j];
			}
		}
			
		
	}

	private static boolean check(int[][] checkmap) {
		boolean flag = true;
		for(int j = 0; j < W; j++) {
			int lt =0;
			int colCnt = 1;
			int tmpcnt = 1;
			for(int i = 1; i< D ; i ++) {
				if(checkmap[lt][j] == checkmap[i][j]) {
					tmpcnt++;
					colCnt = Math.max(colCnt, tmpcnt);
					if(colCnt >= K) break;
				}else {
					tmpcnt = 1;
					lt = i;
				}
			}
			if(colCnt < K) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
