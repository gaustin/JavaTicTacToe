package ticTacToe.Game;

import java.util.*;

public class NaivePlayer extends Player<Character> {

	public NaivePlayer(Character mark) {
		super(mark);
	}

	@Override
	public int getChoice(Board<Character> board) {
		Random generator = new Random();
		List<Integer> empties = board.emptySpaces();
		
		return empties.get(generator.nextInt(empties.size()));
	}
}
