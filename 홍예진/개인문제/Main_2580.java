import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] rowNumExist, columnNumExist, boxNumExist;
	static int[][] numbers;
	static LinkedList<Integer> blankList, answerList;

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		rowNumExist = new boolean[9][10];
		columnNumExist = new boolean[9][10];
		boxNumExist = new boolean[9][10];
		blankList = new LinkedList<Integer>();
		answerList = new LinkedList<Integer>();
		numbers = new int[9][9];

		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				int num = Integer.parseInt(st.nextToken());
				numbers[i][j] = num;
				if (num == 0) {
					blankList.add(9 * i + j);
					continue;
				}
				// 현재 위치에 속하는 행, 열, 박스에 대해 num 존재 표시
				rowNumExist[i][num] = true;
				columnNumExist[j][num] = true;
				int box = j / 3 + (i / 3) * 3;
				boxNumExist[box][num] = true;
			}
		}
	}

	static void solve() {
		// 아래는 blankList에 대해 전체 순회한다.
		// 1. 위의 빈칸 리스트에서 순차적(idx)으로 인덱스 추출 i = num/9; j = num%9;
		// 2. 현재 인덱스에 대해 가능한(row, col, box 모두 충족) 번호 후보를 ableList[idx]에 저장한다.
		// 모든 가능리스트가 만들어지면 각 좌표에 순차적으로 ableList의 원소를 하나씩 대입하고
		// 모든 blankList의 좌표를 탐색하여 ableList의 원소를 모두 대입하면
		// 그로 만들어지는 numbers 배열을 출력한다.
		LinkedList<Integer>[] ableNumLists = new LinkedList[blankList.size()];
		for (int idx = 0; idx < blankList.size(); idx++) {
			int location = blankList.get(idx); // location : 0 ~ 80
			ableNumLists[idx] = getAbleNumList(location);
		}

		ps(0, ableNumLists);

		for (int location : blankList) {
			int i = location / 9, j = location % 9;
			numbers[i][j] = answerList.poll();
		}

		printNumbers(numbers);

	}

	static LinkedList<Integer> getAbleNumList(int location) {
		LinkedList<Integer> ableNumList = new LinkedList<Integer>();
		for (int a = 1; a <= 9; a++) {
			if (isNumberAble(location, a))
				ableNumList.add(a);
		}
		return ableNumList;
	}

	// location은 숫자가 들어가는 81개 박스를 0~80으로 나타낸것..
	// location에 num이란 번호가 들어갈 수 있는지를 나타내는 메소드.
	static boolean isNumberAble(int location, int num) {
		int i = location / 9, j = location % 9;
		int box = j / 3 + (i / 3) * 3;
		boolean isRowAble = !rowNumExist[i][num];
		boolean isColumnAble = !columnNumExist[j][num];
		boolean isBoxAble = !boxNumExist[box][num];

		return isRowAble && isColumnAble && isBoxAble;
	}

	// idx번째 빈칸에 답을 하나씩 모두 넣을 수 있는 경우 true반환
	static boolean ps(int idx, LinkedList<Integer>[] ableNumLists) {
		if (idx == ableNumLists.length) { // 끝까지 가능한 경로가 나올때 탐색을 끝낸다.
			return true;
		}
		int location = blankList.get(idx);
		int i = location / 9, j = location % 9;
		int box = j / 3 + (i / 3) * 3;
		
		for(int ableNum : ableNumLists[idx]) {
			if(isNumberAble(location, ableNum)) { // 현재 위치에 넣을 수 있을 때 다음 탐색시작.
				// 현재 위치에 속하는 행, 열, 박스에 대해 num 존재 표시
				rowNumExist[i][ableNum] = true;
				columnNumExist[j][ableNum] = true;
				boxNumExist[box][ableNum] = true;
				answerList.add(ableNum);
				
				// 현재 location에 ableNum을 넣은 경우 중 끝까지 답을 찾은 경우 이 이상의 탐색을 종료한다.
				// 이때 answerList에는 답이 들어있다.
				if(ps(idx+1, ableNumLists))
					return true;
				
				rowNumExist[i][ableNum] = false;
				columnNumExist[j][ableNum] = false;
				boxNumExist[box][ableNum] = false;
				answerList.pollLast();
			}
		}
		// 모든 경우를 탐색한 후에도 true가 발견되지 않은경우.
		return false;
	}

	static void printNumbers(int[][] numbers) {
		StringBuilder sb = new StringBuilder();
		for (int[] nums : numbers) {
			String s = "";
			for (int num : nums) {
				s += num + " ";
			}
			sb.append(s.strip() + "\n");
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
}
