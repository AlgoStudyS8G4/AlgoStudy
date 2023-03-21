import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42885
class Solution {
    public int solution(int[] people, int limit) {
        // limit 100
        // 1. 작은 순 30 40 60 70 -> (30, 40), (60), (70) => 최적 (30, 70), (40, 60)
         
        ArrayList<Integer> list = new ArrayList<>();
        for(int weight : people)
            list.add(weight);
        
        Collections.sort(list, Comparator.reverseOrder());
        
        int answer = 0;
        int extra = limit;
        while(!list.isEmpty()){
            ArrayDeque<Integer> idxList = new ArrayDeque<>();
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) > extra) continue;
                extra -= list.get(i);
                idxList.add(i);
            }
            
            while(!idxList.isEmpty()){
                int idx = idxList.pollLast();
                list.remove(idx);
            }
            
            answer++;
            extra = limit;
        }
        
        return answer;
    }
}
