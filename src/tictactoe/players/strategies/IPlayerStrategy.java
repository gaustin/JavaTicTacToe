package tictactoe.players.strategies;

import tictactoe.game.Board;
import tictactoe.players.Player;

public interface IPlayerStrategy {
	public int calculateBestMove(Board board, Player player);
}
