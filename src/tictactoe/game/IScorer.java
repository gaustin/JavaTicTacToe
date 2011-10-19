package tictactoe.game;

public interface IScorer {

	public boolean isGameOver();

	public boolean isDraw();
	
	public char getWinner();

	public void reset();
}
