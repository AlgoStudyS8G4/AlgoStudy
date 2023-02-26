import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);
        char ch = phone_book[0].charAt(0);
        for(String s: phone_book){
            if(s.charAt(0) > ch)
                ch = s.charAt(0);
            
            
        }
        
        return answer;
    }
}