package tictactoe.tests;

import tictactoe.game.Board;
import tictactoe.game.IReferee;
import tictactoe.game.Referee;
import tictactoe.mocks.MockMessenger;
import tictactoe.players.HumanPlayer;
import junit.framework.TestCase;

public class TestHumanPlayer extends TestCase {

    public void testGetChoice() {
        Board board = new Board(3, 3);
        MockMessenger messenger = new MockMessenger(board);
        IReferee referee = new Referee();
        HumanPlayer player = new HumanPlayer('X', messenger);

        int choice = player.getChoice(board);
        assertTrue(referee.validateMove(board, player.getMark(), choice));
        assertEquals(4, choice);
    }
    
}
