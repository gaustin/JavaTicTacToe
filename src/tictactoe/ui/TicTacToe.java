package tictactoe.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tictactoe.game.*;
import tictactoe.players.HumanPlayer;
import tictactoe.players.NaivePlayer;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;
import tictactoe.players.PlayerTypes;

public class TicTacToe {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		Board board = new Board(9);
		IMessenger messenger = new ConsoleMessenger();
		IScorer scorer = new TicTacToeScorer(board);
		Referee referee = new Referee();

		PlayerFactory factory = new PlayerFactory();
		
		Player oPlayer = factory.create('O', getPlayerType(reader, 'O'), messenger); 
		Player xPlayer = factory.create('X', getPlayerType(reader, 'X'), messenger); ;
		
		String lastInput = "";
		
		while (!lastInput.equals("quit")) {
			Game game = new Game(board, messenger, referee, scorer, xPlayer, oPlayer);
			
			game.play();
			
			System.out.println(board.toString());
			if (scorer.isDraw())
				System.out.println("It was a draw!");
			else
				System.out.println(scorer.getWinner() + " won!");
			
			System.out.println("Press any key to play again ('quit' to exit):");
			lastInput = reader.readLine();
			game.reset();
		}
	}

	private static PlayerTypes getPlayerType(BufferedReader reader, char mark) throws IOException {
		PlayerTypes playerType = null;
		
		System.out.println("Enter a player type for the " + mark + " player (1) for Human, (2) for Computer: ");
		String input = reader.readLine();
		if ("1".equals(input)) {
			playerType = PlayerTypes.Human;
		} else if ("2".equals(input)) {
			playerType = PlayerTypes.NaiveComputer;
		}
		return playerType;
	}
}
