package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {
    // 활주로 건설
    static int N,X;
    static int[][] map;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지도의 한 변 크기
            X = Integer.parseInt(st.nextToken()); // 경사로의 길이
            result = 0;

            map = new int[N][N]; // 각 지형의 높이를 저장한다.

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가로 세로 체크하기
            for(int i=0;i<N;i++){
                if(isInstall(0,i,0)) result += 1; // 세로
                if(isInstall(i,0,1)) result += 1; // 가로
            }

            System.out.println("#"+t+" "+result);

        }

    }

    // 경사로의 높이 차이가 1이고 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되어야 한다.
    static boolean isInstall(int x,int y,int d){
        boolean[] runway = new boolean[N]; // 경사로가 설치되었을 때 체크하기 위한 배열

        if(d==1){
            // 가로인 경우
            for(int i=1;i<N;i++){
                int diff = map[x][i] - map[x][i-1];

                // diff가 0이 아닌 경우 -> 경사로를 설치해야 하는 경우만 보기
                if(diff==0) continue;

                // 칸의 차이가 2 이상인 경우 -> return false
                if(Math.abs(diff)>=2) return false;

                if(diff == 1){ // 이전칸보다 한칸 클 경우, -> 앞에 있는 것을 봐야한다.
                    for(int j=i-1;j>=i-X;j--){
                        if(j<0) return false; // 범위를 넘어간다면 설치불가 -> false 리턴.
                        if(runway[j] || map[x][i-1] != map[x][j]) return false;
                        runway[j] = true;
                    }

                }else if(diff == -1){ // 이전보다 한칸 작을 경우 -> 뒤에있는 것 봐야함.
                    // 1. X만큼 탐색
                    // 2. 범위를 벗어나면 ? false, 경사로가 이미 설치되어있거나, 높이가 다르다면 false,
                    for(int j=i;j<i+X;j++){ // X만큼 탐색
                        if(N<=j) return false;
                        if(runway[j] || map[x][j] != map[x][i]) return false;
                        runway[j] = true;
                    }
                }
            }
        }

        else if(d==0){
            // 세로인 경우
            for(int i=1;i<N;i++){
                int diff = map[i][y] - map[i-1][y];

                // diff가 0이 아닌 경우 -> 경사로를 설치해야 하는 경우만 보기
                if(diff==0) continue;

                // 칸의 차이가 2 이상인 경우 -> return false
                if(Math.abs(diff)>=2) return false;

                if(diff == 1){ // 이전칸보다 한칸 클 경우, -> 앞에 있는 것을 봐야한다.
                    for(int j=i-1;j>=i-X;j--){
                        if(j<0) return false; // 범위를 넘어간다면 설치불가 -> false 리턴.
                        if(runway[j] || map[i-1][y] != map[j][y]) return false;
                        runway[j] = true;
                    }

                }else if(diff == -1){ // 이전보다 한칸 작을 경우 -> 뒤에있는 것 봐야함.
                    // 1. X만큼 탐색
                    // 2. 범위를 벗어나면 ? false, 경사로가 이미 설치되어있거나, 높이가 다르다면 false,
                    for(int j=i;j<i+X;j++){ // X만큼 탐색
                        if(N<=j) return false;
                        if(runway[j] || map[j][y] != map[i][y]) return false;
                        runway[j] = true;
                    }
                }
            }
        }

        return true;
    }

}
