import java.util.*;

// 프로그래머스 lv.2 귤고르기
public class Solution_귤고르기 {

    // test
    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};

        int answer = solution(k,tangerine);

        System.out.println(answer);
    }

    // solution
    public static int solution(int k, int[] tangerine) {
        int answer = 0;

        // 수확한 귤 중 k개를 골라 상자 하나에 담아 판매하기
        // 크기가 서로 다른 종류의 수의 최솟값을 리턴...

        Map<Integer,Integer> map = new HashMap<>();
        // 각 크기별로 갯수 저장
        for(int i=0;i<tangerine.length;i++){
            map.put(tangerine[i],map.getOrDefault(tangerine[i],0)+1);
        }

        // Map의 키 종류를 리스트로 저장하기
        ArrayList<Integer> keySet = new ArrayList<>(map.keySet());

        // 내림차순 정렬
        keySet.sort((o1,o2)-> map.get(o2).compareTo(map.get(o1)));

        // 내림차순 한 결과를 가지고 하나씩 빼준다.
        int result = k;
        for(int key : keySet){
            if(result<=0) break;
            int num = map.get(key); // 값을 하나 뺀다.
            result -= num;
            answer++;
        }


        return answer;
    }
}
