package tictactoe.tests;

import tictactoe.game.ConsoleMessenger;
import tictactoe.game.MessengerFactory;
import tictactoe.game.MessengerTypes;
import tictactoe.game.SwingMessenger;
import tictactoe.mocks.MockBoard;
import tictactoe.mocks.MockScorer;
import junit.framework.TestCase;

public class MessengerFactoryTest extends TestCase {
    MockScorer scorer;
    MockBoard board;
    
    public void setUp() {
        board = new MockBoard(9);
        scorer = new MockScorer(board);
    }
    
    public void testSwing() {
        assertTrue(MessengerFactory.create(MessengerTypes.valueOf("SWING"), board, scorer) instanceof SwingMessenger);
    }
    
    public void testConsole() {
        assertTrue(MessengerFactory.create(MessengerTypes.valueOf("CONSOLE"), board, scorer) instanceof ConsoleMessenger);
    }
}
