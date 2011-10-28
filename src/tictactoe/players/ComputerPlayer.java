package tictactoe.players;

import tictactoe.game.Board;
import tictactoe.players.strategies.IPlayerStrategy;

public class ComputerPlayer extends Player {
    private IPlayerStrategy playerStrategy;
    
    public ComputerPlayer(char mark, IPlayerStrategy playerStrategy) {
        super(mark);
        this.playerStrategy = playerStrategy;
    }
    
    @Override
    public int getChoice(Board board) {
        return playerStrategy.calculateBestMove(board.copy(), this);
    }
}
