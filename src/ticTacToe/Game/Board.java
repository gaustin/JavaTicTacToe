package ticTacToe.Game;

import java.util.*;

public class Board {
	
	private char[] cells = null;
	
	public Board(int x, int y) {
		cells = new char[x*y];
	}

	public List<Integer> emptySpaces() {
		ArrayList<Integer> empties = new ArrayList<Integer>();
		
		for (char c : cells)
			empties.add((int)c);
		
		return empties;
	}

	public boolean isTaken(int position) {
		return cells[position] != 0;
	}

	private boolean isTakenBy(char mark, int position) {
		return cells[position] == mark;
	}
	
	public boolean markPosition(char mark, int position) throws Exception {
		if (isTakenBy(mark, position))
			return true;
		
		if (isTaken(position))
			throw new Exception("This space is already marked.");
		
		cells[position] = mark;
		
		return true;
	}

}
