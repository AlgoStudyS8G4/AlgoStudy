import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        
        Arrays.sort(phone_book);
        Map<Character, List<String>> map = new HashMap<>();
        for(String phone : phone_book){
            char pre = phone.charAt(0);
            List<String> list = map.getOrDefault(pre, new ArrayList<String>());
            for(String other : list){
                if(other.length() > phone.length()) continue;
                if(other.equals(phone.substring(0, other.length())))
                    return false;
            }
            list.add(phone);
            map.put(pre, list);
        }
        
        return true;
    }
}