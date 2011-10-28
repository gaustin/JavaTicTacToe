package tictactoe.mocks;

import tictactoe.game.Board;
import tictactoe.players.ComputerPlayer;
import tictactoe.players.strategies.RandomStrategy;

public class MockPlayer extends ComputerPlayer {
    public boolean getChoiceCalled = false;
    
    public MockPlayer(char mark) {
        super(mark, new RandomStrategy());
    }

    @Override
    public int getChoice(Board board) {
        getChoiceCalled = true;
        return super.getChoice(board);
    }

}
