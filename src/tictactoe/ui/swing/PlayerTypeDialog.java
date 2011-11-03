package tictactoe.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tictactoe.players.PlayerTypes;

public class PlayerTypeDialog extends JDialog {
    private static final long serialVersionUID = -3228154070397379488L;
    private PlayerTypes selectedPlayerType;
    
    public PlayerTypes getPlayerType() {
        return selectedPlayerType;
    }
    
    public PlayerTypeDialog(char mark) {
        JPanel panel = new JPanel();
        
        JLabel selectionLabel = new JLabel("Select " + mark + " Player Type");
        
        panel.add(selectionLabel);
        
        final PlayerChoiceComboBox playerTypeSelection = new PlayerChoiceComboBox();
        panel.add(playerTypeSelection);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                selectedPlayerType = playerTypeSelection.getPlayerType();
                dispose();
            }
        });
        
        panel.add(okButton);

        add(panel);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Select Player Type");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 75);        

        selectedPlayerType = playerTypeSelection.getPlayerType();
    }
}
