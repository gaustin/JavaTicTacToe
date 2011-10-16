package ticTacToe.Mocks;

import ticTacToe.Game.Board;
import ticTacToe.Game.TicTacToeScorer;

public class MockScorer extends TicTacToeScorer {

	public boolean isGameOverCalled = false;
	public boolean resetCalled = false;

	public MockScorer(Board board) {
		super(board);
	}
	
	public boolean isGameOver() {
		isGameOverCalled = true;
		return super.isGameOver();
	}

	public void reset() {
		resetCalled = true;
		super.reset();
	}
}
