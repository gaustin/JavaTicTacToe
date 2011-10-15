package ticTacToe.Tests;

import junit.framework.TestCase;
import ticTacToe.Game.Board;
import ticTacToe.Game.NaivePlayer;

public class TestPlayer extends TestCase {
	private NaivePlayer player;
	private Board board;
	
	public void setUp() {
		player = new NaivePlayer('O');
		board = new Board(3, 3);
	}

	public void testPlayerHasMark() {
		assertEquals(new Character('O'), player.getMark());
	}
	
	public void testPlayerChoosesMove() {
		int choice =  player.getChoice(board);
		assertTrue(choice > 0 && choice < 9);
	}
}
