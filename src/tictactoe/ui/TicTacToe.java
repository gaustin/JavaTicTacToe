package tictactoe.ui;


import java.io.IOException;

import tictactoe.game.*;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;

public class TicTacToe {

    public static void main(String[] args) throws IOException {
        
        Board board = new Board(9);
        IScorer scorer = new TicTacToeScorer(board);
        ConsoleMessenger messenger = new ConsoleMessenger(board, scorer);
        IReferee referee = new Referee();

        PlayerFactory factory = new PlayerFactory();
        
        Player oPlayer = factory.create('O', messenger.getPlayerType('O'), messenger); 
        Player xPlayer = factory.create('X', messenger.getPlayerType('X'), messenger);
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
