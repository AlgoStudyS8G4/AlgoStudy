
class Solution {
    static int count_O;
    static int count_X;
	boolean isBadCount(String[] board) {
		count_O = 0;
	    count_X = 0;
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

        if(isBadCount(board)) return 0;
        String winner = getWinner(board);
        if(winner.equals("O")) {
        	if(count_O - count_X == 1)
        		return 1;
        	else
        		return 0;
        } else if(winner.equals("X")) {
        	if(count_O - count_X == 0)
        		return 1;
        	else
        		return 0;
        } else { // "-" = 승부가 나지 않았다. = 계속한다.
        	return 1;
        }
    }
	private String getWinner(String[] board) {
		char[][] charBoard = new char[board.length][board[0].length()];
		int idx = 0;
		for(String str : board) {
			if(str.equals("OOO")) return "O";
            if(str.equals("XXX")) return "X";
			charBoard[idx++] = str.toCharArray();
		}
		
		for(int j = 0; j < 3; j++) {
			String str = "";
			for(int i = 0; i < 3; i++) {
				str += charBoard[i][j];
			}
            
			if(str.equals("OOO")) return "O";
            if(str.equals("XXX")) return "X";
		}
		
		if(charBoard[0][0] == charBoard[1][1] && charBoard[1][1] == charBoard[2][2] && charBoard[1][1] != '.') return String.valueOf(charBoard[0][0]);
		if(charBoard[0][2] == charBoard[1][1] && charBoard[1][1] == charBoard[2][0] && charBoard[1][1] != '.') return String.valueOf(charBoard[0][0]);
		
		return "-";
	}
}