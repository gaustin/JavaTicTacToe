package ticTacToe.Players;

import java.util.*;

import ticTacToe.Game.Board;

public class NaivePlayer extends Player {

	public NaivePlayer(Character mark) {
		super(mark);
	}

	@Override
	public int getChoice(Board board) {
		Random generator = new Random();
		List<Integer> empties = board.emptySpaces();
		
		return empties.get(generator.nextInt(empties.size()));
	}
}
