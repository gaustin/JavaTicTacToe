package tictactoe.mocks;

import java.io.IOException;

import tictactoe.game.Board;
import tictactoe.game.IMessenger;
import tictactoe.players.Player;
import tictactoe.players.PlayerTypes;

public class MockMessenger implements IMessenger {

	public boolean getMoveFromPlayerCalled = false;
	public boolean promptPlayerForMoveCalled = false;
	public boolean displayBoardCalled = false;
	public boolean invalidChoiceCalled = false;
	public boolean boardToStringCalled = false;
	public boolean getPlayerTypeCalled = false;
	
	@Override
	public int getMoveFromPlayer(Player player) {
		getMoveFromPlayerCalled = true;
		return 4;
	}

	@Override
	public void promptPlayerForMove(Player player) {
		promptPlayerForMoveCalled = true;
	}

	@Override
	public void displayBoard(Board board) {
		displayBoardCalled = true;
	}

	@Override
	public void informPlayerOfInvalidChoice() {
		invalidChoiceCalled = true;
	}

	@Override
	public String boardToString(Board board) {
		boardToStringCalled = true;
		return "";
	}

	@Override
	public PlayerTypes getPlayerType(char mark) throws IOException {
		getPlayerTypeCalled  = true;
		return PlayerTypes.Human;
	}
	
}
