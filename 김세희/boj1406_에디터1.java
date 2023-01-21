package ag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj1406_에디터1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String str = br.readLine();
        int n = Integer.parseInt(br.readLine());

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for(int i=0;i<str.length();i++){
            left.push(str.charAt(i));
        }

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            char key = st.nextToken().charAt(0);

            if(key == 'L'){ // 커서를 왼쪽으로 한칸 옮김
                if(!left.isEmpty()){
                    right.push(left.pop());
                }
            }
            else if(key == 'D'){ // 오른쪽으로 한칸 옮김
                if(!right.isEmpty()){
                    left.push(right.pop());
                }
            }
            else if(key == 'B'){ // 왼쪽에 있는 문자를 삭제
                if(!left.isEmpty()){
                    left.pop();
                }
            }
            else if(key == 'P'){ // 문자를 커서 왼쪽에 추가함
                char c = st.nextToken().charAt(0);
                left.push(c);
            }
        }

        while(!left.isEmpty()){
            right.push(left.pop());
        }

        while(!right.isEmpty()){
            sb.append(right.pop());
        }

        System.out.println(sb);


    }


}
