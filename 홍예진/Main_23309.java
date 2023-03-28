import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int N, M;

    static class Node {
        int number;
        Node post, next;
        public Node(int number, Node post, Node next){
            this.number = number;
            this.post = post;
            this.next = next;
        }
    }
    public static void main(String[] args){
        Map<Integer, Node> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        Node last = null;
        Node first = null;
        if(true){
            int num = sc.nextInt();
            first = new Node(num, null, null);
            map.put(num, first);
            last = first;
        }
        for(int i = 1; i < N; i++){
            int num = sc.nextInt();
            Node now = new Node(num, last, null);
            last.next = now;
            map.put(num, now);
            last = now;
        }
        // 입력 종료 후 first ... last 로 객체 매칭되어있음. 원형 큐 구조 반영
        last.next = first;
        first.post = last;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            String command = sc.next();
            int num = sc.nextInt();
            Node now = map.get(num);

            if(command.equals("BN")){
                int newNum = sc.nextInt();
                Node next = now.next;
                sb.append(next.number).append("\n");

                Node newNode = new Node(newNum, now, next);
                now.next = newNode;
                next.post = newNode;
                map.put(newNum, newNode);
            } else if(command.equals("BP")){
                int newNum = sc.nextInt();
                Node post = now.post;
                sb.append(post.number).append("\n");

                Node newNode = new Node(newNum, post, now);
                now.post = newNode;
                post.next = newNode;
                map.put(newNum, newNode);
            }else if(command.equals("CN")){
                Node next = now.next;
                map.remove(next.number);
                now.next = next.next;

                sb.append(next.number).append("\n");
            } else { // CP
                Node post = now.post;
                map.remove(post.number);
                now.post = post.post;

                sb.append(post.number).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
