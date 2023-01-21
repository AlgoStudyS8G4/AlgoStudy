import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_5658 {
    // 보물상자 비밀번호
    static int N,K;
    static char[] arr;
    static int turn;
    static Set<Integer> set;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken()); // k번째로 큰 수

            set = new HashSet<>();

            arr = new char[N];
            turn = N/4;
            result = 0;

            String str = br.readLine();
            for(int i=0;i<N;i++){
                arr[i] = str.charAt(i);
            }

            for(int i=0;i<turn;i++){
                // 수 확인
                checkNum();
                // 옮겨주기
                move();
            }

            // set -> list
            List<Integer> numList = new ArrayList<>(set);
            Collections.sort(numList,Collections.reverseOrder());

            result = numList.get(K-1); // k번째 수 가져오기

            System.out.println("#"+t+" "+result);


        }

    }

    // 수 확인
    static void checkNum(){
        for(int i=0;i<N;i+=turn){
            String s = "";
            for(int j=i;j<i+turn;j++){
                s += arr[j];
            }
            //System.out.println(s);
            set.add(Integer.parseInt(s,16));
        }
    }

    // 시계방향으로 이동하기
    static void move(){
        char tmp = arr[N-1];
        for(int i=N-1;i>0;i--){
            arr[i] = arr[i-1];
        }
        arr[0] = tmp;
    }
}
