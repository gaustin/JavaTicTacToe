package ticTacToe.Mocks;

import ticTacToe.Game.Board;

public class MockBoard extends Board {

	public boolean markPositionCalled = false;
	public int markPositionCalledCount = 0;

	public MockBoard(int spacesInBoard) {
		super(spacesInBoard);
	}
	
	public void markPosition(char mark, int position) {		
		markPositionCalled = true;
		markPositionCalledCount += 1;
		super.markPosition(mark, position);
	}
}
