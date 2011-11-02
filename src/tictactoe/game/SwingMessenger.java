package tictactoe.game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;
import tictactoe.players.PlayerTypes;

public class SwingMessenger extends JFrame implements IMessenger {
    // Eclipse is psychotic about adding this...
    private static final long serialVersionUID = 4824133673451387974L;
    
    private Board board;
    private IScorer scorer;
    private JPanel boardPanel;

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public SwingMessenger(Board board, IScorer scorer) {
        this.board = board;
        this.scorer = scorer;
        initWindow();
    }
    
    private void initWindow() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);
        
        initMenu();
        initBoardDisplay();
        
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 400));
        
        // Center
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_C);
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        file.add(exitMenuItem);
        
        menuBar.add(file);
        
        setJMenuBar(menuBar);
    }
    
    private void initBoardDisplay() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        for (int i = 0; i <= 8; i++) {
            JButton button = new JButton("-");
            button.setName("" + i);
            boardPanel.add(button);
        }
        
        add(boardPanel);
        boardPanel.setVisible(true);
    }
    
    @Override
    public void displayResults() {
        System.out.println("displayResults");
        System.out.println("winner: " + scorer.getWinner());
    }

    @Override
    public boolean doPlayAgain() {
        System.out.println("doPlayAgain");
        return false;
    }

    @Override
    public int getMoveFromPlayer(Player player) {
        System.out.println("getMoveFromPlayer " + player.getMark());
        return 0;
    }

    @Override
    public void informPlayerOfInvalidChoice() {
        System.out.println("informPlayerOfInvalidChoice");
    }

    @Override
    public void promptPlayerForMove(Player player) {
        System.out.println("promptPlayerForMove " + player.getMark());
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
                }
            }
        }
    }
    
    public PlayerTypes getPlayerType(char mark) {
        return PlayerTypes.MinimaxComputer;
    }
    
    // TODO: Just for testing purposes.
    public static void main(String[] args) throws IOException {
        
        Board board = new Board(9);
        IScorer scorer = new TicTacToeScorer(board);
        SwingMessenger messenger = new SwingMessenger(board, scorer);
        IReferee referee = new Referee();

        PlayerFactory factory = new PlayerFactory();
        
        Player oPlayer = factory.create('O', messenger.getPlayerType('O'), messenger); 
        Player xPlayer = factory.create('X', messenger.getPlayerType('X'), messenger);
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        
        do {
            Game game = new Game(board, messenger, referee, scorer, xPlayer, oPlayer);
            
            game.play();
            
            messenger.displayResults();
            
            game.reset();
        } while (messenger.doPlayAgain());
    }
}
