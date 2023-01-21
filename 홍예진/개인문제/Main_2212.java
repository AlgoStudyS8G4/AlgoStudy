import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main{
    static int N, K;
    static int[] way_points;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        way_points = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            way_points[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(way_points);
        
        int total = way_points[N-1] - way_points[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < N-1; i++){
            int diff = way_points[i+1] - way_points[i];
            pq.add(diff);
        }
        
        
        for(int i = 0; !pq.isEmpty() && i < K-1; i++){
        	total -= pq.poll();
        }
        
        System.out.println(total);
    }
}