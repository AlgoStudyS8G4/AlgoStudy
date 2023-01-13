package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj22233_가희와키워드 {
    // 새로운 글을 작성할 때 최대 10개의 키워드에 대해 글을 작성한다.
    // 글을 쓰면 메모장의 키워드는 지워짐.
    // 블로그에 글을 쓰고 나서 메모장에 있는 키워드를 구하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 메모장에 적은 키워드 갯수
        int M = Integer.parseInt(st.nextToken()); // 블로그에 쓴 글의 개수

        // 키워드에 없는 키워드를 작성한 경우 그냥 키워드목록 갯수 출력하기
        // 키워드목록 -> set으로 .... -> 작성 시 set 지워주기

        HashSet<String> set = new HashSet<>();
        for(int i=0;i<N;i++){
            String keyword = br.readLine();
            set.add(keyword);
        }

        // 만약 값을 가지고있다면 ? 지우기 > set size 출력
        // 만약 값을 가지고 있지 않다면 ? set size 출력
        for(int i=0;i<M;i++){
            String[] str = br.readLine().split(",");
            for(String keyword : str){
                if(set.contains(keyword)) set.remove(keyword);
            }
            System.out.println(set.size());
        }

    }
}
