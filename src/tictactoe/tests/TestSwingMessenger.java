package tictactoe.tests;

import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tictactoe.game.Board;
import tictactoe.game.IScorer;
import tictactoe.game.SwingMessenger;
import tictactoe.game.TicTacToeScorer;
import tictactoe.players.PlayerTypes;
import junit.framework.TestCase;

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
        assertEquals(JFrame.EXIT_ON_CLOSE, messenger.getDefaultCloseOperation());
        assertEquals("TicTacToe", messenger.getTitle());
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
        JMenuBar menuBar = messenger.getJMenuBar();
        assertEquals(1, menuBar.getMenuCount());
        
        JMenu fileMenu = menuBar.getMenu(0);
        assertEquals("File", fileMenu.getText());
        
        assertEquals(1, fileMenu.getItemCount());
        
        JMenuItem exitMenuItem = fileMenu.getItem(0);
        assertEquals(KeyEvent.VK_C, exitMenuItem.getMnemonic());
    }
    
    public void testGetPlayerType() {
        assertEquals(PlayerTypes.MinimaxComputer, messenger.getPlayerType('X'));
    }
}
