package tictactoe.game;

import tictactoe.players.Player;
import tictactoe.players.PlayerTypes;

public interface IMessenger {
    public int getMoveFromPlayer(Player player);
    public void promptPlayerForMove(Player player);
    public void updateBoardDisplay();
    public void informPlayerOfInvalidChoice();
    public boolean doPlayAgain();
    public void displayResults();
	public void choiceMade(int choice);
	public PlayerTypes getPlayerType(char mark);
}
