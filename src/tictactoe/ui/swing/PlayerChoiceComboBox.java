package tictactoe.ui.swing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import tictactoe.players.PlayerTypes;

public class PlayerChoiceComboBox extends JComboBox {
    private static final long serialVersionUID = 4202685946207645241L;

    private PlayerTypes playerType;
    
    public PlayerChoiceComboBox() {
        super(PlayerTypes.values());
    
        init();
    }
    
    private void init() {
        setSelectedIndex(-1);
        addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                setPlayer(event);
            }
        });
    }
    
    private void setPlayer(ItemEvent event) {
        JComboBox comboBox = (JComboBox)event.getSource();
        playerType = (PlayerTypes)comboBox.getSelectedItem();
    }

    public PlayerTypes getPlayerType() {
        return playerType;
    }
}
