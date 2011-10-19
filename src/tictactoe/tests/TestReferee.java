package tictactoe.tests;

import junit.framework.TestCase;
import tictactoe.game.Board;
import tictactoe.game.Referee;

public class TestReferee extends TestCase {
	private Referee ref;
	private Board board;
	
	public void setUp() {
		ref = new Referee();
		board = new Board(3, 3);
	}
	
	public void testDoesNotAllowInvalidMove() {
		board.markPosition('O', 4);
		assertEquals(false, ref.validateMove(board, 'X', 4));
	}
	
	public void testAllowValidMove() {
		assertEquals(true, ref.validateMove(board, 'O', 4));
	}
}
