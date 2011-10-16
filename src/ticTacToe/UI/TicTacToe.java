package ticTacToe.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ticTacToe.Game.*;
import ticTacToe.Players.NaivePlayer;

public class TicTacToe {

	public static void main(String[] args) throws IOException {
		Board board = new Board(9);
		NaivePlayer xPlayer = new NaivePlayer('X');
		NaivePlayer oPlayer = new NaivePlayer('O');
		IScorer scorer = new TicTacToeScorer(board);
		Referee referee = new Referee();

		String lastInput = "";
		Scanner in = new Scanner(System.in);
		
		while (!lastInput.equals("quit")) {
			Game game = new Game(board, referee, scorer, xPlayer, oPlayer);
			
			game.play();
			
			System.out.println(board.toString());
			if (scorer.isDraw())
				System.out.println("It was a draw!");
			else
				System.out.println(scorer.getWinner() + " won!");
			
			System.out.println("Press any key to continue ('quit' to exit):");
			lastInput = in.nextLine();
			game.reset();
		}
		

	}
}
