package ticTacToe.Tests;

import junit.framework.TestCase;
import ticTacToe.Game.Board;
import ticTacToe.Players.NaivePlayer;

public class TestPlayer extends TestCase {
	private NaivePlayer player;
	private Board board;
	
	public void setUp() {
		player = new NaivePlayer('O');
		board = new Board(3, 3);
	}

	public void testPlayerHasMark() {
		assertEquals('O', player.getMark());
	}
	
	public void testPlayerChoosesMove() {
		int choice = player.getChoice(board);
		assertTrue(0 <= choice);
	}
}
