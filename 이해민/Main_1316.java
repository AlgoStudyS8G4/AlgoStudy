package boj.p1316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_1316 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		
		String[] text = new String[N];
		
		for (int i = 0; i < N; i++) {
			text[i] = br.readLine();
		}
		
		for (int i = 0; i < N; i++) {
			Map<Character, Integer> maps = new HashMap<Character, Integer>();
			boolean flag = false;
			for (int j = 0; j < text[i].length(); j++) {
				if(maps.get(text[i].charAt(j)) == null) maps.put(text[i].charAt(j), 1);
				else {
					flag = true;
					break;
				}
				for (int k = j+1; k < text[i].length(); k++) {
					if(text[i].charAt(j) != text[i].charAt(k)) break;
					else j++;
				}
				
			}
			if(!flag) result++;
		
		}
		
		
		System.out.println(result);
	}
}
