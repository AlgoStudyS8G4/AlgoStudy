package pro.p42885;
import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        
        int idx = 0;
        
        for(int i = people.length-1; idx <= i; i--){
            if(people[i] + people[idx] <= limit){
                answer++;
                idx++;
            }else{
                answer++;                
            }
        }
        
        return answer;
    }
}