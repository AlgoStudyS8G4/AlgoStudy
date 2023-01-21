package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj14658 {
    static int N,M,L,K;
    // 하늘에서 별똥별이 빗발친다.
    // 지표면에 떨어지는 별똥별 수를 최소화하기
    static ArrayList<spot> list;
    static class spot {
        int x;
        int y;
        spot(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 구역의 가로길이
        M = Integer.parseInt(st.nextToken()); // 세로길이
        L = Integer.parseInt(st.nextToken()); // 트램펄린 한 변의 길이
        K = Integer.parseInt(st.nextToken()); // 별똥별의 수

        list = new ArrayList<>(); // 별똥별 좌표 저장 list

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new spot(x,y));
        }

        int max = Integer.MIN_VALUE; // 최대로 췽겨낼 수 있는 별의 수
        // 지구에 떨어지는 별똥별의 수를 최소로 만들기 -> 가장 많은 별을 포함해야 한다.
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.size();j++){
                int count = 0;
                int x = list.get(i).x;
                int y = list.get(j).y;
                for(int k=0;k<list.size();k++){
                    int nx = list.get(k).x;
                    int ny = list.get(k).y;
                    if(x<=nx && nx<=x+L && y<=ny && ny<=y+L) count++;
                }
                max = Math.max(max,count);
            }
        }

        System.out.println(K-max);

    }

}
