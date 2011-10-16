package ticTacToe.Tests;

import java.util.*;

import ticTacToe.Game.Board;
import junit.framework.TestCase;

public class TestBaseBoard extends TestCase {

	Board board;
	
	public void setUp() {
		board = new Board(3, 3);
	}
	
	public void testMarkAt() {
		board.markPosition('O', 4);
		assertEquals('O', board.markAt(4));
	}
	
	public void testGetSpaces() {
		board.markPosition('O', 4);
		board.markPosition('X', 0);
		
		for (int i = 0; i < 9; i++) {
			if (i == 0)
				assertEquals('X', board.markAt(i));
			else if (i == 4)
				assertEquals('O', board.markAt(i));
			else
				assertEquals(0, board.markAt(i));
		}
	}
	
	public void testEmptySpaces() {
		assertEquals(9, board.emptySpaces().size());
	}
	
	public void testIsTaken() {
		for (int i = 0; i <= 8; i++)
			assertEquals(false, board.isTaken(i));
	}
	
	public void testSomePositionsAreEmpty() {
		board.markPosition('O', 4);
		board.markPosition('X', 0);
		
		assertEquals(list(1, 2, 3, 5, 6, 7, 8), board.emptySpaces());
	}

	public void testMakeMark() {
		board.markPosition('O', 4);
		assertEquals(true, board.isTaken(4));
	}
	
	public void testFull() {
		int count = board.emptySpaces().size();
		for (int i = 0; i < count; i++) {
			board.markPosition('O', i);
		}
		assertTrue(board.full());
	}
	
	private List<Integer> list(int... ints) {
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		for (int i : ints)
			integers.add(i);
		
		return integers;
	}
}
