package ticTacToe.Mocks;

import ticTacToe.Game.Board;
import ticTacToe.Game.Referee;

public class MockReferee extends Referee {

	public boolean validateMoveCalled = false;
	public int validateMoveCalledCount = 0;
	
	public boolean validateMove(Board board, char mark, int position) {
		validateMoveCalled = true;
		validateMoveCalledCount += 1;
		return super.validateMove(board, mark, position);
	}

}
