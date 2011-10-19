package tictactoe.players;

import java.util.*;

import tictactoe.game.Board;

public class NaivePlayer extends Player {

	public NaivePlayer(char mark) {
		super(mark);
	}

	@Override
	public int getChoice(Board board) {
		Random generator = new Random();
		List<Integer> empties = board.emptySpaces();
		
		return empties.get(generator.nextInt(empties.size()));
	}
}
