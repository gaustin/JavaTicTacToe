package tictactoe.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tictactoe.game.*;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;

public class TicTacToe {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        Board board = new Board(9);
        IMessenger messenger = new ConsoleMessenger();
        IScorer scorer = new TicTacToeScorer(board);
        IReferee referee = new Referee();

        PlayerFactory factory = new PlayerFactory();
        
        Player oPlayer = factory.create('O', messenger.getPlayerType('O'), messenger); 
        Player xPlayer = factory.create('X', messenger.getPlayerType('X'), messenger);
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        
        String lastInput = "";
        
        while (!lastInput.equals("quit")) {
            Game game = new Game(board, messenger, referee, scorer, xPlayer, oPlayer);
            
            game.play();
            
            messenger.displayBoard(board);
            if (scorer.isDraw())
                System.out.println("It was a draw!");
            else
                System.out.println(scorer.getWinner() + " won!");
            
            System.out.println("Press any key to play again ('quit' to exit):");
            lastInput = reader.readLine();
            game.reset();
        }
    }
}
