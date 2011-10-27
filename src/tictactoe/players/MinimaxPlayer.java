package tictactoe.players;

import tictactoe.game.Board;
import tictactoe.players.strategies.IPlayerStrategy;
import tictactoe.players.strategies.MinimaxStrategy;

public class MinimaxPlayer extends Player {
    private IPlayerStrategy playerStrategy;
    
    public MinimaxPlayer(char mark) {
        super(mark);
        playerStrategy = new MinimaxStrategy();
    }
    
    @Override
    public int getChoice(Board board) {
        return playerStrategy.calculateBestMove(board.copy(), this);
    }
}
