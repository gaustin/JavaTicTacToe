package ticTacToe.Tests;

import java.util.List;

import junit.framework.TestCase;
import ticTacToe.Game.Board;
import ticTacToe.Game.TicTacToeScorer;
import ticTacToe.Game.NaivePlayer;

public class TestScorer extends TestCase{

	private TicTacToeScorer scorer;
	private Board<Character> board;
	private NaivePlayer xPlayer;
	private NaivePlayer oPlayer;
	
	private int[][] winningConfigurations = {
			{ 0, 1, 2 },
			{ 3, 4, 5 },
			{ 6, 7, 8 },
			{ 0, 3, 6 },
			{ 1, 4, 7 },
			{ 2, 5, 8 },
			{ 6, 4, 2 },
			{ 8, 4, 0 }
	};
	
	public void setUp(){
		board = new Board<Character>(3, 3);
		scorer = new TicTacToeScorer(board, winningConfigurations);
		xPlayer = new NaivePlayer((Character)'X');
		xPlayer = new NaivePlayer((Character)'O');
	}
	
	public void testGameOverByWinner() {
		for (int i : winningConfigurations[0]) {
			board.markPosition((Character)'X', i);
			if (i < 2)
				assertFalse(scorer.isGameOver());
			else
				assertTrue(scorer.isGameOver());
		}
		
	}

	public void testGameOverBoardFilled() {
		List<Integer> spaces = board.emptySpaces();
		for (int i : spaces)
			board.markPosition(new Character((char)('A' + i)), i);
		assertTrue(scorer.isGameOver());
	}
	
	public void testGetWinner() {
		for (int i : winningConfigurations[0]) {
			board.markPosition((Character)'X', i);
		}
		assertEquals((Character)'X', scorer.getWinner());
	}
	
	public void testIsTie() {
		Character xMark = new Character('X');
		Character oMark = new Character('O');
		
		board.markPosition(xMark, 0);
		board.markPosition(oMark, 1);
		board.markPosition(xMark, 2);
		board.markPosition(oMark, 3);
		board.markPosition(oMark, 4);
		board.markPosition(xMark, 5);
		board.markPosition(oMark, 6);
		board.markPosition(xMark, 7);
		board.markPosition(oMark, 8);
		assertEquals(null, scorer.getWinner());
		assertTrue(scorer.isDraw());
	}
}
