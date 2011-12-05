package tictactoe.players;

import tictactoe.game.IMessenger;
import tictactoe.players.strategies.MinimaxStrategy;
import tictactoe.players.strategies.RandomStrategy;

public class PlayerFactory {

    public static Player create(char mark, PlayerTypes playerType, IMessenger messenger) {
        Player player = null;
        
        switch (playerType) {
        case Human:
            player = new HumanPlayer(mark, messenger);
            break;
        case NaiveComputer:
            player = new ComputerPlayer(mark, new RandomStrategy());
            break;
        case MinimaxComputer:
            player = new ComputerPlayer(mark, new MinimaxStrategy());
            break;
        default:
            player = new ComputerPlayer(mark, new RandomStrategy()); // Worst case, they can watch crappy computers play.
            break;
        }
        return player;
    }
}
