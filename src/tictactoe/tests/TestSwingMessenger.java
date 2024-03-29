package tictactoe.tests;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import junit.framework.TestCase;
import tictactoe.game.Board;
import tictactoe.game.IScorer;
import tictactoe.game.SwingMessenger;
import tictactoe.game.TicTacToeScorer;

public class TestSwingMessenger extends TestCase {
    SwingMessenger messenger;
    Board board;
    IScorer scorer;
    
    public void setUp() {
        board = new Board(9);
        scorer = new TicTacToeScorer(board);
        messenger = new SwingMessenger(board, scorer);
    }
    
    public void testWindow() {
        assertEquals("TicTacToe", messenger.getFrame().getTitle());
    }
    
    public void testBoard() {
        JPanel boardPanel = messenger.getBoardPanel();
        int jButtonCount = 0;
        
        for (Component component : boardPanel.getComponents()) {
            if(component instanceof JButton)
                jButtonCount++;
        }
        assertEquals(9, jButtonCount);
    }
    
    public void testMenu() {
        JMenuBar menuBar = messenger.getFrame().getJMenuBar();
        assertEquals(1, menuBar.getMenuCount());
        
        JMenu fileMenu = menuBar.getMenu(0);
        assertEquals("File", fileMenu.getText());
        
        assertEquals(1, fileMenu.getItemCount());
    }
}
