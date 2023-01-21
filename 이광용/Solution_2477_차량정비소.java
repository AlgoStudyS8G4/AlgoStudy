package SWEA_AD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2477_차량정비소 {
	static class Customer  implements Comparable<Customer>{
		int customer_no, tk, reception_no = 0 ,
				repair_no = 0, a_t = 0, b_t = 0, moveToBW_t = 0;
		public Customer(int customer_no, int tk) {
			this.customer_no = customer_no;
			this.tk = tk;
		}
		@Override
		public int compareTo(Customer o) {
			if(this.moveToBW_t == o.moveToBW_t)
				return this.reception_no - o.reception_no;
			else
				return 0;
		}
	}
	private static int N, M, K, A, B;
	private static int[] a,b;
	private static Customer[] customers;
	private static PriorityQueue<Customer> aWaitQ;
	private static Queue<Customer> bWaitQ;
	private static int time;
	private static Customer[] aProcessArr;
	private static Customer[] bProcessArr;
	private static int ANS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc =1; tc<= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 
			M = Integer.parseInt(st.nextToken()); 
			K = Integer.parseInt(st.nextToken()); 
			A = Integer.parseInt(st.nextToken()); 
			B = Integer.parseInt(st.nextToken());
			a = new int[N+1];//접수
			b = new int[M+1];//정비
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			} 
			aWaitQ = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					return o1.customer_no - o2.customer_no;
				}

			});

			bWaitQ =new LinkedList<>(); 
					
//					new PriorityQueue<>(new Comparator<Customer>() {
//				@Override
//				public int compare(Customer o1, Customer o2) {
////					if(o1.moveToBW_t == o2.moveToBW_t)
////						return o1.reception_no - o2.reception_no;
////					else 
//						return 0;
//				}
//			});
			aProcessArr = new Customer[N+1];
			bProcessArr = new Customer[M+1];
			customers = new Customer[K+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= K; i++) {
				customers[i] = new Customer(i, Integer.parseInt(st.nextToken()));
			} //end of 입력
			time = 0;
			ANS = 0;
			start();
			ANS = (ANS == 0) ? -1 : ANS;
			System.out.println("#" + tc + " " + ANS);
		}	
	}
	private static void start() {
		int idx = 1;
		while(true) {
			//해당 타임 도착 손님
			for(int i = idx; i <= K; i++) {
				if (customers[i].tk == time) {
					aWaitQ.add(customers[i]);
					idx=i+1;
				}
			}
			//bP 정리하면서 몇개의 빈자리가 있는지 체크
			int b_remain = 0;
			for(int i = 1; i <= M; i++) {
				if(bProcessArr[i]!=null) {
					bProcessArr[i].b_t -= 1;
					if(bProcessArr[i].b_t == 0) {
						bProcessArr[i] = null;//원소 제거
						b_remain++;
					}
				}else {
					b_remain++;
				}
			}
			//aP 정리하면서 몇개의 빈자리가 있는지 체크
			int a_remain = 0;
			for(int i = 1; i <= N; i++) {
				if(aProcessArr[i]!=null) {
					aProcessArr[i].a_t -= 1;
					if(aProcessArr[i].a_t == 0) {
						//bW으로 보내야함
						aProcessArr[i].moveToBW_t = time;
						bWaitQ.add(aProcessArr[i]);
						aProcessArr[i] = null;//원소 제거
						a_remain++;
					}
				}else {
					a_remain++;
				}
			}

			//bW 정리
			while(b_remain-- > 0 && bWaitQ.size() > 0) {
				Customer customer = bWaitQ.poll();
				for(int i  = 1 ; i <= M; i++) {
					if(bProcessArr[i] == null) {
						customer.repair_no = i;
						customer.b_t = b[i];
						bProcessArr[i] = customer;
						//정답에 해당하는지 체크
						if(customer.reception_no == A && customer.repair_no == B) {
							ANS += customer.customer_no;
						}
						break;
					}
				}
			}

			//aW 정리
			while(a_remain-- > 0 && aWaitQ.size() > 0) {
				Customer customer = aWaitQ.poll();
				for(int i  = 1 ; i <= N; i++) {
					if(aProcessArr[i] == null) {
						customer.reception_no = i;
						customer.a_t = a[i];
						aProcessArr[i] = customer;
						break;
					}
				}
			}

			time++;

			//기저조건
			a_remain = 0;
			for(int i = 1; i <= N; i++) {
				if(aProcessArr[i]==null) {
					a_remain++;
				}
			}
			if(idx > K && a_remain == N && aWaitQ.isEmpty() && bWaitQ.isEmpty()) {
				break;
			}
		}//end of while 
	}//end of start()
}

