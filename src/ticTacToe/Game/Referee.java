package ticTacToe.Game;

public class Referee {

	public boolean validateMove(Board board, char mark, int position) {
		if (board.isTaken(position))
			return false;
		else
			return true;
	}

}
