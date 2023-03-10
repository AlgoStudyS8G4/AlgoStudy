package pro.p64065;

import java.util.*;

public class Solution_64065 {
    public ArrayList<Integer> solution(String s) {
        
        String[] nums = split(s);//���ڿ��� �߶� �迭�� �����ϱ�
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
        String parse = str.substring(2);//ó�� {{ ����
        parse = parse.substring(0, parse.length()-2);//������ }} ����
        parse = parse.replace(" ","").replace("},{", "@");//�߰��� ��ȣ replace
        
        String[] num = parse.split("@");//1,2,3 ���·� ����
        Arrays.sort(num, (x,y)->Integer.compare(x.length(), y.length()));//������ ���� ���̺��� Ʃ�� ���ҿ� �߰��ϹǷ� ������
       
        return num;
     }
}
