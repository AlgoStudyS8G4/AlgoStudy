import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int H, W;
	static int[] world;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		world = new int[W + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			world[i] = Integer.parseInt(st.nextToken());
		}

		// 왼쪽 기둥이 될 어떤 지점 left에서 시작한다.
		// 오른쪽 기둥이 될 어떤 지점 right를 찾는다.
		// 오른쪽 기둥이 left보다 기둥높이가 크다면, 해당 공간은 최대 left까지 물이 찬다.
		// left보다 기둥높이가 작지만 right가 오른쪽 기둥이 되는 경우 = 오른쪽 기둥의 바로 오른쪽 값이 작은 경우
		int left = 0;
		int sum = 0;
		while (left < W) {
			int right = left + 1;
			int max = right;
			while (right < W && world[left] > world[right]) {
				max = world[max] < world[right] ? right : max;
				right++;
			}
//			System.out.println("left : "+left+" right : "+right);

			// 끝까지 가도 left보다 큰 기둥은 없다.
			if (right == W) {
				// left~right 사이 그나마 큰 기둥 만큼 빗물이 고인다.
				for (int idx = left+1; idx < max; idx++) {
					sum += world[max] - world[idx];
				}
				left = right;
				continue;
			}
			
			// left보다 큰 기둥이 있다. 그 사이는 left만큼 물이 찬다.
			if(world[left] <= world[right]) {
				for (int idx = left+1; idx < right; idx++) {
					sum += world[left] - world[idx];
				}
				left = right;
			}

		}

		System.out.println(sum);
	}
}