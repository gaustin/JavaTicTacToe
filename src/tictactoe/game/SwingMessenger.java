package tictactoe.game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tictactoe.players.Player;
import tictactoe.players.PlayerTypes;
import tictactoe.ui.swing.PlayerTypeDialog;
import tictactoe.ui.swing.ResultsDialog;

public class SwingMessenger implements IMessenger {
    // Eclipse is psychotic about adding this...
    private static final long serialVersionUID = 4824133673451387974L;
    
    private JFrame frame;
    private Board board;
    private IScorer scorer;
    private JPanel boardPanel;
    private boolean waitForUserInput;
    private int lastMove;
    private boolean doReplay;
    private String emptyBoardMark = "-";
    
    private IMessenger messenger = this;
    
    public JFrame getFrame() {
    	return frame;
    }
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public SwingMessenger(Board board, IScorer scorer) {
        this.board = board;
        this.scorer = scorer;
        frame = new JFrame();
        waitForUserInput = false;
        doReplay = false;
        lastMove = -1;
        initWindow();
    }
    
    private void initWindow() {
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        panel.setLayout(null);
        
        initMenu();
        initBoardDisplay();
        
        frame.setTitle("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 400));
        
        // Center
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        file.add(exitMenuItem);
        
        menuBar.add(file);
        
        frame.setJMenuBar(menuBar);
    }
    
    private void initBoardDisplay() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        for (int i = 0; i <= 8; i++) {
            final JButton button = new JButton(emptyBoardMark);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                	messenger.choiceMade(Integer.parseInt(button.getName()));
                }
            });
            button.setName("" + i);
            boardPanel.add(button);
        }
        
        frame.add(boardPanel);
        boardPanel.setVisible(true);
    }
    
    @Override
    public void displayResults() {
        ResultsDialog rd = new ResultsDialog(scorer);
        rd.setVisible(true);
        doReplay = rd.getDoReplay();
    }

    @Override
    public boolean doPlayAgain() {
        return doReplay;
    }

    @Override
    public int getMoveFromPlayer(Player player) {
        
        waitForUserInput = true;
        while (waitForUserInput) {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return lastMove;
    }

    @Override
    public void choiceMade(int choice) {
    	waitForUserInput = false;
    	lastMove = choice;
    }
    
    @Override
    public void informPlayerOfInvalidChoice() {
        Toolkit.getDefaultToolkit().beep();
    }

    @Override
    public void promptPlayerForMove(Player player) {
    }

    @Override
    public void updateBoardDisplay() {
        JButton button;
        char mark;

        for(Component component : boardPanel.getComponents())
        {
            if(component instanceof JButton)
            {
            	button = (JButton)component;
                int position = Integer.parseInt(button.getName());
                mark = board.markAt(position);
                if(mark != 0) {
                	button.setText("" + mark);
                } else {
                	button.setText(emptyBoardMark);
                }
            }
        }
    }
    
    public PlayerTypes getPlayerType(char mark) {
        PlayerTypeDialog ptd = new PlayerTypeDialog(mark);
        ptd.setVisible(true);
        
        PlayerTypes selectedPlayerType = ptd.getPlayerType();
        
        return  selectedPlayerType == null ? PlayerTypes.MinimaxComputer : selectedPlayerType;
    }
}
