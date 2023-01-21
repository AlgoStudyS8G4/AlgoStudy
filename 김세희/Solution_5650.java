package study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5650 {
    static int N;
    static int[][] map;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
    static class spot{
        int x;
        int y;
        spot(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<spot> blank; // 공이 출발할 수 있는 위치 ?
    static ArrayList<spot>[] hole;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            int maxScore = Integer.MIN_VALUE;

            map = new int[N][N];
            blank = new ArrayList<>();

            hole = new ArrayList[5];
            for(int i=0;i<5;i++){
                hole[i] = new ArrayList<>();
            }

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]==0) blank.add(new spot(i,j)); // 빈칸
                    else if(map[i][j]>=6) hole[map[i][j]-6].add(new spot(i,j)); // 웜홀
                }
            }

            // 출발할 수 있는 각 위치에서 모든 방향 탐색
            for(int i=0;i<blank.size();i++){
                for(int d=0;d<4;d++){
                    spot start = blank.get(i);
                    int score = getScore(start.x,start.y,d);
                    maxScore = Math.max(score,maxScore);
                }
            }

            System.out.println("#"+t+" "+maxScore);
            //sb.append("#").append(t+" ").append(maxScore+"\n");

        }

        //System.out.println(sb);

    }

    // 이동하기
// 블랙홀 만나면 out
    static int getScore(int sx,int sy,int d){
        // 1. 벽에 부딪히면 반대로 전환
        // 2. 웜홀 -> 웜홀 이동
        // 3. 삼각형을 만날 경우
        int nx = sx;
        int ny = sy;
        int score = 0;
        //boolean isStart = true;

        while(true){

            nx += dir[d][0];
            ny += dir[d][1];

            //isStart = false;

            // 한 칸 이동 후 탈출 조건 설정
            // 1. 블랙홀인경우 탈출
            // 2. 출발위치로 다시 돌아올 경우
            if(nx==sx && ny==sy || nx>=0 && ny>=0 && nx<N && ny<N && map[nx][ny] == -1) return score;


            // 1. 벽인경우
            if(nx < 0 || nx >= N || ny < 0 || ny >= N){

                if(d==0 || d==2){
                    d += 1;
                }
                else if(d==1 || d==3) {
                    d -= 1;
                }

                score += 1;

            }
            // 2. 웜홀인 경우
            else if(map[nx][ny]>=6 && map[nx][ny]<=10){
                spot hole1 = hole[map[nx][ny]-6].get(0);
                spot hole2 = hole[map[nx][ny]-6].get(1);

                if(hole1.x == nx && hole1.y == ny){
                    nx = hole2.x;
                    ny = hole2.y;
                }else{
                    nx = hole1.x;
                    ny = hole1.y;
                }

            }
            // 3. 블록인 경우
            else if(map[nx][ny]>=1 && map[nx][ny]<=5){

                if(map[nx][ny]==1){
                    if(d==0) d=1;
                    else if(d==1) d=3;
                    else if(d==2) d=0;
                    else if(d==3) d=2;
                }
                else if(map[nx][ny]==2){
                    if(d==0) d=3;
                    else if(d==1) d=0;
                    else if(d==2) d=1;
                    else if(d==3) d=2;
                }
                else if(map[nx][ny]==3){
                    if(d==0) d=2;
                    else if(d==1) d=0;
                    else if(d==2) d=3;
                    else if(d==3) d=1;
                }
                else if(map[nx][ny]==4){
                    if(d==0) d=1;
                    else if(d==1) d=2;
                    else if(d==2) d=3;
                    else if(d==3) d=0;
                }
                else if(map[nx][ny]==5){
                    if(d==0) d=1;
                    else if(d==1) d=0;
                    else if(d==2) d=3;
                    else if(d==3) d=2;
                }

                score+=1;

            }


        }


    }
}