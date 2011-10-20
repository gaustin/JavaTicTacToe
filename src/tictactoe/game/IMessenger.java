package tictactoe.game;

import java.io.IOException;

import tictactoe.players.Player;
import tictactoe.players.PlayerTypes;

public interface IMessenger {
	public int getMoveFromPlayer(Player player);
	public void promptPlayerForMove(Player player);
	public void displayBoard(Board board);
	public void informPlayerOfInvalidChoice();
	public String boardToString(Board board);
	public PlayerTypes getPlayerType(char mark) throws IOException;
}
