import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static int N, M, K, A, B, ans;
	public static int[] a, b, t;
	public static PriorityQueue<Customer> arrive;
	public static PriorityQueue<Integer> timeLine;

	public static void solve() {
		// 1. 접수 창구에서 시간이 된 사람들을 정비-기다림 큐에 넣는다.
		// 2. 정비 창구에서 시간이 된 사람들을 내보낸다.
		// 3. 도착 큐에서 도착-기다림 큐에 넣는다.
		// 4. 기다림 큐에서 각 창구로 사람을 넣는다.
		boolean[] usingA = new boolean[N];
		boolean[] usingB = new boolean[M];

		PriorityQueue<Customer> waitingReceipt = new PriorityQueue<>((c1, c2) -> (c1.num - c2.num)); // 접수 창구를 대기하고 있는
																										// 고객. 고객번호가 낮은
																										// 순서대로 우선 접수
		PriorityQueue<Customer> receipt = new PriorityQueue<>((c1, c2) -> (c1.time - c2.time)); // 접수 창구를 이용중인 고객. 접수가
																								// 끝나는 대로 아웃
		PriorityQueue<Customer> waitingRepair = new PriorityQueue<>(
				(c1, c2) -> (c1.time == c2.time ? c1.A - c2.A : c1.time - c2.time)); // 정비창구를 이용중인 고객
		PriorityQueue<Customer> repair = new PriorityQueue<>((c1, c2) -> (c1.time - c2.time)); // 정비창구를 이용중인 고객

		int oldTime = -1;
		// 각 창구에서 자리가 나는 시간을 확인한다.
		while (!timeLine.isEmpty()) {
			int time = timeLine.poll();
			if (time == oldTime)
				continue;
//			System.out.println(timeLine);
//			System.out.println("시간 : "+time);

			while (!repair.isEmpty() && time == repair.peek().time) {
				Customer c = repair.poll();
				usingB[c.B - 1] = false;
			}
			while (!receipt.isEmpty() && time == receipt.peek().time) {
				Customer c = receipt.poll();
				usingA[c.A - 1] = false;
				waitingRepair.add(c);
			}
			while (!arrive.isEmpty() && time == arrive.peek().time) {
				Customer c = arrive.poll();
				waitingReceipt.add(c);
			}

			// 대기인원이 존재하고, 사용가능한 정비구가 존재한다면
			// 창구 번호가 작은 곳에 가서 접수한다.
			while (repair.size() < M && !waitingRepair.isEmpty()) {
				Customer c = waitingRepair.poll();
				for (int i = 0; i < M; i++) {
					if (usingB[i])
						continue;
					usingB[i] = true;
					c.B = i + 1;
					if (c.A == A && c.B == B) { // 내가 찾는 창구를 이용한 고객이라면 고객 번호를 더한다.
						ans += c.num;
					}
					c.time = time + b[i]; // i번 창구가 끝나는 시간.
					timeLine.add(c.time);
					repair.add(c);
					break;
				}
			}

			// 대기인원이 존재하고, 사용가능한 접수창구가 존재한다면
			// 창구 번호가 작은 곳에 가서 접수한다.
			while (receipt.size() < N && !waitingReceipt.isEmpty()) {
				Customer c = waitingReceipt.poll();
				for (int i = 0; i < N; i++) {
					if (usingA[i])
						continue;
					usingA[i] = true;
					c.A = i + 1;
					c.time = time + a[i];
					timeLine.add(c.time);
					receipt.add(c);
					break;
				}
			}

//			System.out.println(arrive);
//			System.out.println(waitingReceipt);
//			System.out.println(receipt);
//			System.out.println(waitingRepair);
//			System.out.println(repair);

			oldTime = time;
		}

		// 현재시간에 도착한 사람을 기다림 배열에 추가한다.

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arrive = new PriorityQueue<>((c1, c2) -> (c1.time - c2.time));
			timeLine = new PriorityQueue<>();
			a = new int[N];
			b = new int[M];
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
//				t[i] = Integer.parseInt(st.nextToken());
				Customer c = new Customer();
				c.num = i + 1;
				c.time = Integer.parseInt(st.nextToken());
				arrive.add(c);
				timeLine.add(c.time);
			}
//			System.out.println(arrive);

			solve();

			System.out.print("#" + tc + " " + (ans == 0 ? -1 : ans) + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

}

class Customer {
	int num, time, A, B;

	@Override
	public String toString() {
		return "Customer [num=" + num + ", time=" + time + ", A=" + A + ", B=" + B + "]";
	}

}
