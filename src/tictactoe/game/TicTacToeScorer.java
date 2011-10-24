package tictactoe.game;


public class TicTacToeScorer implements IScorer {

	private char winner = 0;
	private int[][] winningConfigurations = {
			{ 0, 1, 2 }, // Horizontals
			{ 3, 4, 5 },
			{ 6, 7, 8 },
			{ 0, 3, 6 }, // Verticals
			{ 1, 4, 7 },
			{ 2, 5, 8 },
			{ 6, 4, 2 }, // Diagonals
			{ 8, 4, 0 }
	};
	private Board board;
	
	public TicTacToeScorer(Board board) {
		this.board = board;
	}

	public void reset() {
		winner = 0;
	}
	
	@Override
	public boolean isGameOver() {
		if (board.full() ||
			getWinner() != 0)
			return true;
		
		return false;
	}

	@Override
	public char getWinner() {
		if (winner != 0)
			return winner;
		
		for (int[] configuration : winningConfigurations) {
			if (checkConfigurationForWin(configuration)) {
				winner = board.markAt(configuration[0]);
				return winner;	
			}
		}
		
		return 0;
	}

	public boolean isDraw() {
		return board.full() && getWinner() == 0;
	}
	
	private boolean checkConfigurationForWin(int[] configuration) {
		char mark = board.markAt(configuration[0]);
		
		return mark != '\0' && mark == board.markAt(configuration[0]) && mark == board.markAt(configuration[1]) && mark == board.markAt(configuration[2]);
	}
}