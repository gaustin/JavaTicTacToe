package ticTacToe.Mocks;

import ticTacToe.Game.Board;

public class MockBoard extends Board {

	public boolean markPositionCalled = false;
	public boolean resetCalled = false;

	public MockBoard(int spacesInBoard) {
		super(spacesInBoard);
	}
	
	public void markPosition(char mark, int position) {		
		markPositionCalled = true;
		super.markPosition(mark, position);
	}
	
	public void reset() {
		resetCalled = true;
		super.reset();
	}
}
