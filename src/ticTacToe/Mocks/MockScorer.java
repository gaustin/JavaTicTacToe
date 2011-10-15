package ticTacToe.Mocks;

import ticTacToe.Game.Board;
import ticTacToe.Game.IScorer;

public class MockScorer implements IScorer {

	int[][] winningConfigurations;
	Board<Character> board;
	
	public MockScorer(Board<Character> board, int[][] winningConfigurations) {
		this.winningConfigurations = winningConfigurations;
		this.board = board;
	}

	@Override
	public boolean gameOver() {
		if (board.full())
			return true;
		
		for (int[] configuration : winningConfigurations) {
			if (checkConfigurationForWin(configuration))
				return true;
		}
		
		return false;
	}

	private boolean checkConfigurationForWin(int[] configuration) {
		Character firstMark = board.markAt(0);
		for (int position = 1; position < configuration.length; position++) {
			Character thisMark = board.markAt(position);
			if (thisMark != firstMark) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Character getWinner() {
		return new Character('X');
	}

}
