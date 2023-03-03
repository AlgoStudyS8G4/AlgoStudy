import java.util.*;

class Solution {
    public static int getIndex(String[] id_list, String userId){
        for(int i = 0; i < id_list.length; i++){
            if(id_list[i].equals(userId))
                return i;
        }
        return -1;
    }
    public int[] solution(String[] id_list, String[] report, int k) {
        
        Map<String, Integer> count = new HashMap<>();
        ArrayList<String>[] singojaList = new ArrayList[id_list.length];
        
        for(int i = 0; i < id_list.length; i++){
            String singoja = id_list[i];
            singojaList[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(report[i]);
            // 중복 제거용
            Set<String> targets = new HashSet<>();
            while(st.hasMoreTokens()){
                targets.add(st.nextToken());
            }
            
            // 현재 유저에게 신고당한 사람 리스트
            ArrayList<String> targetList = new ArrayList<>(targets);
            for(String target : targetList){
                singojaList[getIndex(id_list,target)].add(singoja);
                if(count.get(target) == null){
                    count.put(target, 1);
                } else {
                    count.put(target, count.get(target)+1);
                }
            }
        }
        
        int[] answer = new int[id_list.length];
        // 신고당한 사람 리스트
        List<String> keyList = new ArrayList<>(count.keySet());
        System.out.println(count);
        for(String target : keyList){
            // 신고를 k미만 당했다면 넘어간다.
            if(count.get(target) < k) continue;
            // 그 이상이라면, 이를 신고한 사람들에게 메일을 준다.
            for(String singoja : singojaList[getIndex(id_list, target)]){
                answer[getIndex(id_list, singoja)]++;
            }
        }
        
        return answer;
    }
}
