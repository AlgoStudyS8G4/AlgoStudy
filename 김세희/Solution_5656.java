import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656 {
    static int N,W,H,minValue;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int[][] arr;
    static int[][] copyArr;
    static int[] indexArr;
    static class block{
        int x;
        int y;
        int size;
        block(int x,int y, int size){
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            arr = new int[H][W];
            indexArr = new int[N];
            minValue = Integer.MAX_VALUE;

            for(int i=0;i<H;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<W;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve(0);
            System.out.println("#"+t+" "+minValue);

        }

    }

    // 중복순열로 떨어질위치 정하기
    static void solve(int depth){
        if(depth == N){
            copyMap();
            for(int i=0;i<N;i++){
                dropBall(indexArr[i]); // 구슬 쏘기
//                for(int n=0;n<H;n++){
//                    for(int m=0;m<W;m++){
//                        System.out.print(copyArr[n][m]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }
            minValue = Math.min(minValue,countBlock());
            return;
        }

        for(int i=0;i<W;i++){
            indexArr[depth] = i;
            solve(depth+1);
        }

    }


    // 공 떨어뜨리기
    static void dropBall(int idx){
        // 공에 맞을 블럭 위치 구해주기 -> 높이만 구해주면 됨.
        int h; // 높이
        int w = idx;
        for(h=0;h<H;h++){
            if(copyArr[h][w]>0) break;
        }

        // 해당 열이 모두 비어있는 경우 -> 부술 벽돌이 없는 경우
        if(h==H) return;

        removeBlock(h,w); // 벽돌제거
        moveBlock(); // 벽돌옮겨주기
    }

    // 벽돌제거
    static void removeBlock(int h,int w){
        Queue<block> queue = new LinkedList<>();

        queue.add(new block(h,w,copyArr[h][w]-1));
        copyArr[h][w] = 0;

        while(!queue.isEmpty()){
            block now = queue.poll();
            int size = now.size; // 지울 블럭의 갯수

            for(int i=0;i<4;i++){
                int nx = now.x;
                int ny = now.y;
                for(int s=0;s<size;s++){
                    nx += dir[i][0];
                    ny += dir[i][1];
                    if(nx>=0 && nx < H && ny >=0 && ny < W && copyArr[nx][ny]>0){
                        queue.add(new block(nx,ny,copyArr[nx][ny]-1));
                        copyArr[nx][ny] = 0;
                    }
                }
//                if(nx >=0 && nx < H && ny >=0 && ny < W && copyArr[nx][ny]>0){
//                    queue.add(new block(nx,ny));
//                }
            }
        }
    }

    // 벽돌옮기기
    static void moveBlock(){
        Queue<Integer> queue = new LinkedList<>();
        for(int j=0;j<W;j++){
            for(int i=H-1;i>=0;i--){ // 배열 밑에서부터 시작하기
                if(copyArr[i][j]>0){
                    queue.add(copyArr[i][j]);
                    copyArr[i][j]=0;
                }
            }

            int i = H-1;
            while(!queue.isEmpty()){
                copyArr[i][j] = queue.poll();
                i--;
            }

            queue.clear(); // 다음 열을 위해 큐 비워주기
        }
    }

    // 부수고 남은벽돌 갯수세기
    static int countBlock(){
        int cnt = 0;
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(copyArr[i][j]>0) cnt++;
            }
        }
        return cnt;
    }

    static void copyMap(){
        copyArr = new int[H][W];
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                copyArr[i][j] = arr[i][j];
            }
        }
    }

}
