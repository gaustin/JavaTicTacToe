package ticTacToe.Tests;

import ticTacToe.Game.Game;
import ticTacToe.Mocks.MockBoard;
import ticTacToe.Mocks.MockPlayer;
import ticTacToe.Mocks.MockReferee;
import ticTacToe.Mocks.MockScorer;
import junit.framework.TestCase;


public class TestGame extends TestCase {
	private Game game;
	private MockPlayer xPlayer;
	private MockPlayer oPlayer;
	private MockBoard board;
	private MockReferee referee;
	private MockScorer scorer;
	
	public void setUp() {
		board = new MockBoard(9);
		xPlayer = new MockPlayer('X');
		oPlayer = new MockPlayer('O');
		referee = new MockReferee();
		scorer = new MockScorer(board);
		game = new Game(board, referee, scorer, xPlayer, oPlayer);
	}
	
	public void testGamePlay() {		
		game.play();
		
		assertTrue(xPlayer.getChoiceCalled);
		assertTrue(oPlayer.getChoiceCalled);
		assertTrue(referee.validateMoveCalled);
		assertTrue(board.markPositionCalled);
		assertTrue(scorer.isGameOverCalled);
	}
	
	public void testReset() {
		game.reset();
		
		assertTrue(board.resetCalled);
		assertTrue(scorer.resetCalled);
	}
}
