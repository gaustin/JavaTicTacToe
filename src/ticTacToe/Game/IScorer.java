package ticTacToe.Game;

public interface IScorer<TMark> {

	public boolean isGameOver();

	public TMark getWinner();

}
