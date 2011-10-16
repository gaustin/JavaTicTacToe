package ticTacToe.Mocks;

import ticTacToe.Game.Board;
import ticTacToe.Players.NaivePlayer;

public class MockPlayer extends NaivePlayer {
	public boolean getChoiceCalled = false;
	
	public MockPlayer(Character mark) {
		super(mark);
	}

	@Override
	public int getChoice(Board board) {
		getChoiceCalled = true;
		return super.getChoice(board);
	}

}
