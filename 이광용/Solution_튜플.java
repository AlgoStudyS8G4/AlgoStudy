package programmers;

import java.util.*;

public class Solution_튜플 {

	public static void main(String[] args) {
		solution("{{123}}");
	}
    public static int[] solution(String s) {
    	int[] answer = {};
    	//String 파싱 -> split, replaceAll
    	//"},{" 문자열로 파싱함
    	//'{', '}'는 메타 캐릭터이기 때문에 이스케이프 \\ 해야힘
//    	System.out.println( "s : " + s);
    	String[] sArr = s.split("\\},\\{");
//    	System.out.println(Arrays.toString(sArr));
//    	System.out.println(sArr[0]);
//    	System.out.println(sArr[1]);
    	
    	//배열의 가장 앞 문자열, 가장 뒷 문자열 에서 '{', '}' 문자 제거
    	sArr[0] = sArr[0].replaceAll("[{}]", "");
    	sArr[sArr.length-1] = sArr[sArr.length-1].replaceAll("[{}]", "");
//    	System.out.println(sArr[0]);
//    	System.out.println(sArr[1]);
    	
    	//길이가 짧은 sArr원소 순으로 정렬 
    	//1. 각 sArr원소를 ','을 기준으로 split함
    	//2. split한 한 배열의 길이를 인덱스로 해서 sortedByLength에 해당 배열을 삽입함
    	String[][] sortedByLength = new String[sArr.length][];
    	for(int i =0 ; i < sArr.length; i++) {
    		String[] sArrSplit = sArr[i].split(",");
    		sortedByLength[sArrSplit.length-1] = sArrSplit;
    	}
    	
    	//sortedByLength을 돌면서 set으로 새로운 숫자가 나온건지 판단하고 없는 요소라면 ansString에 넣는다.
    	String[] ansString = new String[sArr.length];
    	HashSet<String> set = new HashSet<>();
    	for(int i = 0; i < sortedByLength.length; i ++) {
    		for(int j =0; j < sortedByLength[i].length; j++) {
    			if(!set.contains(sortedByLength[i][j])) {
    				set.add(sortedByLength[i][j]);
    				ansString[i] = sortedByLength[i][j];
    			}
    		}
    	}
    	//String[]을 int[]으로 변환`
    	answer = new int[sArr.length];
    	for(int i = 0; i < ansString.length; i++) {
    		answer[i] = Integer.parseInt(ansString[i]);
    		System.out.print(answer[i] + " ");
    	}
        return answer;
    }
}
