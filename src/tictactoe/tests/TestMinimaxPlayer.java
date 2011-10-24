package tictactoe.tests;

import tictactoe.game.Board;
import tictactoe.players.MinimaxPlayer;
import junit.framework.TestCase;

public class TestMinimaxPlayer extends TestCase {
	Board board;
	MinimaxPlayer xPlayer;
	MinimaxPlayer oPlayer;
	
	public void setUp() {
		board = new Board(9);
		xPlayer = new MinimaxPlayer('X');
		oPlayer = new MinimaxPlayer('O');
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
	}

	public void testWinInOneMove() {
		board.markPosition('X', 0);
		board.markPosition('X', 1);
		board.markPosition('O', 3);
		board.markPosition('O', 4);
		board.markPosition('X', 5);
		board.markPosition('X', 6);
		board.markPosition('O', 7);
		board.markPosition('O', 8);
		
		assertEquals(2, xPlayer.getChoice(board));
	}
	
	public void testDraw() {
		board.markPosition('X', 0);
		board.markPosition('O', 1);
		board.markPosition('X', 2);
		board.markPosition('O', 3);
		board.markPosition('O', 4);
		board.markPosition('X', 5);
		board.markPosition('O', 6);
		board.markPosition('X', 7);
		
		assertEquals(8, oPlayer.getChoice(board));
	}
	
	public void testHorizontalWin() {
		board.markPosition('X', 0);
		board.markPosition('O', 3);
		board.markPosition('X', 1);
		board.markPosition('O', 4);
		
		assertEquals(2, xPlayer.getChoice(board));
	}
	
	public void testHorizontalWinBlock() {
		/*
		 * Curious that 0 = 'O', 3 = 'X', 1 = 'O' causes the xPlayer to choose 2 correctly.
		 */
		board.markPosition('O', 3);
		board.markPosition('X', 0);
		board.markPosition('O', 4);
		
		assertEquals(5, xPlayer.getChoice(board));
	}
	
	public void testVerticalWin() {
		board.markPosition('X', 0);
		board.markPosition('O', 1);
		board.markPosition('X', 3);
		board.markPosition('O', 4);
		
		assertEquals(6, xPlayer.getChoice(board));
	}
	
	public void testVerticalWinBlock() {
		board.markPosition('O', 0);
		board.markPosition('X', 1);
		board.markPosition('O', 3);
		
		assertEquals(6, xPlayer.getChoice(board));
	}
	
	public void testDiagonalWin() {
		board.markPosition('X', 0);
		board.markPosition('O', 1);
		board.markPosition('X', 4);
		board.markPosition('O', 2);
		
		assertEquals(8, xPlayer.getChoice(board));
	}
	
	public void testDiagonalWinBlock() {
		board.markPosition('O', 0);
		board.markPosition('X', 1);
		board.markPosition('O', 8);
		
		assertEquals(4, xPlayer.getChoice(board));
	}
	
	public void testTakeFork() {
		board.markPosition('X', 4);
		board.markPosition('O', 1);
		board.markPosition('X', 3);
		board.markPosition('O', 5);
		
		int choice = xPlayer.getChoice(board);
		assertTrue(choice == 0 || choice == 6);
	}
}
