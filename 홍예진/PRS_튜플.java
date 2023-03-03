import java.util.*;

class Solution {
    class Box {
        int key;
        int count;
        public Box(int key, int count){
            this.key = key;
            this.count = count;
        }
    }
    public int[] solution(String s) {
        s = s.replaceAll("\\}", "");
        s = s.replaceAll("\\{", "");
        StringTokenizer st = new StringTokenizer(s, ",");
        Map<Integer, Integer> map = new HashMap<>();
        while(st.hasMoreTokens()){
            int key = Integer.parseInt(st.nextToken());
            if(map.get(key) == null)
                map.put(key, 1);
            else 
                map.put(key, map.get(key)+1);
        }
        
        List<Integer> keyList = new ArrayList<>(map.keySet());
        List<Box> list = new ArrayList<>();
        int[] answer = new int[keyList.size()];
        
        for(int key : keyList){
            list.add(new Box(key, map.get(key)));
        }
        Collections.sort(list, (b1, b2) -> b2.count - b1.count);
        int idx =0;
        for(Box b : list){
            answer[idx++] = b.key;
        }
        
        return answer;
    }
}
