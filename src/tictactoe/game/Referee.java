package tictactoe.game;

public class Referee implements IReferee {
    public boolean validateMove(Board board, char mark, int position) {
        if (position < 0 || position >= board.getSpaces().length || board.isTaken(position))
            return false;
        else
            return true;
    }
}
