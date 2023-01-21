package ag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj21921_블로그 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer,Integer> map = new HashMap<>();

        // 슬라이딩윈도우
        int sum = 0;
        int max = 0;
        // 먼저 x 구간만큼의 합을 구해준다.
        for(int i=0;i<X;i++){
            sum += arr[i];
        }

        map.put(sum,1);
        for(int i=0;i<N-X;i++){
            if(sum > max) max = sum; // max값 갱신

            // 다음 값 하나씩 더해주고 제일 앞에 있는 값을 빼줌.
            sum += arr[i+X];
            sum -= arr[i];
            map.put(sum,map.getOrDefault(sum,0)+1);
        }

        if(max==0){
            System.out.println("SAD");
        }else{
            System.out.println(max);
            System.out.println(map.get(max));
        }


    }
}
