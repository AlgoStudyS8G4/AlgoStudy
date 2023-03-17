import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static int N, M;

    static class Node {
        int number;
        Node before, after;
        public Node(int number, Node before, Node after){
            this.number = number;
            this.before = before;
            this.after = after;
        }
    }
    public static int getBefore(LinkedList<Integer> list, int idx){
        if(idx > 0) return list.get(idx-1);
        else return list.getLast();
    }
    public static int getAfter(LinkedList<Integer> list, int idx){
        if(idx == list.size()-1) return list.getFirst();
        else return list.get(idx + 1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < N; i++){
            list.add(sc.nextInt());
        }

        for(int i = 0; i < M; i++){
            String command = sc.next();
            int num = sc.nextInt();
            int nowIdx = list.indexOf(num);

            if(command.equals("BN")){
                int newNum = sc.nextInt();

                System.out.println(getAfter(list, nowIdx));
                if(nowIdx == list.size() -1 )
                    list.add(newNum);
                else
                    list.add(nowIdx, newNum);
            } else if(command.equals("BP")){
                int newNum = sc.nextInt();
                System.out.println(getBefore(list, nowIdx));
                list.add(nowIdx, newNum);
            }else if(command.equals("CN")){
                System.out.println(getAfter(list, nowIdx));
                list.remove(nowIdx+1);
            } else { // CP
                System.out.println(getBefore(list, nowIdx));
                if(nowIdx == 0)
                    list.pollLast();
                else
                    list.remove(nowIdx - 1);
            }
        }
    }
}
