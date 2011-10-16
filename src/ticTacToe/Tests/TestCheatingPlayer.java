package ticTacToe.Tests;

import junit.framework.TestCase;
import ticTacToe.Game.Board;
import ticTacToe.Players.CheatingPlayer;
import ticTacToe.Players.Player;


public class TestCheatingPlayer extends TestCase {
	private Board board;
	private Player player;
	
	public void setUp() {
		board = new Board(3, 3);
		player = new CheatingPlayer('O');
	}
	
	public void testPlayerCannotModifyBoard() {
		Board boardCopy = board.clone();
		int choice = player.getChoice(boardCopy);
		
		assertTrue(board.markAt(choice) == 0);
	}
}
