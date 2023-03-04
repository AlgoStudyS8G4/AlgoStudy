package pro.p64065;

import java.util.*;

public class Solution_64065 {
    public ArrayList<Integer> solution(String s) {
        
        String[] nums = split(s);//문자열을 잘라서 배열로 저장하기
        Map<Integer, Integer> countMaps = new HashMap<Integer, Integer>();
        
        ArrayList<Integer> answer = new ArrayList<Integer>();
        
        
        for(String tmp : nums){
            String[] parse = tmp.split(",");
            for(String tupleStr : parse){
                int tupleNum = Integer.parseInt(tupleStr);
                if(countMaps.get(tupleNum) == null){
                    countMaps.put(tupleNum, 1);
                    answer.add(tupleNum);
                }
            }
        }

        return answer;
    }
    
    static String[] split(String str){
        String parse = str.substring(2);//처음 {{ 제외
        parse = parse.substring(0, parse.length()-2);//마지막 }} 제외
        parse = parse.replace(" ","").replace("},{", "@");//중간에 괄호 replace
        
        String[] num = parse.split("@");//1,2,3 형태로 저장
        Arrays.sort(num, (x,y)->Integer.compare(x.length(), y.length()));//집합의 작은 길이부터 튜플 원소에 추가하므로 정렬함
       
        return num;
     }
}
