package ticTacToe.Tests;

import ticTacToe.Game.Board;
import junit.framework.TestCase;

public class TestBaseBoard extends TestCase {

	public void testNewBoardIsEmpty() {
		assertEquals(positions(), Board.emptySpaces());
	}

	private char[] positions() {
		return null;
	}
}
