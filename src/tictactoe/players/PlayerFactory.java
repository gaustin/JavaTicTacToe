package tictactoe.players;

import tictactoe.game.IMessenger;

public class PlayerFactory {

    public Player create(char mark, PlayerTypes playerType, IMessenger messenger) {
        Player player = null;
        
        switch (playerType) {
        case Human:
            player = new HumanPlayer(mark, messenger);
            break;
        case NaiveComputer:
            player = new NaivePlayer(mark);
            break;
        case MinimaxPlayer:
            player = new MinimaxPlayer(mark);
            break;
        default:
            player = new NaivePlayer(mark); // Worst case, they can watch crappy computers play.
            break;
        }
        return player;
    }

}
