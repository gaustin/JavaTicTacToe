package tictactoe.tests;

import tictactoe.game.Game;
import tictactoe.mocks.MockBoard;
import tictactoe.mocks.MockMessenger;
import tictactoe.mocks.MockPlayer;
import tictactoe.mocks.MockReferee;
import tictactoe.mocks.MockScorer;
import junit.framework.TestCase;


public class TestGame extends TestCase {
	private Game game;
	private MockPlayer xPlayer;
	private MockPlayer oPlayer;
	private MockBoard board;
	private MockReferee referee;
	private MockScorer scorer;
	private MockMessenger messenger;
	
	public void setUp() {
		board = new MockBoard(9);
		messenger = new MockMessenger();
		xPlayer = new MockPlayer('X');
		oPlayer = new MockPlayer('O');
		referee = new MockReferee();
		scorer = new MockScorer(board);
		game = new Game(board, messenger, referee, scorer, xPlayer, oPlayer);
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
