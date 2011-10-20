package tictactoe.game;

public interface IReferee {
	public boolean validateMove(Board board, char mark, int position);
}