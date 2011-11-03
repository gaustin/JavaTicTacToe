package tictactoe.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tictactoe.game.IScorer;

public class ResultsDialog extends JDialog {
    private static final long serialVersionUID = -787633327752716343L;
    private boolean doReplay = false;
    public boolean getDoReplay() {
    	return doReplay;
    }

    public ResultsDialog(IScorer scorer) {
        JPanel panel = new JPanel();
        
        if (scorer.isDraw()) {
            panel.add(new JLabel("Game was a draw!"));
        } else {
            panel.add(new JLabel(scorer.getWinner() + " won the game!"));
        }
        
        JButton replayButton = new JButton("Replay");
        replayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	doReplay = true;
            	dispose();
            }
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	System.exit(0);
            }
        });
        
        panel.add(replayButton);
        panel.add(quitButton);
        add(panel);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Game over!");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 75);
    }
}
