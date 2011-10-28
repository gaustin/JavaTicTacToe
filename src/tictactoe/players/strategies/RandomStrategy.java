package tictactoe.players.strategies;

import java.util.List;
import java.util.Random;

import tictactoe.game.Board;
import tictactoe.players.Player;

public class RandomStrategy implements IPlayerStrategy {

	@Override
	public int calculateBestMove(Board board, Player player) {
        Random generator = new Random();
        List<Integer> empties = board.emptySpaces();
        
        return empties.get(generator.nextInt(empties.size()));
	}

}
