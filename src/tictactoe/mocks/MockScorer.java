package tictactoe.mocks;

import tictactoe.game.Board;
import tictactoe.game.TicTacToeScorer;

public class MockScorer extends TicTacToeScorer {

    public boolean isGameOverCalled = false;
    public boolean resetCalled = false;
	public boolean isDrawCalled = false;
	public boolean isDraw = false;
	public boolean isGetWinnerCalled = false;
	public char winner = 'X';
    public MockScorer(Board board) {
        super(board);
    }
    
    public boolean isGameOver() {
        isGameOverCalled = true;
        return super.isGameOver();
    }

    public void reset() {
        resetCalled = true;
        super.reset();
    }
    
    public boolean isDraw() {
    	isDrawCalled  = true;
    	return isDraw;
    }
    
    public char getWinner() {
    	isGetWinnerCalled = true;
    	return winner;
    }
}
