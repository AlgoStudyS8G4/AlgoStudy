package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_4008 {
    // 수식의 최대값, 최솟값의 차, + - * / 순
    static int N;
    static int[] operatorCnt = new int[4];
    static int[] num; // 숫자 저장
    static int[] tmp; // 순열돌린 결과 저장을 위한 배열
    static int min;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            num = new int[N];
            tmp = new int[N-1];

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<4;i++){
                operatorCnt[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                num[i] = Integer.parseInt(st.nextToken());
            }

            solve(0);
            int ans = max-min;
            System.out.println("#"+t+" "+ans);

        }

    }

    // 중복순열
    static void solve(int depth){
        if(depth==N-1){
            // 계산하기
            int result = calc();
            if(result > max){
                max = result;
            }
            if(result < min){
                min = result;
            }
            return;
        }

        for(int i=0;i<4;i++){
            if(operatorCnt[i]>0){
                operatorCnt[i]--;
                tmp[depth] = i;
                solve(depth+1);
                operatorCnt[i]++;
            }
        }
    }

    // 계산
    static int calc(){
        int result = num[0];

        for(int i=0;i<tmp.length;i++){
            if(tmp[i]==0) { // +
                result += num[i+1];
            }
            else if(tmp[i]==1){ // -
                result -= num[i+1];
            }
            else if(tmp[i]==2){ // *
                result *= num[i+1];
            }
            else if(tmp[i]==3){ // /
                result /= num[i+1];
            }
        }

        return result;

    }


}
