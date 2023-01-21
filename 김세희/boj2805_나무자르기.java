package ag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2805_나무자르기 {
    static int N,M;
    static int[] tree;
    static int max = 0;
    static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(tree[i],max);
        }

        Arrays.sort(tree);
        result = solve();
        System.out.println(result);

    }

    static int solve(){
        int left = 0;
        int right = max; // 최대로 설정할 수 있는 절단기의 길이
        int mid = 0; // 높이

        while(left<=right){
            mid = (left+right)/2;

            long total = 0; // 나무 높이
            total = getSum(mid);

            if(total>=M){ // 잘린 나무가 최소 길이보다 크다면 ? 길게 설정
                left = mid+1;
            }
            else if(result<M){ // 절단기 짧게 설정하기
                right = mid-1;
            }

        }

        return right;

    }

    // 나무 길이 구하기
    static long getSum(int mid){
        long result = 0;
        for(int i=0;i<N;i++){
            if(tree[i]>mid){
                result += tree[i] - mid;
            }
        }
        return result;
    }
}
