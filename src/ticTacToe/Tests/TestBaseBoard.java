package ticTacToe.Tests;

import java.util.*;

import ticTacToe.Game.Board;
import junit.framework.TestCase;

public class TestBaseBoard extends TestCase {

	Board board;
	
	public void setUp() {
		board = new Board(3, 3);
	}
	
	public void testEmptySpaces() {
		assertEquals(9, board.emptySpaces().size());
	}
	
	public void testIsTaken() {
		for (int i = 0; i <= 8; i++)
			assertEquals(false, board.isTaken(i));
	}
	
	public void testSomePositionsAreEmpty() throws Exception {
		board.markPosition('O', 4);
		board.markPosition('X', 0);
		
		assertEquals(list(1, 2, 3, 5, 6, 7, 8), board.emptySpaces());
	}
	
	private List<Integer> list(int... ints) {
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		for (int i : ints)
			integers.add(i);
		
		return integers;
	}

	public void testMakeMark() throws Exception {
		board.markPosition('O', 4);
		assertEquals(true, board.isTaken(4));
	}
	
	public void testFull() throws Exception {
		int count = board.emptySpaces().size();
		for (int i = 0; i < count; i++) {
			board.markPosition('O', i);
		}
		assertTrue(board.full());
	}
}
