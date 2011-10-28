package tictactoe.tests;

import junit.framework.TestCase;
import tictactoe.game.Board;
import tictactoe.players.ComputerPlayer;
import tictactoe.players.strategies.RandomStrategy;

public class TestPlayer extends TestCase {
    private ComputerPlayer player;
    private Board board;
    
    public void setUp() {
        board = new Board(3, 3);
        player = new ComputerPlayer('O', new RandomStrategy());
    }

    public void testPlayerHasMark() {
        assertEquals('O', player.getMark());
    }
    
    public void testPlayerChoosesMove() {
        int choice = player.getChoice(board);
        assertTrue(0 <= choice);
    }
}
