package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5653 {
    static int N,M,K;
    static int[][] arr;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
    static Queue<spot> queue;
    static PriorityQueue<spot> pq; // 활성상태인 경우 넣기 (번식할것들)
    static class spot implements Comparable<spot>{
        int x;
        int y;
        int k; // 생명력
        int inputT; // 큐에 들어온 시간 -> 번식시작

        spot(int x, int y, int k, int inputT){
            this.x = x;
            this.y = y;
            this.k = k;
            this.inputT = inputT;
        }

        @Override
        public int compareTo(spot o) {
            return o.k - this.k;
        }
    }
    // swea 줄기세포배양 - K시간 후 살아있는 줄기세포의 총 개수를 구하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken()); // 배양시간

            int maxSize = 5000;
            arr = new int[maxSize][maxSize];

            queue = new LinkedList<>();
            pq = new PriorityQueue<>();

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    int x = st.nextToken().charAt(0)-'0';
                    arr[maxSize/2+i][maxSize/2+j] = x;
                    if(arr[maxSize/2+i][maxSize/2+j]>0){
                        queue.add(new spot(maxSize/2+i,maxSize/2+j,arr[maxSize/2+i][maxSize/2+j],0));
                    }
                }
            }

            // 배양시간동안 반복
            // x시간 후 활성화 -> x시간이 지나면 죽게된다.
            // 1시간동안 상하좌우 번식
            // 이미 줄기세포가 있는 경우에는 번식하지 않음, 단 두 개 이상의 줄기세포가 동시에 번식하려고 하는 경우 생명력이 높은 것이 차지
            for(int time = 1; time<=K; time++){

                int size = queue.size();
                for(int s=0;s<size;s++){ // 현재 큐 사이즈만큼 돈다. -> 큐로 다시 들어가는 애들도 있기 때문에 size만큼 돌아야된다.!
                    spot cur = queue.poll();

                    //활성화가 된 경우 -> 상하좌우로 세포 번식하기 위해 pq에 넣기
                    if (time == cur.inputT + cur.k + 1){
                        pq.add(new spot(cur.x,cur.y,cur.k,cur.inputT));
                    }
                    // 이미 활성화가 되었거나(이미 번식을 완료한 세포) or 비활성 상태인 경우
                    else if(time < cur.inputT + cur.k*2){
                        queue.add(new spot(cur.x,cur.y,cur.k,cur.inputT));
                    }
                }

                // bfs로 번식
                while(!pq.isEmpty()){
                    spot cur = pq.poll();

                    if(time < cur.inputT+cur.k*2) queue.add(cur);

                    for(int i=0;i<4;i++){
                        int nx = cur.x + dir[i][0];
                        int ny = cur.y + dir[i][1];

                        if(arr[nx][ny]==0){
                            arr[nx][ny] = cur.k; // 번식
                            queue.add(new spot(nx,ny,cur.k,time));
                        }

                    }

                }
            }


            System.out.println("#"+t+" "+queue.size());


        }

    }

}
