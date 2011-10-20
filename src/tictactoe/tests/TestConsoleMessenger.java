package tictactoe.tests;

import junit.framework.TestCase;
import tictactoe.game.Board;
import tictactoe.game.ConsoleMessenger;
import tictactoe.game.IMessenger;
import tictactoe.players.HumanPlayer;

public class TestConsoleMessenger extends TestCase {
	IMessenger messenger;
	HumanPlayer player;
	Board board;
	
	public void setUp() {
		board = new Board(9);
		messenger = new ConsoleMessenger();
		player = new HumanPlayer('X', messenger);
	}
	
	public void testGetMoveFromPlayer() {
//		int choice = player.getChoice(board);
//		assertTrue(0 < choice && choice < 9);
	}
	
	public void testBoardToString() {
    	String boardRep =
    		" X | O | X \n" +
            "-----------\n" +
            " O | O | X \n" +
            "-----------\n" +
            " 6 | O | 8 \n";
    	
    	board.markPosition('X', 0);
    	board.markPosition('O', 1);
    	board.markPosition('X', 2);
    	board.markPosition('O', 3);
    	board.markPosition('O', 4);
    	board.markPosition('X', 5);
    	board.markPosition('O', 7);
    	
    	assertEquals(boardRep, messenger.boardToString(board));
	}
}
