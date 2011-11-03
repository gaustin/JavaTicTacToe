package tictactoe.tests;

import java.awt.Dialog.ModalityType;

import javax.swing.WindowConstants;

import junit.framework.TestCase;
import tictactoe.mocks.MockBoard;
import tictactoe.mocks.MockScorer;
import tictactoe.ui.swing.ResultsDialog;

public class TestResultsDialog extends TestCase {

    MockScorer scorer;
    ResultsDialog rdg;
    public void setUp() {
        scorer = new MockScorer(new MockBoard(9));
        rdg = new ResultsDialog(scorer);
    }
    
    public void testDialog() {
        assertEquals(ModalityType.APPLICATION_MODAL, rdg.getModalityType());
        assertEquals(WindowConstants.DISPOSE_ON_CLOSE, rdg.getDefaultCloseOperation());
    }
    
    public void testScorerExpectations() {
        assertTrue(scorer.isDrawCalled);
        assertTrue(scorer.isGetWinnerCalled);
    }
}
