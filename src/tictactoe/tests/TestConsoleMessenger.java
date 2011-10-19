package tictactoe.tests;

import junit.framework.TestCase;
import tictactoe.game.Board;
import tictactoe.game.ConsoleMessenger;
import tictactoe.game.IMessenger;
import tictactoe.players.HumanPlayer;

public class TestConsoleMessenger extends TestCase {
	IMessenger messenger;
	HumanPlayer player;
	Board board;
	
	public void setUp() {
		board = new Board(9);
		messenger = new ConsoleMessenger();
		player = new HumanPlayer('X', messenger);
	}
	
	public void testGetMoveFromPlayer() {
//		int choice = player.getChoice(board);
//		assertTrue(0 < choice && choice < 9);
	}
}
