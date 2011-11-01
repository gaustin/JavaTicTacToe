package tictactoe.game;

import tictactoe.players.Player;

public interface IMessenger {
    public int getMoveFromPlayer(Player player);
    public void promptPlayerForMove(Player player);
    public void updateBoardDisplay();
    public void informPlayerOfInvalidChoice();
    public boolean doPlayAgain();
    public void displayResults();
}
