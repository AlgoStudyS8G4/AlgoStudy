import java.util.*;
import java.io.*;

public class Main_14719_빗물 {
    // 14719 빗물
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] arr = new int[H][W]; // 벽은 1, 빈 공간 0 으로 표시

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<W;i++){
            int n = Integer.parseInt(st.nextToken());
            for(int j=0;j<n;j++){
                arr[j][i] = 1;
            }
        }

        // 벽과 벽 사이의 거리를 구해주는 방식으로 접근
        int totalCnt = 0; // 전체 물의 양
        for(int i=0;i<H;i++){
            int startIdx = -1; // 벽이 시작하는 시점
            for(int j=0;j<W;j++){
                if(arr[i][j]==1 && startIdx < 0){ // 벽이 처음 시작하는 시점의 인덱스 위치를 저장해준다
                    startIdx = j;
                }
                else if( arr[i][j]==1 && startIdx >= 0 ){
                    totalCnt += j-startIdx-1; // 벽과 벽 사이의 물이 고일 수 있는 부분을 계산해서 카운팅 해준다.
                    startIdx = j; // 다시 벽의 위치를 갱신해준다.
                }
            }
        }

        System.out.println(totalCnt);


//        for(int i=0;i<H;i++){
//            for(int j=0;j<W;j++){
//                System.out.print(arr[i][j]+" ");
//            }
//            System.out.println();
//        }



    }
}