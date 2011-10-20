package tictactoe.game;

import tictactoe.players.Player;

public interface IMessenger {
	public int getMoveFromPlayer(Player player);
	public void promptPlayerForMove(Player player);
	public void displayBoard(Board board);
	public void informPlayerOfInvalidChoice();
	public String boardToString(Board board);
}
