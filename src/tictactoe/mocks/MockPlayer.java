package tictactoe.mocks;

import tictactoe.game.Board;
import tictactoe.players.NaivePlayer;

public class MockPlayer extends NaivePlayer {
	public boolean getChoiceCalled = false;
	
	public MockPlayer(char mark) {
		super(mark);
	}

	@Override
	public int getChoice(Board board) {
		getChoiceCalled = true;
		return super.getChoice(board);
	}

}
