class Solution {
    public int cal(int i, int brown, int yellow){
        return i*i - i*(brown + 4)/2 + yellow + brown;
    }
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        // 테두리 1줄 => brown = 2n+2m - 4 
        // yellow = n*m - brown = (n-2)*(m-2) = n*m -2(n+m) + 4
        // n(세로) <= m (가로)
        // n+m = (brown + 4)/2
        // n*m = yellow + brown = n* ((brown + 4)/2 - n) = -n^2 + n(brown+4)/2
        // n^2 - n(brown+4)/2 + yellow + brown = 0;
        // n = (n(brown+4)/2 + root((n(brown+4)/2)^2 -4*(yellow+brown)))/2
        int n = 1;
        int m = 1;
        for(int i = 1; i < 2000000; i++){
            if(cal(i, brown, yellow) == 0){
                n = i;
                m = (brown+4)/2 - n;
                break;
            }   
        }
        
        answer[0] = m;
        answer[1] = n;
        
        return answer;
    }
}
