package tictactoe.ui;


import java.io.IOException;

import tictactoe.game.*;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;

public class TicTacToe {

    public static void main(String[] args) throws IOException {
        
        Board board = new Board(9);
        IScorer scorer = new TicTacToeScorer(board);
        IMessenger messenger = null;
        if (args.length == 1) { 
            messenger = MessengerFactory.create(MessengerTypes.valueOf(args[0].toUpperCase()), board, scorer);
        } else {
            messenger = MessengerFactory.create(MessengerTypes.CONSOLE, board, scorer);
        }
        
        IReferee referee = new Referee();

        Player oPlayer = PlayerFactory.create('O', messenger.getPlayerType('O'), messenger); 
        Player xPlayer = PlayerFactory.create('X', messenger.getPlayerType('X'), messenger);
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        
        do {
            Game game = new Game(board, messenger, referee, scorer, xPlayer, oPlayer);
            
            game.play();
            
            messenger.displayResults();
            
            game.reset();
        } while (messenger.doPlayAgain());
    }
}
