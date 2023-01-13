package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class boj17615_볼모으기 {
    public static void main(String[] args) throws IOException {
        // 볼의 최소 이동횟수 구하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String balls = br.readLine();

        int result = Integer.MAX_VALUE;

        // 1. r을 오른쪽으로
        boolean blueFlag = false; // B값이 나타난 이후부터 R의 갯수를 카운팅해주기 위함
        int RCount = 0;
        for(int i=N-1;i>=0;i--){
            if(!blueFlag && balls.charAt(i)=='B') blueFlag = true; // B 최초 발견 시 flag true로 변경
            else if(blueFlag && balls.charAt(i)=='R') RCount++;
        }

        result = Math.min(result,RCount);

        // 2. r을 왼쪽으로
        blueFlag = false;
        RCount = 0;
        for(int i=0;i<N;i++){
            if(!blueFlag && balls.charAt(i)=='B') blueFlag = true;
            else if(blueFlag && balls.charAt(i)=='R') RCount++;
        }

        result = Math.min(result,RCount);

        // 3. b를 오른쪽으로
        boolean redFlag = false;
        int BCount = 0;
        for(int i=N-1;i>=0;i--){
            if(!redFlag && balls.charAt(i)=='R') redFlag = true;
            else if(redFlag && balls.charAt(i)=='B') BCount++;
        }

        result = Math.min(result,BCount);

        // 4. b를 왼쪽으로
        redFlag = false;
        BCount = 0;
        for(int i=0;i<N;i++){
            if(!redFlag && balls.charAt(i)=='R') redFlag = true;
            else if(redFlag && balls.charAt(i)=='B') BCount++;
        }

        result = Math.min(result,BCount);

        System.out.println(result);

    }
}
