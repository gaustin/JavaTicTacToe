package tictactoe.players;

import tictactoe.game.Board;

public class MinimaxPlayer extends Player {
    
    public MinimaxPlayer(char mark) {
        super(mark);
    }
    
    @Override
    public int getChoice(Board board) {
        return Minimax.startMinimax(board.copy(), this);
    }
}
