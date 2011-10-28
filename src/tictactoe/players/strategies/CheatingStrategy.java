package tictactoe.players.strategies;

import java.util.List;
import java.util.Random;

import tictactoe.game.Board;
import tictactoe.players.Player;

public class CheatingStrategy implements IPlayerStrategy {

	@Override
	public int calculateBestMove(Board board, Player player) {
        Random generator = new Random();
        List<Integer> empties = board.emptySpaces();
        
        int choice = empties.get(generator.nextInt(empties.size()));
        board.markPosition(player.getMark(), choice);
        
        return choice;
	}

}
