package ticTacToe.Tests;

import ticTacToe.Game.Board;
import junit.framework.TestCase;

public class TestBaseBoard extends TestCase {

	Board board;
	
	public void setUp() {
		board = new Board(3, 3);
	}
	
	public void testNewBoardIsEmpty() {
		assertEquals(9, board.emptySpaces().size());
	}
	
	public void testAllPositionsAreEmpty() {
		for (int i = 0; i <= 7; i++)
			assertEquals(false, board.isTaken(i));
	}
	
	public void testMakeMark() throws Exception {
		assertEquals(true, board.markPosition('O', 4));
		assertEquals(true, board.isTaken(4));
	}
	
	public void testMarkingMarkedCellThrowsException() throws Exception {
		board.markPosition('O', 4);
		
		try {
			board.markPosition('X', 4);
			fail("Expected exception.");
		} catch (Exception ex) {
			// We want this exception...
		}
	}
	
	public void testNoOpMark() throws Exception {
		board.markPosition('O', 4);
		assertEquals(true, board.markPosition('O', 4));
	}
}
