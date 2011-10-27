package tictactoe.players;

import tictactoe.game.Board;
import tictactoe.game.IMessenger;

public class HumanPlayer extends Player {

    private IMessenger messenger;
    
    public HumanPlayer(char mark) {
        super(mark);
    }
    
    public HumanPlayer(char mark, IMessenger messenger) {
        super(mark);
        this.messenger = messenger;
    }

    @Override
    public int getChoice(Board board) {
        int choice = messenger.getMoveFromPlayer(this);

        return choice;
    }

}
