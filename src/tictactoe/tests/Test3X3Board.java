package tictactoe.tests;

import tictactoe.game.Board;
import junit.framework.TestCase;

public class Test3X3Board extends TestCase {

    Board board;
    int boardSize = 9;
    
    public void setUp() {
        board = new Board(9);
    }
    
    public void testMarkAt() {
        board.markPosition('O', 4);
        assertEquals('O', board.markAt(4));
    }
    
    public void testGetSpaces() {
        board.markPosition('O', 4);
        board.markPosition('X', 0);
        
        for (int i = 0; i < boardSize; i++) {
            if (i == 0)
                assertEquals('X', board.markAt(i));
            else if (i == 4)
                assertEquals('O', board.markAt(i));
            else
                assertEquals(0, board.markAt(i));
        }
    }
    
    public void testEmptySpaces() {
        assertEquals(boardSize, board.emptySpaces().size());
    }
    
    public void testIsTaken() {
        for (int i = 0; i < boardSize; i++)
            assertEquals(false, board.isTaken(i));
    }
    
    public void testSomePositionsAreEmpty() {
        board.markPosition('O', 4);
        board.markPosition('X', 0);
        
        assertTrue(board.emptySpaces().size() > 0);
    }

    public void testMakeMark() {
        board.markPosition('O', 4);
        assertEquals(true, board.isTaken(4));
    }
    
    public void testFull() {
        int count = board.emptySpaces().size();
        for (int i = 0; i < count; i++) {
            board.markPosition('O', i);
        }
        assertTrue(board.full());
    }

    public void testLoadNullState() {
        board.loadState(null);
        
        for (int i = 0; i <= 8; i++) {
        	assertEquals(0, board.markAt(i));
        }
    }
    public void testLoadState() {
        String state = "XXXXXXXXX";
        
        board.loadState(state);
        
        for (int i = 0; i <= 8; i++) {
            assertEquals('X', board.markAt(i));
        }
    }
    
    public void testSerializeState() {
        String expectedState = "XXXXXXXXX";
        
        for (int i = 0; i <= 8; i++) {
            board.markPosition('X', i);
        }
        
    	String state = board.serializeState();
    	assertEquals(expectedState, state);
    }
}
