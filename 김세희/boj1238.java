package study;

import java.util.*;
import java.io.*;

public class boj1238 {
    // boj1238 파티
    static int N;
    static int M;
    static int X;
    static int[] dist;
    static ArrayList<Node>[] list;
    static int min = Integer.MIN_VALUE;
    static int result = 0;

    static class Node{
        int edge;
        int cost;

        public Node(int edge, int cost) {
            this.edge = edge;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        list = new ArrayList[N+1];
        for(int i=0;i<N+1;i++) {
            list[i]=new ArrayList<>();
        }


        for(int i=1;i<M+1;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[s].add(new Node(e,c));

        }

        for(int i=1;i<=N;i++) {

            int a = getMinCost(i,X); //갈때 거리
            int b = getMinCost(X,i); //올떄 거리

            //System.out.println(a);
            min = Math.max(a+b, min);


        }

        System.out.println(min);



    }

    static int getMinCost(int start,int end) {
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2)->Integer.compare(o1.cost, o2.cost));

        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(new Node(start,0));
        dist[start]=0;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(dist[cur.edge]<cur.cost) continue; // 이미 갱신됐다면

            for(int i=0;i<list[cur.edge].size();i++) {
                Node next = list[cur.edge].get(i);
                if(next.cost+cur.cost<dist[next.edge]) {
                    dist[next.edge] = next.cost+cur.cost;
                    q.add(new Node(next.edge,dist[next.edge]));
                }

            }

        }


        return dist[end];


    }
}
