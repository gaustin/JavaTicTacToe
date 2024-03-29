package tictactoe.tests;

import junit.framework.TestCase;
import tictactoe.game.Board;
import tictactoe.players.ComputerPlayer;
import tictactoe.players.Player;
import tictactoe.players.strategies.CheatingStrategy;


public class TestCheatingPlayer extends TestCase {
    private Board board;
    private Player player;
    
    public void setUp() {
        board = new Board(3, 3);
        player = new ComputerPlayer('O', new CheatingStrategy());
    }
    
    public void testPlayerCannotModifyBoard() {
        Board boardCopy = board.copy();
        int choice = player.getChoice(boardCopy);
        
        assertTrue(board.markAt(choice) == 0);
    }
}
