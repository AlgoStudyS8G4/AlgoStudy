import java.util.Arrays;

// 정확성 70.
class Solution {
	public int solution(String name) {
		int answer = 0;

		char[] temp = new char[name.length()];
		Arrays.fill(temp, 'A');

		int idx = 0;
		while (true) {
			char target = name.charAt(idx);
			if (temp[idx] != target) {
				answer += Math.min(target - 'A', 'Z' - target + 1);
				temp[idx] = target;
			}
            // System.out.println(Arrays.toString(temp));

			if (String.valueOf(temp).equals(name))
				break;

			for (int i = 1; i < name.length(); i++) {
				int right = (idx + i) % name.length();
				int left = (idx + (name.length() - 1) * i) % name.length();

				if (temp[right] != name.charAt(right)) {
                    if(right > idx) answer += right - idx;
                    else answer += name.length() - right + idx;
					idx = right;
					break;
				} else if (temp[left] != name.charAt(left)) {
					if(left < idx) answer += idx - left;
                    else answer += name.length() - left + idx;
                    idx = left;
					break;
				}
			}
		}

		return answer;
	}
}
