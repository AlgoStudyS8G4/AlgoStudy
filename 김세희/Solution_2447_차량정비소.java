package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2447_차량정비소 {
    // 차량정비소 문제
    static int N,M,K,A,B; // 각 접수창구 정비창구 갯수, 고객 수, 고객이 이용한 접수창구 정비창구 번호
    static int[] timeA; // 접수시간
    static int[] timeB; // 정비시간
    static class person{
        int idx;
        int visitTime; // 도착시간
        int receptionEndTime; // 접수 끝나는 시간
        int receptionIdx; // 사용한 접수창구 번호
        int repairIdx; // 사용한 정비창구 번호
        int repairEndTime; // 정비 끝나는 시간

        public person(int idx, int visitTime, int receptionEndTime, int receptionIdx) {
            this.idx = idx;
            this.visitTime = visitTime;
            this.receptionEndTime = receptionEndTime;
            this.receptionIdx = receptionIdx;
        }
    }
    static PriorityQueue<person> visitQueue; // 접수창구 기다리는 큐
    static PriorityQueue<person> waitingQueue; // 정비창구 기다리는 큐
    static PriorityQueue<person> receptionDesk; // 접수창구 처리 큐
    static PriorityQueue<person> repairDesk; // 접수창구 처리 큐
    // 현재 사용중인지 아닌지 체크하기 위한 배열
    static boolean[] useRepair;
    static boolean[] useReception;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 고객이 이용한 창구번호
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            timeA = new int[N];
            timeB = new int[M];
            useReception = new boolean[N];
            useRepair = new boolean[M];

            result = 0; // 결과를 저장하기 위한 변수

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                timeA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++){
                timeB[i] = Integer.parseInt(st.nextToken());
            }

            visitQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.visitTime));
            waitingQueue = new PriorityQueue<>((o1, o2) -> {
                // 1. 먼저온사람, 2. 똑같이 왔다면, 이용했던 접수창구번호가 작은 고객이 우선한다.
                if(o1.receptionEndTime == o2.receptionEndTime){
                    return o1.receptionIdx - o2.receptionIdx;
                }
                return o1.receptionEndTime - o2.receptionEndTime;
            });

            // 끝나는 시간이 같다면 -> 접수 창구번호가 작은 고객순으로 출력
            receptionDesk = new PriorityQueue<>((o1, o2) -> {
                // 1. 먼저온사람, 2. 똑같이 왔다면, 이용했던 접수창구번호가 작은 고객이 우선한다.
                if(o1.receptionEndTime == o2.receptionEndTime){
                    return o1.receptionIdx - o2.receptionIdx;
                }
                return o1.receptionEndTime - o2.receptionEndTime;
            });

            repairDesk = new PriorityQueue<>(Comparator.comparingInt(o -> o.repairEndTime)); // 소요시간 작은 순으로 정렬

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<K;i++){
                int time = Integer.parseInt(st.nextToken());
                visitQueue.add(new person(i,time,-1,-1));
            }

            solve();

            if(result == 0) result = -1; // 해당 고객이 없는 경우
            System.out.println("#"+t+" "+result);

        }
    }

    // 1. 빈 접수창구로 가서 접수, 없다면 생길때까지 기다리기
    // - 고객번호가 낮은 순서대로, 빈 창구가 여러곳인 경우 접수 창구번호가 낮은 순서대로
    // 2. 빈 정비창구로 이동, 없다면 생길때까지 기다리기
    static void solve(){
        int time = visitQueue.peek().visitTime; // 현재 시간 -> 첫번째사람의 방문시간으로 세팅

        while(true){

            // 정비창구의 업무가 다 끝날때까지 반복 -> 모든 큐가 비어있어야함..
            if(visitQueue.isEmpty() && repairDesk.isEmpty() && waitingQueue.isEmpty() && receptionDesk.isEmpty()) break;

            // 배치 전 접수창구 사람들 없애기
            if(!receptionDesk.isEmpty()){
                person p = receptionDesk.poll();
                if(time<p.receptionEndTime) time = p.receptionEndTime;

                useReception[p.receptionIdx] = false; // 사용해제

                while(!receptionDesk.isEmpty() && time == receptionDesk.peek().receptionEndTime){
                    p = receptionDesk.poll();
                    useReception[p.receptionIdx] = false; // 사용 해제
                }
            }


            // n개의 접수창구 중 빈 곳에 배치
            for(int i=0;i<N;i++) {
                if(useReception[i]) continue; // 이미 사용중인 경우 -> 다음 접수창구 탐색

                // i번째 접수창구에 배치 !
                if(!visitQueue.isEmpty()) {
                    person p = visitQueue.poll();

                    if(time<p.visitTime) time = p.visitTime;

                    // 끝나는시간 : 현재시간 + 해당 창구 소요시간
                    int endTime = time + timeA[i];
                    receptionDesk.add(new person(p.idx,p.visitTime,endTime,i)); // 창구로 보낸다.
                }
            }

            // 정비창구 배치 전 끝난 정비창구 사람들 없애기
            if(!repairDesk.isEmpty()){
                person p = repairDesk.poll();
                if(time<p.repairEndTime) time = p.repairEndTime;
                if(p.repairIdx == B && p.receptionIdx == A){
                    result+=p.idx+1;
                }

                useRepair[p.repairIdx] = false; // 사용해제

                while(!repairDesk.isEmpty() && time == repairDesk.peek().repairEndTime){
                    p = repairDesk.poll();
                    // 같은 번호의 고객이라면 -> 고객번호 결과값에 더해주기
                    if(p.repairIdx == B && p.receptionIdx == A){
                        result+=p.idx+1;
                    }

                    useRepair[p.repairIdx] = false; // 사용 해제
                }
            }


            // m개의 정비창구 중 빈 곳에 배치하기 -> 정비 창구가 작은 곳 부터 배치해야함.
            for(int i=0;i<M;i++) {
                if(useRepair[i]) continue; // 이미 사용중인 경우 -> 다음 정비창구 탐색

                // i번째 정비창구에 배치
                if(!waitingQueue.isEmpty()) {
                    person p = waitingQueue.poll();

                    if(time<p.receptionEndTime) time = p.receptionEndTime;
                    useRepair[i] = true; // 현재 이용여부 처리

                    // 끝나는시간 : 현재시간 + 해당 창구 소요시간
                    p.repairIdx = i; // 이용창구
                    p.repairEndTime = time + timeB[i];
                    repairDesk.add(p); // 창구로 보낸다.
                }
            }

        }

    }
}

