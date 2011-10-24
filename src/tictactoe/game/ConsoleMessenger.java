package tictactoe.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import tictactoe.players.Player;
import tictactoe.players.PlayerTypes;

public class ConsoleMessenger implements IMessenger {

	@Override
	public int getMoveFromPlayer(Player player) {
		Scanner scanner = new Scanner(System.in);
		// TODO: Figure out a better way than using magic -1 (fix the board.isTaken method when you do).
		int choice;

		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException ex) {
			choice = -1;
		}

		return choice;
	}

	@Override
	public void promptPlayerForMove(Player player) {
		System.out.println("Player " + player.getMark() + " make a choice (0..8): ");
	}

	@Override
	public void displayBoard(Board board) {
		System.out.println(boardToString(board));
	}

	@Override
	public void informPlayerOfInvalidChoice() {
		System.out.println("Invalid move");
	}

	@Override
	public String boardToString(Board board) {
    	String boardRep =
    		" " + board.markOrDashAt(0) + " | " + board.markOrDashAt(1) + " | " + board.markOrDashAt(2) + " \n" +
            "-----------\n" +
            " " + board.markOrDashAt(3) + " | " + board.markOrDashAt(4) + " | " + board.markOrDashAt(5) + " \n" +
            "-----------\n" +
            " " + board.markOrDashAt(6) + " | " + board.markOrDashAt(7) + " | " + board.markOrDashAt(8) + " \n";
    	
    	return boardRep;
	}
	
	public PlayerTypes getPlayerType(char mark) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PlayerTypes playerType = null;
		
		System.out.println("Enter a player type for the " + mark + " player (1) for Human, (2) for Computer: ");
		String input = reader.readLine();
		if ("1".equals(input)) {
			playerType = PlayerTypes.Human;
		} else if ("2".equals(input)) {
			playerType = PlayerTypes.MinimaxPlayer;
		}
		return playerType;
	}
}