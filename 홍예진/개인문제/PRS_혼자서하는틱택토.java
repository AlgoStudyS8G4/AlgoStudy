
class Solution {
	boolean isBadCount(String[] board) {
		int count_O = 0;
		int count_X = 0;
		for(String str : board) {
			for(char chr : str.toCharArray()) {
				if(chr == 'O')
					count_O++;
				if(chr == 'X')
					count_X++;
			}
		}
		
		int diff = count_O - count_X;
		if(diff == 0 || diff == 1) return false;
		return true;
	}
    public int solution(String[] board) {
        int answer = 1;
        if(isBadCount(board)) return 0;
        if(isEndGame(board)) return 0;
        return answer;
    }
	private boolean isEndGame(String[] board) {
		char[][] charBoard = new char[board.length][board[0].length()];
		int idx = 0;
		// 가로로 연속
		for(String str : board) {
			if(str.equals("OOO") || str.equals("XXX")) return true;
			charBoard[idx++] = str.toCharArray();
		}
		
		// 세로로 연속
		for(int j = 0; j < 3; j++) {
			String str = "";
			for(int i = 0; i < 3; i++) {
				str += charBoard[i][j];
			}
			if(str.equals("OOO") || str.equals("XXX")) return true;
		}
		
		// 대각선으로 연속
		if(charBoard[0][0] == charBoard[1][1] && charBoard[1][1] == charBoard[2][2] && charBoard[1][1] != '.') return true;
		if(charBoard[0][2] == charBoard[1][1] && charBoard[1][1] == charBoard[2][0] && charBoard[1][1] != '.') return true;
		
		return false;
	}
}
