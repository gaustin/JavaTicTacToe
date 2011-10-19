package tictactoe.tests;

import junit.framework.TestCase;
import tictactoe.game.Board;
import tictactoe.players.NaivePlayer;

public class TestPlayer extends TestCase {
	private NaivePlayer player;
	private Board board;
	
	public void setUp() {
		board = new Board(3, 3);
		player = new NaivePlayer('O');
	}

	public void testPlayerHasMark() {
		assertEquals('O', player.getMark());
	}
	
	public void testPlayerChoosesMove() {
		int choice = player.getChoice(board);
		assertTrue(0 <= choice);
	}
}
