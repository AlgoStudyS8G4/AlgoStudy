package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] honey;
    private static int total, ans;
    private static int[] toLeftSum, toRightSum;

    public static void case1(){
        for(int i = 1; i < n-1; i++){
            int bee1 = total - ( honey[0] + honey[i] );
            int bee2 = total - toRightSum[i];
            ans = Math.max(ans, bee1 + bee2);
        }
    }
    public static void case2(){
        for(int i = 1; i < n-1; i++){
            int bee1 = total - ( honey[n-1] + honey[i] );
            int bee2 = total - toLeftSum[i];
            ans = Math.max(ans, bee1 + bee2);
        }
    }
    public static void case3(){
        for(int i = 1; i < n -1; i++){
            int bee1 = toRightSum[i] - honey[0];
            int bee2 = toLeftSum[i] - honey[n-1];
            ans = Math.max(ans, bee1 + bee2);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        honey = new int[n];
//        최대값을 위해 양 쪽 끝에 bee 혹은 벌통으로 채워져 있어야하므로 3가지 case
//        case1: 왼쪽 끝에 bee1, 오른쪽 끝에 벌통, bee2는 위치 탐색
//          bee1의 채집량 = 모든 꿀 양의 총합 - ( honey[bee1시작위치: 0] + honey[bee2시작위치] )
//          bee2의 채집량 = 모든 꿀 양의 총합 - ( [0] ~ [bee2 시작위치]까지 누적합 )
//        case2: 왼쪽 끝에 벌통, 오른쪽 끝에 bee1, bee2는 위치 탐색
//          bee1의 채집량 = 모든 꿀 양의 총합 - ( honey[bee1시작위치: n-1] + honey[bee2시작위치] )
//          bee2의 채집량 = 모든 꿀 양의 총합 - ( [bee2 시작위치] ~ [n-1] 까지 누적합 )
//        case3: 왼쪽 끝에 bee1, 오른쪽 끝에 bee2, 벌통은 위치 탐색
//          bee1의 채집량 = ( [0] ~ [벌통 위치] 누적합 ) - honey[bee1시작위치: 0]
//          bee2의 채집량 =  ( [벌통 위치] ~ [bee2 위치] 누적합 ) - honey[bee1시작위치: 0]

        //왼쪽부터 오른쪽으로 가는 누적합 배열
        toRightSum = new int[n];
        //오른쪽부터 왼쪽으로 가는 누적합 배열
        toLeftSum = new int[n];

        int tempSum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0 ; i< n; i++){
            honey[i] = Integer.parseInt(st.nextToken());
            tempSum += honey[i];
            toRightSum[i] = tempSum;
        }
        total = toRightSum[n-1];
        tempSum = 0;
        for(int i = n -1; i >= 0; i--){
            tempSum += honey[i];
            toLeftSum[i] = tempSum;
        }

        ans = 0; //최대의 꿀의 채집량
        case1();
        case2();
        case3();
        System.out.println(ans);
    }
}