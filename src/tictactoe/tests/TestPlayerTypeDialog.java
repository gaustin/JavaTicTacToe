package tictactoe.tests;

import java.awt.Dialog.ModalityType;

import javax.swing.WindowConstants;

import tictactoe.ui.swing.PlayerTypeDialog;
import junit.framework.TestCase;

public class TestPlayerTypeDialog extends TestCase {

    PlayerTypeDialog pdg;
    public void setUp() {
        pdg = new PlayerTypeDialog('X');
    }
    
    public void testDialog() {
        assertEquals(ModalityType.APPLICATION_MODAL, pdg.getModalityType());
        assertEquals(WindowConstants.DISPOSE_ON_CLOSE, pdg.getDefaultCloseOperation());
    }
}
