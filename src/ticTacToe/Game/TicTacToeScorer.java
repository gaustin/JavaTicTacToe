package ticTacToe.Game;


public class TicTacToeScorer implements IScorer<Character> {

	private Character winner = null;
	private int[][] winningConfigurations;
	private Board<Character> board;
	
	public TicTacToeScorer(Board<Character> board, int[][] winningConfigurations) {
		this.winningConfigurations = winningConfigurations;
		this.board = board;
	}

	@Override
	public boolean isGameOver() {
		if (board.full() ||
			getWinner() != null)
			return true;
		
		return false;
	}

	@Override
	public Character getWinner() {
		if (winner != null)
			return winner;
		
		for (int[] configuration : winningConfigurations) {
			if (checkConfigurationForWin(configuration)) {
				winner = (Character)board.markAt(configuration[0]);
				return winner;	
			}
		}
		
		return null;
	}

	public boolean isDraw() {
		return board.full() && getWinner() == null;
	}
	
	private boolean checkConfigurationForWin(int[] configuration) {
		Character mark = board.markAt(0);
		
		return mark == board.markAt(configuration[0]) && mark == board.markAt(configuration[1]) && mark == board.markAt(configuration[2]);
	}
}